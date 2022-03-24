package com.medication.medicalreminder.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.reminder.ReminderActivity;

import java.util.Locale;

public class RefillReminder extends AppCompatActivity {
    String medicineName;
    String uid;
    int medicineLimit;
    int medicineAmount;
    String refillTime;
    TextView newRefillAmount;
    Button btnDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refill_reminder);
         btnDone= findViewById(R.id.btnNewRefill);
         newRefillAmount= findViewById(R.id.newAmount);

        Intent intent = getIntent();
        newRefillAmount= findViewById(R.id.newAmount);
        medicineName = intent.getStringExtra(ReminderActivity.NAME);
        uid = intent.getStringExtra(ReminderActivity.UID);
        medicineLimit = intent.getIntExtra(ReminderActivity.LIMIT,-1);
        medicineAmount = intent.getIntExtra(ReminderActivity.AMOUNT,-1);
        refillTime = intent.getStringExtra(ReminderActivity.REFILLTIME);
        Medicine medicine = Medicine.getInstance();


       // medicine.setRefillLimit(Integer.parseInt(newRefillAmount.getText().toString()));


btnDone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList").child(uid);
        reference.child("medLeft").setValue(Integer.parseInt(newRefillAmount.getText().toString())+ medicineAmount);
        finish();


    }
});
//    btnDone.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList").child(uid);
//            reference.child("medLeft").setValue(newRefillAmount.getText());
//            Log.i("TAG", "onClick: "+ newRefillAmount.getText().toString());
//            finish();
//        }
//    });
    }

}