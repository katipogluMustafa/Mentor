package com.example.mentor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.hdodenhof.circleimageview.CircleImageView;

import com.google.android.material.snackbar.Snackbar;

public class DoctorActivity extends AppCompatActivity implements View.OnClickListener {

    private String doctorUID;

    private static final int PERMISSION_REQ_ID = 22;
    private static final String[] REQUESTED_PERMISSIONS = {Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};

    private CircleImageView doctorCircularImage;
    private TextView doctorNameTextView;
    private TextView doctorSpecialistTextView;
    private LinearLayout photosLinearLayout;
    private LinearLayout reviewsLinearLayout;
    private LinearLayout callLinearLayout;
    private TextView aboutTextView;
    private Button bookAppointmentButton;
    private ImageButton backImageButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        setUp();
        getPassedStrings(getIntent());

    }


    private void setUp() {
        doctorCircularImage = (CircleImageView) findViewById(R.id.news_circularImageView);
        doctorNameTextView = (TextView) findViewById(R.id.activity_doctor_doctorName_textView);
        doctorSpecialistTextView = (TextView) findViewById(R.id.activity_doctor_doctorSpecialist_textView);
        photosLinearLayout = (LinearLayout) findViewById(R.id.activity_doctor_photosLinearLayout);
        reviewsLinearLayout = (LinearLayout) findViewById(R.id.activity_doctor_reviewsLinearLayout);
        callLinearLayout = (LinearLayout) findViewById(R.id.activity_doctor_callLinearLayout);
        aboutTextView = (TextView) findViewById(R.id.activity_doctor_doctorAbout_textView);
        bookAppointmentButton = (Button) findViewById(R.id.activity_doctor_bookAppointmentButton);
        backImageButton = (ImageButton) findViewById(R.id.activity_doctor_backImageButton);

        photosLinearLayout.setOnClickListener(this);
        reviewsLinearLayout.setOnClickListener(this);
        callLinearLayout.setOnClickListener(this);
        bookAppointmentButton.setOnClickListener(this);
        backImageButton.setOnClickListener(this);
    }

    private void getPassedStrings(Intent intent) {
        doctorUID = intent.getStringExtra("doctorUID");
        doctorNameTextView.setText(intent.getStringExtra("doctorName"));
        doctorSpecialistTextView.setText(intent.getStringExtra("doctorSpecialist"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.activity_doctor_photosLinearLayout:
                if (!handlePhotos())
                    Snackbar.make(v, R.string.error_message, Snackbar.LENGTH_LONG).show();
                break;

            case R.id.activity_doctor_reviewsLinearLayout:
                startActivity(new Intent(this, ReviewActivity.class));
                break;

            case R.id.activity_doctor_callLinearLayout:
                callDoctor();
                break;

            case R.id.activity_doctor_bookAppointmentButton:
                Intent intent = new Intent(this, BookAppointmentCalenderActivity.class);
                intent.putExtra("doctorUID",doctorUID);
                startActivity(intent);
                break;

            case R.id.activity_doctor_backImageButton:
                onBackPressed();
                break;

        }
    }

    private void callDoctor() {
        if( checkSelfPermission( REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) && checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) ){
            startActivity(new Intent(this, CallingActivity.class));
        }
    }

    private boolean handlePhotos() {
        //TODO: The user wants to see the doctor photos!
        return false;
    }

    public boolean checkSelfPermission(String permission, int requestCode){
        if( ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch ( requestCode ){
            case PERMISSION_REQ_ID:
                if( grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"You need to give permissions first", Toast.LENGTH_LONG).show();
                    super.onBackPressed();      // go back to last activity
                }
                break;
        }
    }

    public void goBack(View v){
        super.onBackPressed();
    }
}
