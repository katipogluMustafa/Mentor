package com.example.mentor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView doctorsCardView;
    private CardView newsCardView;
    private CardView appointmentsCardView;
    private CardView myProfileCardView;
    private CardView logOutCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                finish();
                startActivity(new Intent(this, SignInActivity.class));
                break;

        }

    }

    private boolean handleLogOutButton() {
        //TODO: Log-out button was clicked!
        return false;
    }


}
