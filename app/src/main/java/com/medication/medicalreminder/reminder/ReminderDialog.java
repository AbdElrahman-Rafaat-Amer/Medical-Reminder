package com.medication.medicalreminder.reminder;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.displaymedicine.view.ScheduleWorkManger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReminderDialog extends AppCompatActivity {
    Button takeButton, snoozeButton;
    String medicineName;
    String uid;
    TextView fiveBtn, tenBtn, fifteenBtn, thirtyBtn, sixtyBtn, hundredBtn, medName;
    ImageView medIcon;
    int medicineLimit;
    int medicineAmount;
    String refillTime;
    int snoozeTime;
    int medicineIcon;
    public static final String NAME = "NAME";
    public static final String UID = "uid";
    public static final String LIMIT = "LIMIT";
    public static final String AMOUNT = "AMOUNT";
    public static final String REFILLTIME = "REFILLTIME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_dialog);
        takeButton = findViewById(R.id.take_button);
        snoozeButton = findViewById(R.id.snooze_button);
        medName = findViewById(R.id.med_name);
        medIcon = findViewById(R.id.med_icon);
        this.setFinishOnTouchOutside(true);
        Intent intent = getIntent();
        medicineName = intent.getStringExtra(ReminderActivity.NAME);
        medicineIcon = intent.getIntExtra("image",-1);
        medName.setText(medicineName);
        medIcon.setImageResource(medicineIcon);
        uid = intent.getStringExtra(ReminderActivity.UID);
        medicineLimit = intent.getIntExtra(ReminderActivity.LIMIT,-1);
        medicineAmount = intent.getIntExtra(ReminderActivity.AMOUNT,-1);
        refillTime = intent.getStringExtra(ReminderActivity.REFILLTIME);
        Log.i("TAG", "onCreate: Refill time" + refillTime);
        Log.i("TAG", "onCreate: DialogRefill name" + medicineName);
        Log.i("TAG", "onCreate: DialogRefill limiit " + medicineLimit);

        takeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                medicineAmount--;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList").child(uid);
                reference.child("medLeft").setValue(medicineAmount);
                Log.i("TAG", "onClick: "+medicineAmount);
                if(medicineLimit>= medicineAmount)
                {
                    refillDialog(refillTime);
                }
                finish();
            }
        });

        snoozeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(ReminderDialog.this);
                dialog.setContentView(R.layout.snooze_dialog);

                fiveBtn = dialog.findViewById(R.id.five_button);
                tenBtn = dialog.findViewById(R.id.ten_button);
                fifteenBtn = dialog.findViewById(R.id.fifteen_button);
                thirtyBtn = dialog.findViewById(R.id.thirty_button);
                sixtyBtn = dialog.findViewById(R.id.sixty_button);
                hundredBtn = dialog.findViewById(R.id.hundred_button);

                dialog.show();


                fiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snoozeTime = 5;
                        Toast.makeText(ReminderDialog.this, "Your medicine is snoozed for "+ snoozeTime , Toast.LENGTH_SHORT).show();
                        OneTimeWorkRequest snoozeRequest = new OneTimeWorkRequest.Builder(ScheduleWorkManger.class)
                                .setInitialDelay(snoozeTime, TimeUnit.MINUTES)
                                .build();
                        WorkManager.getInstance(getApplicationContext()).enqueue(snoozeRequest);
                        dialog.dismiss();
                    }
                });
                tenBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snoozeTime = 10;
                        Toast.makeText(ReminderDialog.this, "Your medicine is snoozed for "+ snoozeTime , Toast.LENGTH_SHORT).show();
                        OneTimeWorkRequest snoozeRequest = new OneTimeWorkRequest.Builder(ScheduleWorkManger.class)
                                .setInitialDelay(snoozeTime, TimeUnit.MINUTES)
                                .build();
                        WorkManager.getInstance(getApplicationContext()).enqueue(snoozeRequest);
                        dialog.dismiss();
                    }
                });
                fifteenBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snoozeTime = 15;
                        Toast.makeText(ReminderDialog.this, "Your medicine is snoozed for "+ snoozeTime , Toast.LENGTH_SHORT).show();
                        OneTimeWorkRequest snoozeRequest = new OneTimeWorkRequest.Builder(ScheduleWorkManger.class)
                                .setInitialDelay(snoozeTime, TimeUnit.MINUTES)
                                .build();
                        WorkManager.getInstance(getApplicationContext()).enqueue(snoozeRequest);
                        dialog.dismiss();
                    }
                });
                thirtyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snoozeTime = 30;
                        Toast.makeText(ReminderDialog.this, "Your medicine is snoozed for "+ snoozeTime , Toast.LENGTH_SHORT).show();
                        OneTimeWorkRequest snoozeRequest = new OneTimeWorkRequest.Builder(ScheduleWorkManger.class)
                                .setInitialDelay(snoozeTime, TimeUnit.MINUTES)
                                .build();
                        WorkManager.getInstance(getApplicationContext()).enqueue(snoozeRequest);
                        dialog.dismiss();
                    }
                });
                sixtyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snoozeTime = 60;
                        Toast.makeText(ReminderDialog.this, "Your medicine is snoozed for "+ snoozeTime , Toast.LENGTH_SHORT).show();
                        OneTimeWorkRequest snoozeRequest = new OneTimeWorkRequest.Builder(ScheduleWorkManger.class)
                                .setInitialDelay(snoozeTime, TimeUnit.MINUTES)
                                .build();
                        WorkManager.getInstance(getApplicationContext()).enqueue(snoozeRequest);
                        dialog.dismiss();
                    }
                });
                hundredBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snoozeTime = 120;
                        Toast.makeText(ReminderDialog.this, "Your medicine is snoozed for "+ snoozeTime , Toast.LENGTH_SHORT).show();
                        OneTimeWorkRequest snoozeRequest = new OneTimeWorkRequest.Builder(ScheduleWorkManger.class)
                                .setInitialDelay(snoozeTime, TimeUnit.MINUTES)
                                .build();
                        WorkManager.getInstance(getApplicationContext()).enqueue(snoozeRequest);
                        dialog.dismiss();
                    }
                });

                Log.i("TAG", "snooze time is" + snoozeTime);

            }

        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void refillDialog(String refillTime) {
        int x = LocalDateTime.now().getDayOfMonth();
        int y = LocalDateTime.now().getMonthValue();
        int z = LocalDateTime.now().getYear();
        Log.i("TAG", "refillDialog: day " + x);
        Log.i("TAG", "refillDialog:month " + y);
        Log.i("TAG", "refillDialog: year" + z);
        String[] datee = refillTime.split(":");
        int day =x;
        int month =y;
        int year = z;
        int hour = Integer.parseInt(datee[0]);
        int minutes = Integer.parseInt(datee[1]);
        String timeAndDate = day +"-" + month+"-" + year+" " + hour +":" + minutes ;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        long timeInMilliseconds;
        Date mDate = null;
        try {
            mDate = sdf.parse(timeAndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("TAG", "Date m datee: " + mDate);

        timeInMilliseconds = mDate.getTime();
        System.out.println("Date in milli refill :: " + timeInMilliseconds);
        long finalTime = timeInMilliseconds - currentTime;
        Log.i("TAG", "final time refill " + finalTime);

        Data data;
        data = new Data.Builder()
                .putString(WorkManagerRefill.DATA, "batoot")
                .putString(NAME,medicineName)
                .putString(UID,uid)
                .putInt(LIMIT,medicineLimit)
                .putInt(AMOUNT,medicineAmount)
                .putString(REFILLTIME,refillTime)
                .build();
        OneTimeWorkRequest reminderRequest = new OneTimeWorkRequest.Builder(WorkManagerRefill.class)
                .setInitialDelay(finalTime, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build();
        WorkManager.getInstance(this).enqueue(reminderRequest);
    }
}


/*
package com.medication.medicalreminder.reminder;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ReminderDialog extends AppCompatActivity {
    Button takeButton;
    String medicineName;
    String uid;
    int medicineLimit;
    int medicineAmount;
    String refillTime;
    public static final String NAME = "NAME";
    public static final String UID = "uid";
    public static final String LIMIT = "LIMIT";
    public static final String AMOUNT = "AMOUNT";
    public static final String REFILLTIME = "REFILLTIME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_dialog);
        takeButton = findViewById(R.id.take_button);
        this.setFinishOnTouchOutside(true);
        Intent intent = getIntent();
        medicineName = intent.getStringExtra(ReminderActivity.NAME);
        uid = intent.getStringExtra(ReminderActivity.UID);
        medicineLimit = intent.getIntExtra(ReminderActivity.LIMIT,-1);
        medicineAmount = intent.getIntExtra(ReminderActivity.AMOUNT,-1);
        refillTime = intent.getStringExtra(ReminderActivity.REFILLTIME);
        Log.i("TAG", "onCreate: Refill time" + refillTime);
        Log.i("TAG", "onCreate: DialogRefill name" + medicineName);
        Log.i("TAG", "onCreate: DialogRefill limiit " + medicineLimit);




        takeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                medicineAmount--;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList").child(uid);
                reference.child("medLeft").setValue(medicineAmount);
                Log.i("TAG", "onClick: "+medicineAmount);
                if(medicineLimit>= medicineAmount)
                {
                    refillDialog(refillTime);
                }
                finish();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void refillDialog(String refillTime) {
        int x = LocalDateTime.now().getDayOfMonth();
        int y = LocalDateTime.now().getMonthValue();
        int z = LocalDateTime.now().getYear();
        Log.i("TAG", "refillDialog: day " + x);
        Log.i("TAG", "refillDialog:month " + y);
        Log.i("TAG", "refillDialog: year" + z);
        String[] datee = refillTime.split(":");
        int day =x;
        int month =y;
        int year = z;
        int hour = Integer.parseInt(datee[0]);
        int minutes = Integer.parseInt(datee[1]);
        String timeAndDate = day +"-" + month+"-" + year+" " + hour +":" + minutes ;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        long timeInMilliseconds;
        Date mDate = null;
        try {
            mDate = sdf.parse(timeAndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("TAG", "Date m datee: " + mDate);

        timeInMilliseconds = mDate.getTime();
        System.out.println("Date in milli refill :: " + timeInMilliseconds);
        long finalTime = timeInMilliseconds - currentTime;
        Log.i("TAG", "final time refill " + finalTime);

        Data data;
        data = new Data.Builder()
                .putString(WorkManagerRefill.DATA, "batoot")
                .putString(NAME,medicineName)
                .putString(UID,uid)
                .putInt(LIMIT,medicineLimit)
                .putInt(AMOUNT,medicineAmount)
                .putString(REFILLTIME,refillTime)
                .build();
        OneTimeWorkRequest reminderRequest = new OneTimeWorkRequest.Builder(WorkManagerRefill.class)
                .setInitialDelay(finalTime, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build();
        androidx.work.WorkManager.getInstance(this).enqueue(reminderRequest);
    }
}
*/
