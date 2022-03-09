package com.medication.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    private TextInputLayout passwordEditText, emailEditText;
    private TextView forgotPasswordTextView, skipTextView;
    private Button signingButton;
    private CheckBox rememberMeCheckbox;
    private ImageButton googleImageButton, twitterImageButton, facebookImageButton;
    private ImageView backImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("TAG", "onCreate: you are in LoginActivity");

        forgotPasswordTextView = findViewById(R.id.forgot_password_text_view);
        skipTextView = findViewById(R.id.textView_skip_login);
        backImageView = findViewById(R.id.image_view_back_login);
        signingButton = findViewById(R.id.signin_button);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        rememberMeCheckbox = findViewById(R.id.remember_me_checkbox);
        googleImageButton = findViewById(R.id.google_image_button);
        twitterImageButton = findViewById(R.id.twitter_image_button);
        facebookImageButton = findViewById(R.id.facebook_image_button);

        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "signingButton.setOnClickListener: beforeCondition");
                if (isValidateEmail() & isValidatePassword()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    Log.i("TAG", "signingButton.setOnClickListener: LoginActivity going to MainActivity");
                }

            }
        });
        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                Log.i("TAG", "skipTextView.setOnClickListener: LoginActivity going to MainActivity");
            }
        });

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "backImageView.setOnClickListener: LoginActivity going to RegisterActivity");
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

    }

    private boolean isValidateEmail() {
        String emailInput = emailEditText.getEditText().getText().toString().trim();
        boolean isValidate;
        if (emailInput.isEmpty()) {
            emailEditText.setError(getResources().getString(R.string.email_error));
            isValidate = false;
        } else {
            emailEditText.setError(null);
            isValidate = true;
        }
        return isValidate;
    }


    private boolean isValidatePassword() {
        String passwordInput = passwordEditText.getEditText().getText().toString().trim();
        boolean isValidate;
        if (passwordInput.isEmpty()) {
            passwordEditText.setError(getResources().getString(R.string.password_error));
            isValidate = false;
        } else {
            passwordEditText.setError(null);
            isValidate = true;
        }
        return isValidate;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}