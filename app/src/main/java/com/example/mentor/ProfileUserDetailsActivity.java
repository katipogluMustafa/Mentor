package com.example.mentor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ProfileUserDetailsActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 71;

    // Firestore
    FirebaseFirestore db;

    // XML
    private ImageView profile_photo;
    private EditText prioritized_name;
    private EditText age;
    private EditText gender;
    private EditText blood;
    private EditText name;
    private EditText surname;
    private Button updateBtn;
    private Button profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user_details);
        //setup();
    }

    public void setup(){
        // XML Setup
        profile_photo = findViewById(R.id.edit_user_details_photo_imageView);
        prioritized_name = findViewById(R.id.edit_user_details_prioritized_name_editText);
        age = findViewById(R.id.edit_user_details_age_editText);
        gender = findViewById(R.id.edit_user_details_gender_editText);
        blood = findViewById(R.id.edit_user_details_blood_editText);
        name = findViewById(R.id.edit_user_details_name_editText);
        surname = findViewById(R.id.edit_user_details_surname_editText);
        updateBtn = findViewById(R.id.edit_user_details_update_btn);
        profileBtn = findViewById(R.id.edit_user_details_go_profile_btn);
        // Database Setup
        db = FirebaseFirestore.getInstance();
    }

    public void goProfile(View view){
        startActivity(new Intent(this,UserProfileActivity.class));
    }

    public void updateContent(View view){

    }

    public void updateFields(Map<String,Object> user_details){
        profile_photo.setImageURI( (Uri)user_details.get("photo_link") );
        prioritized_name.setText( (String)user_details.get("prioritized_name") );
        age.setText( (String)user_details.get("age") );
        gender.setText( (String)user_details.get("gender") );
        blood.setText( (String)user_details.get("blood") );
        name.setText( (String)user_details.get("name"));
        surname.setText( (String)user_details.get("surname") );
    }

    public Map<String,Object> getInputs() throws URISyntaxException {
        Map<String,Object> userPack = new HashMap<>();
        userPack.put("photo_link", getPhotoUri() );//TODO: put photo uri
        userPack.put("prioritized_name", prioritized_name.getText().toString());
        userPack.put("age", age.getText().toString());
        userPack.put("gender", gender.getText().toString());
        userPack.put("blood", blood.getText().toString());
        userPack.put("name", name.getText().toString());
        userPack.put("surname", surname.getText().toString());
        return userPack;
    }

    public void uploadToDatabase(String uid, Map<String,Object> user_details){
        CollectionReference user_packs = db.collection("user_packs");
        user_packs.document(uid).set(user_details);
    }

    public Map<String, Object> downloadFromDatabase(String uid){
        DocumentReference docRef = db.collection("user_packs").document(uid);
        Task<DocumentSnapshot> documentSnapshotTask =  docRef.get();

        documentSnapshotTask.isSuccessful(){
            DocumentSnapshot document = documentSnapshotTask.getResult();
            if( document.exists() )
                return document.getData();
            else{
                Log.d("DEBUG", "get failed with ", documentSnapshotTask.getException());
                return null;
            }

        }
    }

    //TODO: Compelete getPhotoUri method
    public Uri getPhotoUri(){
        return null;
    }

    //TODO: Compelete setPhotoUri method
    public boolean setPhotoUri(){
        return false;
    }


/*
    public void setup(){
        profile_image = findViewById(R.id.user_profile_image);
        Picasso.get().load("https://www.gravatar.com/avatar/1162ffcaa98e640c5ff5ac6e3048ff51?s=32&d=identicon&r=PG").resize(208,154).placeholder(R.drawable.test_doctor_1).into(profile_image);
    }


    public void uploadImage(View v){
        startActivityForResult( Intent.createChooser(new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT),"Select Profile Image"), PICK_IMAGE_REQUEST);
    }
*/

/*
    public void uploadToDatabase(){
        Map<String,Object> user_pack = getInputs();
        // Upload
        db.collection("user_packs").add(user_pack).addOnSuccessListener( documentReference ->{
            //TODO: log, successfull upload
        } ).addOnFailureListener( exception->{
            //TODO: log, error while adding document, exception.getMessage()
        });

        // Check
        db.collection("user_packs").get().addOnCompleteListener( task -> {
            if( task.isSuccessful() )
                for(QueryDocumentSnapshot document : task.getResult()){
                    Log.d("DEBUG", document.getId() + "=>" + document.getData());
                }
            else
                Log.w("DEBUG","Error getting documents", task.getException());
        });
    }
*/
}
