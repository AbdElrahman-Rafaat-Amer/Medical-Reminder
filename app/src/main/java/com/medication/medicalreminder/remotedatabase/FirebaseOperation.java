package com.medication.medicalreminder.remotedatabase;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.UserPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FirebaseOperation implements FirebaseOperationInterface {
    private static DatabaseReference databaseReference = null;
    private static FirebaseOperation firebaseOperation = null;
    private static final String TAG = "FirebaseOperation";
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private FirebaseOperation() {
    }

    public static FirebaseOperation getInstance() {
        if (firebaseOperation == null)
            firebaseOperation = new FirebaseOperation();
        return firebaseOperation;
    }


    @Override
    public void addHealthTaker(String email, NetworkDelegate networkDelegate) {
        Log.i(TAG, "sendInvitationRequest: ");
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(email);

        Log.i(TAG, "sendInvitationRequest: query " + query);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i(TAG, "onDataChange: ");
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        UserPojo userPojo = dataSnapshot.getValue(UserPojo.class);
                        if (userPojo.getAccessUID().equals("NULL")) {
                            Log.i(TAG, "onDataChange: Query\n userName : " + userPojo.getUserName() + "\tEmail : " + userPojo.getEmail()
                                    + "\tPassword : " + userPojo.getPassword() + "\tAccessUID : " + userPojo.getAccessUID());
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(dataSnapshot.getKey());
                            reference.child("accessUID").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    UserPojo user = task.getResult().getValue(UserPojo.class);
                                    reference.child("requesterName").setValue(user.getUserName());
                                }
                            });
                            networkDelegate.onInvitationResponse("success");
                        } else {
                            networkDelegate.onInvitationResponse("error");
                        }

                    }
                } else {
                    networkDelegate.onInvitationResponse("notfound");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG", "onCancelled: " + error.getMessage());

            }
        });
    }

    @Override
    public void sendReplyAddHealthTaker(String replyMessage, String UID) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(UID).child("requestReply");
        databaseReference.setValue(replyMessage);
        Log.i(TAG, "sendReplyAddHealthTaker: UID>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + UID);
        Log.i(TAG, "sendReplyAddHealthTaker: replyMessage >>>>>>>>>>>>>>>>>>>>>>>>> " + replyMessage);
    }

    @Override
    public void signWithEmail(String email, String password, NetworkDelegate networkDelegate) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.i(TAG, "signWithEmail: user success" + user);
                    Log.i(TAG, "signWithEmail: user.getDisplayName " + user.getDisplayName());
                    Log.i(TAG, "signWithEmail: user.getEmail " + user.getEmail());
                    networkDelegate.onResponseLogin("success");
                } else {
                    Log.i(TAG, "signWithEmail: Exception" + task.getException());
                    networkDelegate.onResponseLogin(task.getException().getMessage());
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "signWithEmail addOnFailureListener" + e.getMessage());
                networkDelegate.onResponseLogin(e.getMessage());
            }
        });
    }

    @Override
    public void createUserWithEmailAndPassword(UserPojo user, NetworkDelegate networkDelegate) {
        String email = user.getEmail();
        String password = user.getPassword();
        Log.i(TAG, "createUserWithEmailAndPassword: email--------------> " + email);
        Log.i(TAG, "createUserWithEmailAndPassword: password-----------> " + password);

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.i("TAG", "signingButton for if first one task.isSuccessful()  " + task.isSuccessful());
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.i("TAG", "signingButton.setOnClickListener: RegisterActivity going to MainActivity ");
                                        networkDelegate.onResponseRegister("success");
                                    } else {
                                        Log.i("TAG", "signingButton for else Authentication " + task.isSuccessful());
                                        networkDelegate.onResponseRegister(task.getException().getMessage());
                                    }
                                }
                            });
                        } else {
                            networkDelegate.onResponseRegister(task.getException().getMessage());
                            Log.i("TAG", "signingButton for addOnCompleteListener  " + task.getException());
                        }
                    }
                });
    }

    @Override
    public void sendReplyOnInvitationOfTaker(boolean isAccept) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        if (isAccept) {
            databaseReference.child("requestReply").setValue("True");
            sendReplyOnInvitationToRequester(databaseReference);
        } else {
            databaseReference.child("accessUID").setValue("NULL");
            databaseReference.child("requestReply").setValue("False");
            databaseReference.child("requesterName").setValue("NULL");
        }

    }

    @Override
    public void addMedToFireBase(Medicine medicine) {
        DatabaseReference referencee = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList").push();
        String MedUid = referencee.getKey();
        medicine.setUid(MedUid);
        referencee.setValue(medicine);
    }

    @Override
    public void addMedicineHealthTaker(Medicine medicine) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserPojo user = snapshot.getValue(UserPojo.class);
                String accessUID = user.getAccessUID();
                if (accessUID.equals("NULL")) {
                    Log.i(TAG, "addMedicineHealthTaker: accessUID " + accessUID);
                    Log.i(TAG, "addMedicineHealthTaker: Error No data found ");
                } else {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(accessUID).child("medicineList").push();
                    String MedUid = reference.getKey();
                    medicine.setUid(MedUid);
                    reference.setValue(medicine);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                for (DataSnapshot snapshot1 : datasnapshot.getChildren()) {
                    Medicine medicine = snapshot1.getValue(Medicine.class);
                    storedMedicine.add(medicine);
                }
                Log.i(TAG, "onDataChange Read from Firebase: " + storedMedicine);
                Log.i(TAG, "onDataChange Size of list " + storedMedicine.size());


                networkDelegate.onSuccessGetMediciene(storedMedicine);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        Log.i(TAG, "getAllMedicine: " + storedMedicine.size());

    }

    @Override
    public void getAllMedicineOfHealthTaker(NetworkDelegate networkDelegate) {
        List<Medicine> storedMedicine = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserPojo user = snapshot.getValue(UserPojo.class);
                String accessUID = user.getAccessUID();
                if (accessUID.equals("NULL")) {
                    Log.i(TAG, "getAllMedicineOfHealthTaker: accessUID " + accessUID);
                    Log.i(TAG, "getAllMedicineOfHealthTaker: Error No data found ");
                } else {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(accessUID).child("medicineList");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                            storedMedicine.clear();
                            for (DataSnapshot snapshot1 : datasnapshot.getChildren()) {
                                Medicine medicine = snapshot1.getValue(Medicine.class);
                                storedMedicine.add(medicine);
                            }
                            Log.i(TAG, "onDataChange Read from Firebase: " + storedMedicine);
                            Log.i(TAG, "onDataChange Size of list " + storedMedicine.size());


                            networkDelegate.onSuccessGetMediciene(storedMedicine);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }

                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void updateMedicine(Medicine medicine) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList");
        HashMap hashMap = new HashMap();
        hashMap.put(medicine.getUid(), medicine); //add to firebase using the KEY
        reference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(getApplicationContext(), "Medicine have been updated", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void updateMedicineHealthTaker(Medicine medicine) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserPojo user = snapshot.getValue(UserPojo.class);
                String accessUID = user.getAccessUID();
                if (accessUID.equals("NULL")) {
                    Log.i(TAG, "updateHealthTaker: accessUID " + accessUID);
                    Log.i(TAG, "updateHealthTaker: Error No data found ");
                } else {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(accessUID).child("medicineList");
                    HashMap hashMap = new HashMap();
                    hashMap.put(medicine.getUid(), medicine); //add to firebase using the KEY
                    reference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Log.i(TAG, "updateHealthTaker: accessUID " + accessUID);
                            Log.i(TAG, "updateHealthTaker: Done");
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                        Log.i(TAG, pojo.getName());
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
    public void deleteMedicineHealthTaker(Medicine medicine) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserPojo user = snapshot.getValue(UserPojo.class);
                String accessUID = user.getAccessUID();
                if (accessUID.equals("NULL")) {
                    Log.i(TAG, "deleteMedicineHealthTaker: accessUID " + accessUID);
                    Log.i(TAG, "deleteMedicineHealthTaker: Error No data found ");
                } else {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(accessUID).child("medicineList");
                    Query query = reference.orderByChild("uid").equalTo(medicine.getUid());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                    Medicine pojo = snap.getValue(Medicine.class);
                                    Log.i(TAG, pojo.getName());
                                    snap.getRef().removeValue();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void sendReplyOnInvitationToRequester(DatabaseReference databaseReference) {
        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                UserPojo user = task.getResult().getValue(UserPojo.class);
                FirebaseDatabase.getInstance().getReference("Users").child(user.getAccessUID()).child("isYourRequestAccepted:").setValue("True");
                Log.i(TAG, "sendReplyOnInvitationToRequester: " + user.getUserName());
                Log.i(TAG, "sendReplyOnInvitationToRequester: " + user.getAccessUID());
                Log.i(TAG, "sendReplyOnInvitationToRequester: " + user.getRequesterName());
                Log.i(TAG, "sendReplyOnInvitationToRequester: " + user.getRequestReply());
            }
        });
    }

}
