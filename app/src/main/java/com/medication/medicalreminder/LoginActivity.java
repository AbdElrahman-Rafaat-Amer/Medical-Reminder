package com.medication.medicalreminder;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private TextInputLayout passwordEditText, emailEditText;
    private TextView forgotPasswordTextView, skipTextView;
    private Button signingButton;
    private CheckBox rememberMeCheckbox;
    private ImageButton googleImageButton, twitterImageButton, facebookImageButton;
    private ImageView backImageView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("TAG", "onCreate: you are in LoginActivity");

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
        mAuth = FirebaseAuth.getInstance();
        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "signingButton.setOnClickListener: beforeCondition");
                if (isValidateEmail() & isValidatePassword()) {
                    String email = emailEditText.getEditText().getText().toString().trim();
                    String password = passwordEditText.getEditText().getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                Log.i("TAG", "onResume: user success" + user);
                                Log.i("TAG", "onResume: user.getDisplayName " + user.getDisplayName());
                                Log.i("TAG", "onResume: user.getEmail " + user.getEmail());
                                Log.i("TAG", "signingButton.setOnClickListener: LoginActivity going to MainActivity");
                                finish();
                            } else {
                                Log.i("TAG", "signingButton.onComplete: Exception" + task.getException());
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("TAG", "signingButton.addOnFailureListener: Exception" + e.getMessage());
                        }
                    });

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

        googleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, GoogleLoginActivity.class));
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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.i("TAG", "onResume: user success" + user);
            Log.i("TAG", "onResume: user.getDisplayName " + user.getDisplayName());
            Log.i("TAG", "onResume: user.getEmail " + user.getEmail());
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Log.i("TAG", "onResume: user failed------------>>>>>>> " + user);
        }
    }
}