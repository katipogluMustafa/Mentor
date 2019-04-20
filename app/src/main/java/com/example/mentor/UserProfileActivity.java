package com.example.mentor;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {
    private TextView prioritized_name;
    private TextView account_balance;
    private TextView age;
    private TextView blood;
    private TextView gender;
    private CircleImageView profilePhoto;
    private TextView name;
    private TextView surname;
    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        db = FirebaseFirestore.getInstance();
    }

    public void setup(){
        prioritized_name = findViewById(R.id.profile_user_prioritized_name);
        account_balance = findViewById(R.id.profile_account_ballance);
        age = findViewById(R.id.profile_age);
        blood = findViewById(R.id.profile_blood);
        gender = findViewById(R.id.profile_gender);
        profilePhoto = findViewById(R.id.doctor_main_photo);
        name = findViewById(R.id.profile_user_actual_name);
        surname = findViewById(R.id.profile_user_actual_surname);

    }

    public void updateUserPack(){
        Map<String,Object> userPack= new HashMap<>();
        userPack.put("prioritized_name", prioritized_name.getText().toString());
        userPack.put("account_balance", Double.valueOf(account_balance.getText().toString()));
        userPack.put("age", Integer.valueOf(age.getText().toString()));
        userPack.put("blood", blood.getText().toString());
        userPack.put("gender", Boolean.valueOf(gender.getText().toString()));
        userPack.put("name", name.getText().toString());
        userPack.put("surname", surname.getText().toString());
        //TODO: insert into userPack the owner's uid

        db.collection("user_packs").add(userPack).addOnSuccessListener( documentReference -> {
            //TODO: sucessfully put to database, log
        }).addOnFailureListener( exception->{
            //TODO: Handle Exception or log to database error while adding to database
        });
    }






    //TODO: Check whether the user is anonymous and require strict login to take new appointment
    //TODO: Require email verification, take a look at here : https://firebase.googleblog.com/2017/02/email-verification-in-firebase-auth.html
    //TODO: After completing required information, convert anonymous account to normal account : https://firebase.google.com/docs/auth/android/anonymous-auth?authuser=2
    //TODO: Put additional delete account button but first authenticate from email : https://firebase.google.com/docs/auth/android/manage-users#send_a_password_reset_email
}
