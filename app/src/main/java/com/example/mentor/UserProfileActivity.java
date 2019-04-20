package com.example.mentor;

import android.os.Bundle;

import com.example.mentor.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    //TODO: Check whether the user is anonymous and require strict login to take new appointment
    //TODO: Require email verification, take a look at here : https://firebase.googleblog.com/2017/02/email-verification-in-firebase-auth.html
}
