package com.medication.medicalreminder.registrion.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.MainActivity;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.UserPojo;

public class GoogleLoginActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_GOOGLE_SIGN_IN = 357753;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);

        createGoogleRequest();
        signInWithGoogleAccount();
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
                            boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();
                            Log.i("GoogleLoginActivity", "onComplete: " + (isNew ? "new user" : "old user"));
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(isNew){
                                UserPojo testUser = new UserPojo(user.getDisplayName(), "", user.getEmail(), "NULL", "False", "NULL", "NULL");
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(testUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.i("TAG", "signingButton.setOnClickListener: RegisterActivity going to MainActivity ");
                                            startActivity(new Intent(GoogleLoginActivity.this, MainActivity.class));
                                            finish();

                                        } else {
                                            Log.i("TAG", "signingButton for else Authentication " + task.isSuccessful());
                                            Toast.makeText(GoogleLoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else {
                                startActivity(new Intent(GoogleLoginActivity.this, MainActivity.class));
                                finish();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "signInWithCredential:failure", task.getException());
                            // updateUI(null);
                        }
                    }
                });
    }
}