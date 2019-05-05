package com.example.mentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;

import Controller.DatabaseController.AppointmentQuery;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import model.Appointment;
import model.User;

@SuppressWarnings("unchecked")
public class BookAppointmentCalenderActivity extends AppCompatActivity implements View.OnClickListener {

    private User currentUser;
    private User chosenDoctor;
    private Date timeStamp;

    private String appointmentUID;
    private String doctorUID;

    private CalendarView calendarView;
    private Button nextButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment_calender);

        Intent intent = getIntent();
        doctorUID = intent.getStringExtra("doctorUID");

        databaseSetup();
        setUp();

        Appointment current = new Appointment(currentUser,chosenDoctor, timeStamp);
        appointmentUID = AppointmentQuery.appointmentFactory(current);
    }

    private void databaseSetup() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");

        ref.child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentUser = User.createUser((HashMap<String,Object>)dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        ref.child( doctorUID ).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chosenDoctor = User.createUser((HashMap<String,Object>)dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    private void setUp() {
        calendarView = (CalendarView) findViewById(R.id.bookAppointmentCalenderActivity_calenderView);
        nextButton = (Button) findViewById(R.id.bookAppointmentCalenderActivity_nextButton);

        long appointmentDate = calendarView.getDate();
        timeStamp = new Date(appointmentDate);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BookAppointmentTimeActivity.class);
        intent.putExtra("appointmentUID", appointmentUID);
        startActivity(intent);
    }

    public void goBack(View v){
        super.onBackPressed();
    }

}
