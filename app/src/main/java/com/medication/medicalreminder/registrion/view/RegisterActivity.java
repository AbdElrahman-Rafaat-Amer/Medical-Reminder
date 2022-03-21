package com.medication.medicalreminder.registrion.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.medication.medicalreminder.MainActivity;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.model.UserPojo;
import com.medication.medicalreminder.registrion.persenter.RegisterPresenter;
import com.medication.medicalreminder.registrion.persenter.RegisterPresenterInterface;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;


public class RegisterActivity extends AppCompatActivity implements RegisterViewInterface {

    private TextInputLayout passwordEditTextInputLayout, emailEditTextInputLayout, userNameEditTextInputLayout;
    private Button signingButton;
    private ImageButton googleImageButton, twitterImageButton, facebookImageButton;
    private ImageView backImageView;
    private RegisterPresenterInterface presenterInterface;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenterInterface = new RegisterPresenter(this,
                Repository.getInstance(this, ConcreteLocalSource.getInstance(this), FirebaseOperation.getInstance()));

        userNameEditTextInputLayout = findViewById(R.id.user_name_edit_text_textInputLayout);
        emailEditTextInputLayout = findViewById(R.id.email_edit_text_textInputLayout);
        passwordEditTextInputLayout = findViewById(R.id.password_edit_text_textInputLayout);
        backImageView = findViewById(R.id.image_view_back_register);
        signingButton = findViewById(R.id.signin_button);
        googleImageButton = findViewById(R.id.google_image_button);
        twitterImageButton = findViewById(R.id.twitter_image_button);
        facebookImageButton = findViewById(R.id.facebook_image_button);


        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "signingButton.setOnClickListener: before Condition ");
                if (isValidateEmail() & isValidatePassword() & isValidateUserName()) {
                    Log.i(TAG, "signingButton.setOnClickListener: in Condition ");
                    String email = emailEditTextInputLayout.getEditText().getText().toString().trim();
                    String password = passwordEditTextInputLayout.getEditText().getText().toString().trim();
                    String userName = userNameEditTextInputLayout.getEditText().getText().toString().trim();
                    Log.i(TAG, "onClick: userName---------------------> " + userName.length());
                    UserPojo userPojo = new UserPojo(userName, password, email, "NULL", "NULL", "NULL", "False");
                    presenterInterface.createAccount(userPojo);
                }

            }
        });


        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                Log.i(TAG, "backImageView.setOnClickListener: RegisterActivity going to LoginActivity ");
            }
        });

        googleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "googleImageButton.setOnClickListener: RegisterActivity going to GoogleLoginActivity");
                startActivity(new Intent(RegisterActivity.this, GoogleLoginActivity.class));
                finish();
            }
        });

        facebookImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "facebookImageButton.setOnClickListener: RegisterActivity going to FacebookLoginActivity");
                startActivity(new Intent(RegisterActivity.this, FacebookLoginActivity.class));
                finish();
            }
        });

        twitterImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "twitterImageButton.setOnClickListener: RegisterActivity going to Twitter");
            }
        });


    }

    private boolean isValidateUserName() {
        String emailInput = userNameEditTextInputLayout.getEditText().getText().toString().trim();
        boolean isValidate;
        if (emailInput.isEmpty()) {
            userNameEditTextInputLayout.setError(getResources().getString(R.string.user_name_error));
            isValidate = false;
        } else {
            if (emailInput.length() < 6) {
                userNameEditTextInputLayout.setError(getResources().getString(R.string.user_name_error_length));
                isValidate = false;
            } else {
                userNameEditTextInputLayout.setError(null);
                isValidate = true;
            }
        }
        return isValidate;
    }

    private boolean isValidateEmail() {
        String emailInput = emailEditTextInputLayout.getEditText().getText().toString().trim();
        boolean isValidate;
        if (emailInput.isEmpty()) {
            emailEditTextInputLayout.setError(getResources().getString(R.string.email_error));
            isValidate = false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                emailEditTextInputLayout.setError(getResources().getString(R.string.email_badFormat));
                isValidate = false;
            } else {
                emailEditTextInputLayout.setError(null);
                isValidate = true;
            }
        }
        return isValidate;
    }

    private boolean isValidatePassword() {
        String passwordInput = passwordEditTextInputLayout.getEditText().getText().toString().trim();
        boolean isValidate;
        if (passwordInput.isEmpty()) {
            passwordEditTextInputLayout.setError(getResources().getString(R.string.password_error));
            isValidate = false;
        } else {
            if (passwordInput.length() < 8) {
                passwordEditTextInputLayout.setError(getResources().getString(R.string.password_length_error));
                isValidate = false;
            } else {
                passwordEditTextInputLayout.setError(null);
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
    public void registerResponse(String reply) {
        if (reply.equals("success")) {
            finish();
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        } else {
            emailEditTextInputLayout.setError(reply);
            passwordEditTextInputLayout.setError(reply);
            userNameEditTextInputLayout.setError(reply);
        }
    }
}