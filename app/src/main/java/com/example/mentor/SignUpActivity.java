package com.example.mentor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentor.R;
import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText mobileNumberEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button signUpButton;
    private ImageButton backImageButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setUp();
    }

    private void setUp() {
        nameEditText = (EditText) findViewById(R.id.activity_signUp_name_editText);
        emailEditText = (EditText) findViewById(R.id.activity_signUp_email_editText);
        mobileNumberEditText = (EditText) findViewById(R.id.activity_signUp_mobileNumber_editText);
        passwordEditText = (EditText) findViewById(R.id.activity_signUp_password_editText);
        confirmPasswordEditText = (EditText) findViewById(R.id.activity_signUp_confirmPassword_editText);
        signUpButton = (Button) findViewById(R.id.activity_signUp_signUp_button);
        backImageButton=(ImageButton)findViewById(R.id.activity_signUp_back_button);

        backImageButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.activity_signUp_back_button:
                onBackPressed();
                break;
            case R.id.activity_signUp_signUp_button:
                if(!handleSignUpButton()){
                    Snackbar.make(v, R.string.error_message, Snackbar.LENGTH_LONG).show();
                }


        }
    }

    private boolean handleSignUpButton() {
        //TODO: Sign-up button was clicked!
        return false;
    }
}
