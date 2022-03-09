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
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout passwordEditText, emailEditText, userNameEditText;
    private TextView skipTextView;
    private Button signingButton;
    private ImageButton googleImageButton, twitterImageButton, facebookImageButton;
    private ImageView backImageView;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_GOOGLE_SIGN_IN = 357753;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_register);

        userNameEditText = findViewById(R.id.user_name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        skipTextView = findViewById(R.id.textView_skip_register);
        backImageView = findViewById(R.id.image_view_back_register);
        signingButton = findViewById(R.id.signin_button);

        googleImageButton = findViewById(R.id.google_image_button);
        twitterImageButton = findViewById(R.id.twitter_image_button);
        facebookImageButton = findViewById(R.id.facebook_image_button);

        Log.i("TAG", "onCreate: RegisterActivity going to createGoogleRequest() method");
        createGoogleRequest();
        mAuth = FirebaseAuth.getInstance();


        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "signingButton.setOnClickListener: before Condition ");
                if (isValidateEmail() & isValidatePassword() & isValidateUserName()) {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                    Log.i("TAG", "signingButton.setOnClickListener: RegisterActivity going to MainActivity ");
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
                Log.i("TAG", "googleImageButton.setOnClickListener: RegisterActivity going to signInWithGoogleAccount() Method ");
                signInWithGoogleAccount();
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

    private void createGoogleRequest() {

        Log.i("TAG", "RegisterActivity createGoogleRequest() ");
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signInWithGoogleAccount() {
        Log.i("TAG", "RegisterActivity signInWithGoogleAccount(): ");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("TAG", "RegisterActivity onActivityResult(): ");

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.i("TAG", "firebaseAuthWithGoogle : account.getId()" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.i("TAG", "Google sign in failed", e);
            }
        }

    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "signInWithCredential:failure", task.getException());
                            // updateUI(null);
                        }
                    }
                });
    }

    private boolean isValidateUserName() {
        String emailInput = userNameEditText.getEditText().getText().toString().trim();
        boolean isValidate;
        if (emailInput.isEmpty()) {
            userNameEditText.setError(getResources().getString(R.string.user_name_error));
            isValidate = false;
        } else {
            userNameEditText.setError(null);
            isValidate = true;
        }
        return isValidate;
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