package com.example.mentor;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileUserDetailsActivity extends AppCompatActivity {


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
    }

    public void setup(){
        EditText age = findViewById( R.id.user_details_age);
        // Database Setup
        db = FirebaseFirestore.getInstance();
        // Authentication Setup
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public void goProfile(View view){
        startActivity(new Intent(this,UserProfileActivity.class));
    }

<<<<<<< HEAD
=======
    //TODO: Handle profile photo Uri
    //TODO: Update operation always fails, refactor this method
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

    //TODO: Refactor this function, use DatabaseConnector class
    public boolean uploadToDatabase(String uid, Map<String,Object> user_details){
        CollectionReference user_packs = db.collection("user_packs");
        Task task = user_packs.document(uid).set(user_details);

        return task.isSuccessful() && task.isComplete();
    }

    //TODO: Refactor this function, use DatabaseConnector class
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
>>>>>>> ba364d3125218fa8894a885fa520318ce1e1cfae


}
