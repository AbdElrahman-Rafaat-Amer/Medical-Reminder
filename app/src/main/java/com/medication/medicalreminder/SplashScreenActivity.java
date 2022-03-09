package com.medication.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    /*    Log.i("TAG", "onCreate: ");
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


        Log.i("TAG", "onCreate: agter");*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", "run: you are in SplashScreenActivity going to loginActivity");
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);
    }
}