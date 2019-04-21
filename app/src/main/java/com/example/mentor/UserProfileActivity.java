package com.example.mentor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import Controller.DatabaseConnector;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity{
    private TextView prioritized_name;
    private TextView account_balance;
    private TextView age;
    private TextView blood;
    private TextView gender;
    private CircleImageView profile_photo;
    private TextView name;
    private TextView surname;
    private Button update_user_details_btn;

    // Database
    FirebaseFirestore db;
    DatabaseConnector databaseConnector;

    // Authentication
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        db = FirebaseFirestore.getInstance();
    }

    public void setup(){
        prioritized_name = findViewById(R.id.edit_user_details_prioritized_name_editText);
        account_balance = findViewById(R.id.profile_account_ballance);
        age = findViewById(R.id.profile_age);
        blood = findViewById(R.id.profile_blood);
        gender = findViewById(R.id.profile_gender);
        profile_photo = findViewById(R.id.edit_user_details_photo_imageView);
        name = findViewById(R.id.profile_name_content);
        surname = findViewById(R.id.profile_surname_content);
        update_user_details_btn = findViewById(R.id.profile_user_details_update_btn);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();
        databaseConnector = new DatabaseConnector(db, currentUser);
        updateFields( databaseConnector.downloadUserDetails() );       // get user details from database
    }

    public void updateFields(Map<String,Object> user_details){
        Uri profile_photo_uri = (Uri)user_details.get("photo_link");
        String prioritized_name_s = (String)user_details.get("prioritized_name");
        String age_s = (String)user_details.get("age");
        String gender_s = (String)user_details.get("gender");
        String blood_s = (String)user_details.get("blood");
        String name_s = (String)user_details.get("name");
        String surname_s = (String)user_details.get("surname");

        // Default Value Assignments
        if( profile_photo_uri != null ) profile_photo.setImageURI( profile_photo_uri ); else profile_photo.setImageResource(R.drawable.test_doctor_1);
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

    public void goHome(View v){
        startActivity( new Intent(this, MainActivity.class));
    }

    public void goUserDetails(View v){
        startActivity( new Intent(this, ProfileUserDetailsActivity.class));
    }


    //TODO: Check whether the user is anonymous and require strict login to take new appointment
    //TODO: Require email verification, take a look at here : https://firebase.googleblog.com/2017/02/email-verification-in-firebase-auth.html
    //TODO: After completing required information, convert anonymous account to normal account : https://firebase.google.com/docs/auth/android/anonymous-auth?authuser=2
    //TODO: Put additional delete account button but first authenticate from email : https://firebase.google.com/docs/auth/android/manage-users#send_a_password_reset_email
}
