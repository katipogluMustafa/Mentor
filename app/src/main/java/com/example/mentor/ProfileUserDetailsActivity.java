package com.example.mentor;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

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



}
