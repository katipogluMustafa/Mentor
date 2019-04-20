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

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText emailEditText;
    private Button resetPasswordButton;
    private ImageButton backImageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setUp();

    }

    private void setUp() {
        emailEditText = (EditText) findViewById(R.id.activity_forgetPass_email_editText);
        resetPasswordButton = (Button) findViewById(R.id.activity_forgetPass_resetPassword_button);
        backImageButton = (ImageButton) findViewById(R.id.activity_forgetPass_backButton);

        resetPasswordButton.setOnClickListener(this);
        backImageButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.activity_forgetPass_resetPassword_button:
                if (!handleResetPasswordButton())
                    Snackbar.make(v, R.string.error_message, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.activity_forgetPass_backButton:
                onBackPressed();
                break;


        }
    }

    private boolean handleResetPasswordButton() {
        //TODO: Reset password button was clicked!
        return false;
    }
}
