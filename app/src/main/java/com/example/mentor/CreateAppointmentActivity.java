package com.example.mentor;

import android.os.Bundle;

import com.example.mentor.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
    }
}

//TODO: Check whether the user is anonymous and require strict login to take new appointment
