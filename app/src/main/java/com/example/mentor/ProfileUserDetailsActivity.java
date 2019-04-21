package com.example.mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ProfileUserDetailsActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 71;
    private ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user_details);
        //setup();
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
}
