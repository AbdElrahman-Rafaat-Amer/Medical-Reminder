package com.medication.medicalreminder.healthtaker.view;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.healthtaker.persenter.AddHealthTakerPresenter;
import com.medication.medicalreminder.healthtaker.persenter.AddHealthTakerPresenterInterface;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.model.UserPojo;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;

public class AddHealthTaker extends AppCompatActivity implements AddHealthTakerViewInterface {

    private EditText inviteFiendEditText;
    private Button inviteFriendButton;
    private AddHealthTakerPresenterInterface presenterInterface;
    private ImageView backImageView;
    private String TAG = "AddHealthTaker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_helath_taker);
        inviteFiendEditText = findViewById(R.id.inviteFriends_editText);
        inviteFriendButton = findViewById(R.id.send_invitation_button);
        backImageView = findViewById(R.id.back_imageView_cancel_invitation);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        presenterInterface = new AddHealthTakerPresenter(AddHealthTaker.this,
                Repository.getInstance(this, ConcreteLocalSource.getInstance(this), FirebaseOperation.getInstance()));

        inviteFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inviteFiendEditText.getText().toString().trim();
                if (isValidateEmail(email)) {
                    sendInvitation(email);
                    Log.i(TAG, "showInvitationDialog onClick: email " + email);
                }
            }
        });

    }

    private void sendInvitationRequest(String email) {
        //   sendInvitation(email);

        Log.i(TAG, "sendInvitationRequest: ");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = reference.orderByChild("email").equalTo(email);

        Log.i(TAG, "sendInvitationRequest: query " + query);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i(TAG, "onDataChange: ");
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        UserPojo userPojo = dataSnapshot.getValue(UserPojo.class);
                        Log.i(TAG, "onDataChange: Query\n userName : " + userPojo.getUserName() + "\tEmail : " + userPojo.getEmail()
                                + "\tPassword : " + userPojo.getPassword() + "\tAccessUID : " + userPojo.getAccessUID());
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(dataSnapshot.getKey());
                        // getAllData(reference);
                        reference.child("accessUID").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    }
                } else {
                    inviteFiendEditText.setError(getString(R.string.email_not_found));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "onCancelled: " + error.getMessage());

            }
        });
    }


    private boolean isValidateEmail(String email) {
        boolean isValidate;
        if (email.isEmpty()) {
            inviteFiendEditText.setError(getResources().getString(R.string.email_error));
            isValidate = false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                inviteFiendEditText.setError(getResources().getString(R.string.email_badFormat));
                isValidate = false;
            } else {
                inviteFiendEditText.setError(null);
                isValidate = true;
            }
        }
        return isValidate;
    }

    @Override
    public void sendInvitation(String email) {
        presenterInterface.sendInvitationRequest(email);
    }

    @Override
    public void receiveInvitationRequest(String message) {
        switch (message) {
            case "success":
              //  inviteFiendEditText.setError(getResources().getString(R.string.request_sent));
                Toast.makeText(this, "You send request success", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case "error":
                inviteFiendEditText.setError(getResources().getString(R.string.reserved_healthTaker));
                break;
            case "notfound":
                inviteFiendEditText.setError(getResources().getString(R.string.email_not_found));
                break;
        }
    }
}