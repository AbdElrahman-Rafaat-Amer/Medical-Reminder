package com.medication.medicalreminder;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.LoginStatusCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Arrays;


public class FacebookLoginActivity extends AppCompatActivity {

    String TAG = "GoogleUserFragment";
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private TextView name;
    private TextView email;
    private TextView phoneNumber;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        Log.i(TAG, "onStart: ");
     //   FirebaseUser currentUser = mAuth.getCurrentUser();
     //   updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Log.i(TAG, "before updateUI: ");
        Log.i(TAG, "updateUI: currentUser " + currentUser);
        if (currentUser != null) {
            name.setText(currentUser.getDisplayName());
            phoneNumber.setText(currentUser.getPhoneNumber());
            email.setText(currentUser.getEmail());
        } else {

            name.setText("null");
            phoneNumber.setText("null");
            email.setText("null");
        }
        Log.i(TAG, "after updateUI: ");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_google_user);

        Log.i(TAG, "onCreate: ");
        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
             AccessToken accessToken = AccessToken.getCurrentAccessToken();
             boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
             LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));


        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
       // loginButton.setReadPermissions("email", "public_profile");
        loginButton.setReadPermissions("email", "public_profile", "user_friends");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                handleFacebookAccessToken(loginResult.getAccessToken());
                Log.i(TAG, "onSuccess: loginResult " + loginResult.toString());
                Log.i(TAG, "onSuccess: loginResult toString " + loginResult.toString());
                Log.i(TAG, "onSuccess: loginResult getAccessToken " + loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code

                Log.i(TAG, "onCancel: ");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i(TAG, "onError: exception " + exception);
            }
        });


    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.i(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.i(TAG, "onComplete: FirebaseUser " + user);
                            Log.i(TAG, "onComplete: mAuth " + mAuth);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: requestCode " + requestCode);
        Log.i(TAG, "onActivityResult: resultCode " + resultCode);
        Log.i(TAG, "onActivityResult: data " + data);
    }
}