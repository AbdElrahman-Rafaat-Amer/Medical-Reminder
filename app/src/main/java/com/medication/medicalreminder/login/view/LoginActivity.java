package com.medication.medicalreminder.login.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.medication.medicalreminder.MainActivity;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.login.persenter.LoginPresenter;
import com.medication.medicalreminder.login.persenter.LoginPresenterInterface;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.registrion.view.FacebookLoginActivity;
import com.medication.medicalreminder.registrion.view.GoogleLoginActivity;
import com.medication.medicalreminder.registrion.view.RegisterActivity;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;


public class LoginActivity extends AppCompatActivity implements LoginViewInterface {
    private static final String TAG = "LoginActivity";

    private TextInputLayout passwordEditText, emailEditText;
    private TextView forgotPasswordTextView, skipTextView;
    private Button signingButton;
    private CheckBox rememberMeCheckbox;
    private ImageButton googleImageButton, twitterImageButton, facebookImageButton;
    private ImageView backImageView;
    private LoginPresenterInterface presenterInterface;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenterInterface = new LoginPresenter(this,
                Repository.getInstance(this, ConcreteLocalSource.getInstance(this), FirebaseOperation.getInstance()));


        Log.i(TAG, "onCreate: you are in LoginActivity");

        forgotPasswordTextView = findViewById(R.id.forgot_password_text_view);
        skipTextView = findViewById(R.id.textView_skip_login);
        backImageView = findViewById(R.id.image_view_back_login);
        signingButton = findViewById(R.id.signin_button);
        emailEditText = findViewById(R.id.email_edit_text_textInputLayout);
        passwordEditText = findViewById(R.id.password_edit_text_textInputLayout);
        rememberMeCheckbox = findViewById(R.id.remember_me_checkbox);
        googleImageButton = findViewById(R.id.google_image_button);
        twitterImageButton = findViewById(R.id.twitter_image_button);
        facebookImageButton = findViewById(R.id.facebook_image_button);

        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "signingButton.setOnClickListener: beforeCondition");
                if (isValidateEmail() & isValidatePassword()) {
                    String email = emailEditText.getEditText().getText().toString().trim();
                    String password = passwordEditText.getEditText().getText().toString().trim();
                    presenterInterface.signWithEmail(email, password);
                }
            }
        });

        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                Log.i(TAG, "skipTextView.setOnClickListener: LoginActivity going to MainActivity");
            }
        });

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "backImageView.setOnClickListener: LoginActivity going to RegisterActivity");
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        googleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, GoogleLoginActivity.class));
                finish();
            }
        });

        facebookImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "facebookImageButton.setOnClickListener: RegisterActivity going to FacebookLoginActivity");
                startActivity(new Intent(LoginActivity.this, FacebookLoginActivity.class));
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
            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                emailEditText.setError(getResources().getString(R.string.email_badFormat));
                isValidate = true;
            } else {
                emailEditText.setError(null);
                isValidate = true;
            }
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
            if (passwordInput.length() < 8) {
                passwordEditText.setError(getResources().getString(R.string.password_length_error));
                isValidate = false;
            } else {
                passwordEditText.setError(null);
                isValidate = true;
            }
        }
        return isValidate;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void loginResponse(String reply) {
        if (reply.equals("success")) {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            emailEditText.setError(reply);
            passwordEditText.setError(reply);
        }
    }
}