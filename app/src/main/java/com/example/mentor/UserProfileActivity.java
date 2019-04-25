package com.example.mentor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.io.IOException;

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

    // Storage
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setup();
    }
    //TODO: Broken database connection, refactor
    public void setup(){
        // Authentication
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        // Storage
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // View initializations
        prioritized_name = findViewById(R.id.profile_prioritized_name);
        account_balance = findViewById(R.id.profile_balance);
        age = findViewById(R.id.profile_age);
        blood = findViewById(R.id.profile_blood);
        gender = findViewById(R.id.profile_gender);
        profile_photo = findViewById(R.id.profile_photo_view);
        name = findViewById(R.id.profile_name);
        surname = findViewById(R.id.profile_surname);
        update_user_details_btn = findViewById(R.id.profile_update_btn);

        // Profile Photo
        StorageReference photoRef = storageReference.child("user_photos/" + currentUser.getUid());
        photoRef.getDownloadUrl().addOnCompleteListener( task ->{
            if( task.isSuccessful())
                Picasso.get().load(task.getResult()).into(profile_photo);
            else
                Snackbar.make( profile_photo , task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
        });
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
