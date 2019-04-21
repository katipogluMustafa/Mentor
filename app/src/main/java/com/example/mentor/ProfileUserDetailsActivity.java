package com.example.mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ProfileUserDetailsActivity extends AppCompatActivity {

    private ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user_details);
        setup();
    }

    public void setup(){
        profile_image = findViewById(R.id.user_profile_image);
        Picasso.get().load("http://cdn.journaldev.com/wp-content/uploads/2017/01/android-constraint-layout-sdk-tool-install.png").into(profile_image);
    }

}
