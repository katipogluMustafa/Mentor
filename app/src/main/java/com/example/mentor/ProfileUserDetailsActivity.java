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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
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
        setup();
        updateFields(downloadFromDatabase(currentUser.getUid()));
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
        // Authentication Setup
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public void goProfile(View view){
        startActivity(new Intent(this,UserProfileActivity.class));
    }

    public void updateFields(Map<String,Object> user_details){
        if( user_details == null) {
           Log.d("DEBUG", "Can't update fields, null user details");
            return;
        }
 //       Uri profile_photo_uri = (Uri)user_details.get("photo_link");
        String prioritized_name_s = (String)user_details.get("prioritized_name");
        String age_s = (String)user_details.get("age");
        String gender_s = (String)user_details.get("gender");
        String blood_s = (String)user_details.get("blood");
        String name_s = (String)user_details.get("name");
        String surname_s = (String)user_details.get("surname");

        // Default Value Assignments
//        if( profile_photo_uri != null ) profile_photo.setImageURI( profile_photo_uri ); else profile_photo.setImageResource(R.drawable.test_doctor_1);
        if( prioritized_name_s == null )  prioritized_name_s = "Dr Anonymous";
        if( age_s == null  ) age_s = "0";
        if( gender_s == null) gender_s = "Male";
        if(blood_s == null ) blood_s = "AB rh+";
        if( name_s == null ) name_s = "Undefined";
        if( surname_s == null) surname_s = "Undefined";

        // Assign Values to Views
        age.setText( age_s );
        gender.setText( gender_s );
        blood.setText( blood_s );
        name.setText( name_s );
        surname.setText( surname_s );
        prioritized_name.setText( prioritized_name_s );
    }

    public void updateContent(View view){
        if( !uploadToDatabase( currentUser.getUid(), getInputs() ) ) {
            setContentAsDefault();
            Snackbar.make(view, "Couldn't successfully saved, check internet connection", Snackbar.LENGTH_LONG).show();
        }

    }

    public void setContentAsDefault(){
        profile_photo.setImageResource(R.drawable.test_doctor_1);
        age.setText( "0" );
        gender.setText( "Male" );
        blood.setText( "A rh+" );
        name.setText( "Undefined" );
        surname.setText( "Undefined" );
        prioritized_name.setText( "Dr Undefined" );
    }

    public Map<String,Object> getInputs() {
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

    public boolean uploadToDatabase(String uid, Map<String,Object> user_details){
        CollectionReference user_packs = db.collection("user_packs");
        Task task = user_packs.document(uid).set(user_details);

        return task.isSuccessful() && task.isComplete();
    }

    public Map<String, Object> downloadFromDatabase(String uid){
        if( uid == null)
            return null;

        DocumentReference docRef = db.collection("user_packs").document(uid);
        Task<DocumentSnapshot> documentSnapshotTask =  docRef.get();

        if( documentSnapshotTask.isSuccessful() ){
            DocumentSnapshot document = documentSnapshotTask.getResult();
            if( document.exists() )
                return document.getData();
        }

        Log.d("DEBUG", "get failed with ", documentSnapshotTask.getException());
        return null;
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
