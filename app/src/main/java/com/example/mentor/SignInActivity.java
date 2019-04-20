package com.example.mentor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    // Firebase API
    private FirebaseAuth mAuth;

    // Facebook API
    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private Context context = this;

    private EditText emailEditText;
    private EditText passwordEditText;
    private RadioButton rememberRadioButton;
    private TextView forgetPasswordTextView;
    private Button logInButton;
    private ImageButton googlePlusImageButton;
    private TextView createAccountTextView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        setUp();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser user){
        if(user != null){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("Uid",user.getUid());
            startActivity(intent);
            finish();
        }
    }

    private void setUp() {
        emailEditText = (EditText) findViewById(R.id.activity_sign_in_email_editText);
        passwordEditText = (EditText) findViewById(R.id.activity_sign_in_password_editText);
        rememberRadioButton = (RadioButton) findViewById(R.id.activity_sign_in_remember_radioButton);
        forgetPasswordTextView = (TextView) findViewById(R.id.activity_sign_in_forget_password_textView);
        logInButton = (Button) findViewById(R.id.activity_sign_in_log_in_button);
        googlePlusImageButton = (ImageButton) findViewById(R.id.activity_sign_in_google_plus_imageButton);
        createAccountTextView = (TextView) findViewById(R.id.activity_sign_in_create_account_textView);

        rememberRadioButton.setOnClickListener(this);
        logInButton.setOnClickListener(this);
        googlePlusImageButton.setOnClickListener(this);
        forgetPasswordTextView.setOnClickListener(this);
        createAccountTextView.setOnClickListener(this);

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton = findViewById(R.id.activity_sign_in_facebook_imageButton);
        facebookLoginButton.setReadPermissions("email", "public_profile");
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //TODO: successfull login log
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                //TODO: facebook cancel log
                Toast.makeText(context,R.string.facebook_cancel,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                //TODO: facebook error log
                Toast.makeText(context,R.string.facebook_error,Toast.LENGTH_LONG).show();
            }
        });
        facebookLoginButton.setOnClickListener(v -> handleFacebookAccessToken(AccessToken.getCurrentAccessToken()));
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("DEBUG", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("DEBUG", "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("DEBUG", "signInWithCredential:failure", task.getException());
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.activity_sign_in_remember_radioButton:
                if (((RadioButton) v).isChecked())
                    ((RadioButton) v).setChecked(false);
                else
                    ((RadioButton) v).setChecked(true);
                break;

            case R.id.activity_sign_in_forget_password_textView:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;

            case R.id.activity_sign_in_log_in_button:
               handleLogInButton(v);
                break;
            case R.id.activity_sign_in_google_plus_imageButton:
                if (!handleGoogleButton()) {
                    Snackbar.make(v, R.string.error_message, Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.activity_sign_in_create_account_textView:
                startActivity(new Intent(this, SignUpActivity.class));


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleLogInButton(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, task->{
            if( task.isSuccessful() ){
                //TODO: Log to database, user has logged in "signInWithEmail:success"
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            }else
            {
                //TODO: Log to database, Authentication failed."signInWithEmail:failure"
                Snackbar.make(view, R.string.msg_account_not_found, Snackbar.LENGTH_LONG).show();
            }

        });
    }


    private boolean handleGoogleButton() {
        //TODO: Google button was clicked!
        return false;
    }


}
