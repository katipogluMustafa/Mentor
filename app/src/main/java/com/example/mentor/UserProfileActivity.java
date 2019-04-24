package com.example.mentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


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

    // Authentication
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setup();
    }

    public void setup(){
        prioritized_name = findViewById(R.id.profile_prioritized_name);
        account_balance = findViewById(R.id.profile_balance);
        age = findViewById(R.id.profile_age);
        blood = findViewById(R.id.profile_blood);
        gender = findViewById(R.id.profile_gender);
        profile_photo = findViewById(R.id.profile_photo_view);
        name = findViewById(R.id.profile_name);
        surname = findViewById(R.id.profile_surname);
        update_user_details_btn = findViewById(R.id.profile_update_btn);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
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
