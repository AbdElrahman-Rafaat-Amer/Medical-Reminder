package com.medication.medicalreminder.remotedatabase;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.Medication;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.UserPojo;

import java.util.ArrayList;
import java.util.HashMap;
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
    public void updateMedicine(Medicine medicine) {
        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ghada").child("MyUser");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList");
        HashMap hashMap = new HashMap();
        hashMap.put(medicine.getUid(), medicine); //add to firebase using the KEY
        reference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(getApplicationContext(), "Medicine have been updated",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void deleteMedicineFB(Medicine medicine) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList");
        Query query = reference.orderByChild("uid").equalTo(medicine.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        Medicine pojo = snap.getValue(Medicine.class);
                        Log.i("TAG", pojo.getName());
                        snap.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

        DatabaseReference referencee = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList").push();
        String  MedUid = referencee.getKey();
       medicine.setUid(MedUid);
        referencee.setValue(medicine);
      // referencee.child("Uid").setValue(MedUid);

    }

    @Override
    public void getAllMedicine(NetworkDelegate networkDelegate) {

        List<Medicine> storedMedicine = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList");
                //.getReference("Users").child("medicineList");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                storedMedicine.clear();
                for(DataSnapshot snapshot1: datasnapshot.getChildren()){
                    Medicine medicine= snapshot1.getValue(Medicine.class);
                    storedMedicine.add(medicine);
                }
                Log.i("TAG", "onDataChange Read from Firebase: " + storedMedicine);
                Log.i("TAG", "onDataChange Size of list " + storedMedicine.size());


                networkDelegate.onSuccessGetMediciene(storedMedicine);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        Log.i("TAG", "getAllMedicine: " + storedMedicine.size());

    }

}
