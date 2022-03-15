package com.medication.medicalreminder.remotedatabase;

import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.Medication;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.UserPojo;
import com.medication.medicalreminder.model.Medicine;

import java.util.List;

public class FirebaseOperation implements FirebaseOperationInterface {
    private static DatabaseReference databaseReference = null;
    private static FirebaseOperation firebaseOperation = null;

    private FirebaseOperation() {
    }

    public static FirebaseOperation getInstance() {
        if (firebaseOperation == null)
            firebaseOperation = new FirebaseOperation();
        return firebaseOperation;
    }

    @Override
    public void insertUser(UserPojo userPojo) {

    }

    @Override
    public void insertMedicine() {

    }

    @Override
    public void updateMedicine(String UID) {

    }

    @Override
    public void deleteMedicine(String UID) {

    }

    @Override
    public UserPojo getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<Medication> getAllMedicine(String UID) {
        return null;
    }

    @Override
    public Medication getSpecificMedicine(String UID) {
        return null;
    }

    @Override
    public void addHealthTaker(String email, NetworkDelegate networkDelegate) {
        Log.i("TAG", "sendInvitationRequest: ");
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(email);

        Log.i("TAG", "sendInvitationRequest: query " + query);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("TAG", "onDataChange: ");
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        UserPojo userPojo = dataSnapshot.getValue(UserPojo.class);

                        if (userPojo.getAccessUID().equals("NULL")) {
                            Log.i("TAG", "onDataChange: Query\n userName : " + userPojo.getUserName() + "\tEmail : " + userPojo.getEmail()
                                    + "\tPassword : " + userPojo.getPassword() + "\tAccessUID : " + userPojo.getAccessUID());
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(dataSnapshot.getKey());
                            //   getAllData(reference);
                            reference.child("accessUID").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            networkDelegate.onSuccessInvitation(Resources.getSystem().getString(R.string.added_done) + userPojo.getUserName());
                        } else {
                            networkDelegate.onFailureInvitation(Resources.getSystem().getString(R.string.reserved_healthTaker));
                        }

                    }
                } else {
                    networkDelegate.onFailureInvitation(Resources.getSystem().getString(R.string.email_not_found));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG", "onCancelled: " + error.getMessage());

            }
        });
    }

    @Override
    public void addMedToFireBase(Medicine medicine) {

        DatabaseReference referencee = FirebaseDatabase.getInstance().getReference("lastMedObject").child("Med").push();
        String  Mid = referencee.getKey();
        referencee.child("key").setValue(Mid);
        referencee.setValue(medicine);
    }


}
