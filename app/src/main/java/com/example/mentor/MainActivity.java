package com.example.mentor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private Boolean isSpecialUser;

    private CardView doctorsCardView;
    private CardView newsCardView;
    private CardView appointmentsCardView;
    private CardView myProfileCardView;
    private CardView logOutCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("users").child( currentUser.getUid() );


        setUp();


    }


    private void setUp() {
        doctorsCardView = (CardView) findViewById(R.id.activity_main_doctors_cardView);
        newsCardView = (CardView) findViewById(R.id.activity_main_news_cardView);
        appointmentsCardView = (CardView) findViewById(R.id.activity_main_appointments_cardView);
        myProfileCardView = (CardView) findViewById(R.id.activity_main_myProfile_cardView);
        logOutCardView = (CardView) findViewById(R.id.activity_main_logOut_cardView);

        doctorsCardView.setOnClickListener(this);
        newsCardView.setOnClickListener(this);
        appointmentsCardView.setOnClickListener(this);
        myProfileCardView.setOnClickListener(this);
        logOutCardView.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        if( auth.getCurrentUser() == null ) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.activity_main_doctors_cardView:
                startActivity(new Intent(this, FindDoctorActivity.class));
                break;
            case R.id.activity_main_news_cardView:
                startActivity(new Intent(this, NewsActivity.class));
                break;
            case R.id.activity_main_appointments_cardView:
                startActivity(new Intent(this, EventListActivity.class));
                break;
            case R.id.activity_main_myProfile_cardView:
                startActivity(new Intent(this, UserProfileActivity.class));
                break;
            case R.id.activity_main_logOut_cardView:
                handleLogOutButton();
                break;

        }

    }

    private void handleLogOutButton() {
        LoginManager.getInstance().logOut();        // Facebook Connection Log out
        FirebaseAuth.getInstance().signOut();       // Firebase Connection Log out
        startActivity(new Intent(this,SignInActivity.class));
        startActivity(new Intent(this,SignInActivity.class));
        finish();
    }


}
