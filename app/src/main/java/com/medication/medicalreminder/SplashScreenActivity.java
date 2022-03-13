package com.medication.medicalreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

      /*  Log.i("TAG", "onCreate: ");
        try {
            Log.i("TAG", "onCreate: TRY");
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.medication.medicalreminder",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("TAG:","-----------------------------------" +  Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("TAG", "onCreate: catch1" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.i("TAG", "onCreate: catch2");
        }
        Log.i("TAG", "onCreate: after");*/

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.i("TAG", "onCreate: SplashScreenActivity ----------------------------------------------------------------------------");
        Log.i("TAG", "onCreate: user.getUid() " + user.getUid());
        Log.i("TAG", "onCreate: user.getEmail() " + user.getEmail());
        Log.i("TAG", "onCreate: SplashScreenActivity ----------------------------------------------------------------------------");

   /*     DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserPojo userPojo = dataSnapshot.getValue(UserPojo.class);
                    Log.i("TAG", "onDataChange: userPojo/n userName : " + userPojo.getUserName() + "/tEmail : " + userPojo.getEmail()
                            + "/tPassword : " + userPojo.getPassword() + "/tAccessUID" + userPojo.getAccessUID());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Query query = reference.orderByChild("email").equalTo("test@gmail.com");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //   Log.i("TAG", "onDataChange: Query" + snapshot.getChildren());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //   Log.i("TAG", "onDataChange: dataSnapshot.getRef() : " + dataSnapshot.getRef());
                    //    Log.i("TAG", "onDataChange:  dataSnapshot.getKey() : " +  dataSnapshot.getKey());

                    UserPojo userPojo = dataSnapshot.getValue(UserPojo.class);
                    Log.i("TAG", "onDataChange: Query\n userName : " + userPojo.getUserName() + "\tEmail : " + userPojo.getEmail()
                            + "\tPassword : " + userPojo.getPassword() + "\tAccessUID : " + userPojo.getAccessUID());

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users/" + userPojo.getAccessUID());
                    getALLDATA(reference);
                    reference.child("userName").setValue("after edit");

                    // DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users/" + dataSnapshot.getKey()).child("EditKEY");
                    //    reference.setValue("First Try");


                    // dataSnapshot.getRef().child("accessUID").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                }

                //          Log.i("TAG", "onDataChange: snapshot.getChildren() " + snapshot.getChildren().);
                //     DataSnapshot dataSnapshot = snapshot.getChildren();
                //   UserPojo userPojo = dataSnapshot.getValue(UserPojo.class);
                //  Log.i("TAG", "onDataChange: userPojo.getAccessUID() + " + userPojo.getAccessUID()
                //    + "\tuserPojo.getEmail() " + userPojo.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Users");
*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", "run: you are in SplashScreenActivity going to loginActivity");
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);
    }

  /*  private void getALLDATA(DatabaseReference reference) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserPojo userPojo = snapshot.getValue(UserPojo.class);
                Log.i("TAG", "getALLDATA: getALLDATA\n userName : " + userPojo.getUserName() + "\tEmail : " + userPojo.getEmail()
                        + "\tPassword : " + userPojo.getPassword() + "\tAccessUID : " + userPojo.getAccessUID());
                //   Log.i("TAG", "getALLDATA: reference-------------------------------------------------------------------");
                //    Log.i("TAG", "getALLDATA: reference.child(\"email\")----------------------------------" + reference.child("email").get);
                //   Log.i("TAG", "getALLDATA: reference.child(\"password\")-------------------------------" + reference.child("password").getKey());
                //    Log.i("TAG", "getALLDATA: reference.child(\"userName\")-------------------------------" + reference.child("userName").getKey());
                //    Log.i("TAG", "getALLDATA: reference.child(\"accessUID\")------------------------------" + reference.child("accessUID").getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/
}