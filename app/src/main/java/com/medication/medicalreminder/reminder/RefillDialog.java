package com.medication.medicalreminder.reminder;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.reminder.RefillReminder;
import com.medication.medicalreminder.reminder.ReminderActivity;
import com.medication.medicalreminder.reminder.ReminderDialog;

public class RefillDialog extends AppCompatActivity {
    Button btnOk;
    Button btn2;
    String medicineName;
    String uid;
    int medicineLimit;
    int medicineAmount;
    String refillTime;
    Button btnRefill;
    Button newRefillAmount;
    Button btnDone;
    EditText newAmountEditText;
    TextView textViewName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        btnOk= findViewById(R.id.btnOk);
        btnRefill= findViewById(R.id.btnRefill);
        textViewName=findViewById(R.id.textViewName);

        this.setFinishOnTouchOutside(true);
        Intent intent = getIntent();
        medicineName = intent.getStringExtra(ReminderActivity.NAME);
        uid = intent.getStringExtra(ReminderActivity.UID);
        medicineLimit = intent.getIntExtra(ReminderActivity.LIMIT,-1);
        medicineAmount = intent.getIntExtra(ReminderActivity.AMOUNT,-1);
        refillTime = intent.getStringExtra(ReminderActivity.REFILLTIME);
        textViewName.setText("Your Medicine"+medicineName +"has" + medicineAmount + "Pills Left");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnRefill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Dialog dialog = new Dialog(getApplicationContext());
//                dialog.setContentView(R.layout.activity_dialog);
//               newRefillAmount= dialog.findViewById(R.id.btnRefill);
//                btnDone= findViewById(R.id.btnNewRefill);
//                newAmountEditText=findViewById(R.id.newAmount);
//                dialog.show();
//                newRefillAmount.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View view) {
//                       DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList").child(uid);
//                       reference.child("medLeft").setValue(Integer.parseInt(newRefillAmount.getText().toString())+ medicineAmount);
//                   }/
//               });
//
//               btnDone.setOnClickListener(new View.OnClickListener() {
//                 @Override
//               public void onClick(View view) {
//                        finish();
//                  }
//               });

                       Intent intent = new Intent(getApplicationContext(), RefillReminder.class);
                intent.putExtra(ReminderActivity.NAME,medicineName);
                intent.putExtra(ReminderActivity.UID, uid);
                intent.putExtra(ReminderActivity.LIMIT, medicineLimit);
                intent.putExtra(ReminderActivity.AMOUNT,medicineAmount);
                intent.putExtra(ReminderActivity.REFILLTIME,refillTime);
                 startActivity(intent);
            }
        });
    }
}