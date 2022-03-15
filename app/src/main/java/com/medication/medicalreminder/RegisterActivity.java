package com.medication.medicalreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.model.UserPojo;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout passwordEditTextInputLayout, emailEditTextInputLayout, userNameEditTextInputLayout;
    private TextInputEditText passwordTextInputEditText, emailTextInputEditText, userNameTextInputEditText;
    private TextView skipTextView;
    private Button signingButton;
    private ImageButton googleImageButton, twitterImageButton, facebookImageButton;
    private ImageView backImageView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_register);

        //layout
        userNameEditTextInputLayout = findViewById(R.id.user_name_edit_text_textInputLayout);
        emailEditTextInputLayout = findViewById(R.id.email_edit_text_textInputLayout);
        passwordEditTextInputLayout = findViewById(R.id.password_edit_text_textInputLayout);
        //EditText
        passwordTextInputEditText = findViewById(R.id.password_edit_text_TextInputEditText);
        emailTextInputEditText = findViewById(R.id.email_edit_text_TextInputEditText);
        userNameTextInputEditText = findViewById(R.id.user_name_edit_text_TextInputEditText);

        skipTextView = findViewById(R.id.textView_skip_register);
        backImageView = findViewById(R.id.image_view_back_register);
        signingButton = findViewById(R.id.signin_button);

        googleImageButton = findViewById(R.id.google_image_button);
        twitterImageButton = findViewById(R.id.twitter_image_button);
        facebookImageButton = findViewById(R.id.facebook_image_button);

        Log.i("TAG", "onCreate: RegisterActivity going to createGoogleRequest() method");
        mAuth = FirebaseAuth.getInstance();


        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "signingButton.setOnClickListener: before Condition ");
                if (isValidateEmail() & isValidatePassword() & isValidateUserName()) {
                    Log.i("TAG", "signingButton.setOnClickListener: in Condition ");
                    String email = emailTextInputEditText.getText().toString().trim();
                    String password = passwordTextInputEditText.getText().toString().trim();
                    String userName = userNameTextInputEditText.getText().toString().trim();
                    Log.i("TAG", "signingButton.setOnClickListener: in Condition email " + email);
                    Log.i("TAG", "signingButton.setOnClickListener: in Condition password " + password);
                    Log.i("TAG", "signingButton.setOnClickListener: in Condition userName " + userName);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        Log.i("TAG", "signingButton for if first one task.isSuccessful()  " + task.isSuccessful());
                                        UserPojo user = new UserPojo(userName, password, email,"NULL",null);
                                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.i("TAG", "signingButton for if second one task.isSuccessful()  " + task.isSuccessful());
                                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                                    finish();
                                                    Log.i("TAG", "signingButton.setOnClickListener: RegisterActivity going to MainActivity ");
                                                } else {
                                                    Log.i("TAG", "signingButton for else second one task.isSuccessful() " + task.isSuccessful());
                                                }
                                            }
                                        });
                                    } else {
                                        Log.i("TAG", "signingButton for else first one task.isSuccessful()  " + task.isSuccessful());
                                        Log.i("TAG", "signingButton for else first one task.isSuccessful()  " + task.getException());
                                        emailEditTextInputLayout.setError(task.getException().getMessage());
                                    }


                                }
                            });

                  /*  mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.i("TAG", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Log.i("TAG", "createUserWithEmail:success" + user.getEmail());
                                        Log.i("TAG", "createUserWithEmail:success" + user.getDisplayName());
                                        //     updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.i("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        //   updateUI(null);
                                    }
                                }
                            });*/

                }

            }
        });
        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
                Log.i("TAG", "skipTextView.setOnClickListener: RegisterActivity going to MainActivity ");
            }
        });

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                Log.i("TAG", "backImageView.setOnClickListener: RegisterActivity going to LoginActivity ");
            }
        });

        googleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "googleImageButton.setOnClickListener: RegisterActivity going to GoogleLoginActivity signInWithGoogleAccount() Method ");
                // signInWithGoogleAccount();
                startActivity(new Intent(RegisterActivity.this, GoogleLoginActivity.class));
            }
        });

        facebookImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "facebookImageButton.setOnClickListener: RegisterActivity going to FacebookLoginActivity");
                startActivity(new Intent(RegisterActivity.this, FacebookLoginActivity.class));
                finish();
            }
        });

        twitterImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "twitterImageButton.setOnClickListener: RegisterActivity going to Twitter");
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG", "onStart: ");
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.i("TAG", "onStart: user success" + user);
            Log.i("TAG", "onStart: user.getDisplayName " + user.getDisplayName());
            Log.i("TAG", "onStart: user.getEmail " + user.getEmail());

        } else {
            Log.i("TAG", "onStart: user failed ------------------->> " + user);
//            Log.i("TAG", "onStart: user " + user.getEmail());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "onResume: ");
        //   mAuth.signOut();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.i("TAG", "onResume: user success" + user);
            Log.i("TAG", "onResume: user.getDisplayName " + user.getDisplayName());
            Log.i("TAG", "onResume: user.getEmail " + user.getEmail());

        } else {
            Log.i("TAG", "onResume: user failed------------>>>>>>> " + user);
        }
    }


    private boolean isValidateUserName() {
        String emailInput = userNameEditTextInputLayout.getEditText().getText().toString().trim();
        boolean isValidate;
        if (emailInput.isEmpty()) {
            userNameEditTextInputLayout.setError(getResources().getString(R.string.user_name_error));
            isValidate = false;
        } else {

            userNameEditTextInputLayout.setError(null);
            isValidate = true;
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
            emailEditTextInputLayout.setError(null);
            isValidate = true;
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

}