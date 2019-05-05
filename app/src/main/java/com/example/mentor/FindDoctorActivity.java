package com.example.mentor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Gender;
import model.Speciality;
import model.SpinnerItem;
import ui.SpinnerAdapter;

@SuppressWarnings("unchecked")
public class FindDoctorActivity extends AppCompatActivity implements View.OnClickListener {


    private Spinner specialistSpinner;
    private Spinner genderSpinner;
    private Button findDoctorButton;
    private ImageButton backImageButton;

    private ArrayList<SpinnerItem> specialists;
    private ArrayList<SpinnerItem> genders;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        setUp();
        setSpinners();


    }

    private void setUp() {
        specialistSpinner = (Spinner) findViewById(R.id.activity_findDoctor_specialistSpinner);
        genderSpinner = (Spinner) findViewById(R.id.activity_findDoctor_genderSpinner);
        findDoctorButton = (Button) findViewById(R.id.activity_findDoctor_findDoctorButton);
        backImageButton = (ImageButton) findViewById(R.id.activity_findDoctor_backImageButton);

        findDoctorButton.setOnClickListener(this);
        backImageButton.setOnClickListener(this);
    }

    private void setSpinners() {
        specialists = new ArrayList<>();
        genders = new ArrayList<>();

        String[] specialists = getResources().getStringArray(R.array.specialists);
        for (String string : specialists)
            this.specialists.add(new SpinnerItem(string));

        String[] genders = getResources().getStringArray(R.array.genders);
        for (String string : genders)
            this.genders.add(new SpinnerItem(string));

        specialistSpinner.setAdapter(new SpinnerAdapter(this, this.specialists));
        genderSpinner.setAdapter(new SpinnerAdapter(this, this.genders));

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.activity_findDoctor_backImageButton:
                onBackPressed();
                break;
            case R.id.activity_findDoctor_findDoctorButton:
                if (!handleFindDoctorButton())
                    Snackbar.make(v, R.string.msg_account_not_found, Snackbar.LENGTH_LONG).show();
                else
                    startActivity(new Intent(this, DoctorListActivity.class));
                break;
        }

    }

    private boolean handleFindDoctorButton() {
        //TODO: FindDoctor button was clicked!
        String chosenSpeciality = (String)specialistSpinner.getSelectedItem();
        Speciality speciality = Speciality.specialityFactory(chosenSpeciality);

        String chosenGender = (String)genderSpinner.getSelectedItem();
        Gender gender = Gender.genderFactory(chosenGender);
/*
        ArrayList<String> doctorsWithSameSpeciality;
        .orderByChild("speciality").equalTo(speciality).orderByChild("gender").equalTo(gender);
        FirebaseDatabase.getInstance().getReference().child("users").orderByChild("speciality").equalTo( speciality.getIntValue() ).

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })
*/
        return true;
    }

    public void goBack(View v){
        super.onBackPressed();
    }
}
