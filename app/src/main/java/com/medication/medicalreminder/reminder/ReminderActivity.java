package com.medication.medicalreminder.reminder;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.medication.medicalreminder.model.Medicine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReminderActivity {
    public static final String NAME = "NAME";
    public static final String UID = "uid";
    public static final String LIMIT = "LIMIT";
    public static final String AMOUNT = "AMOUNT";
    public static final String ICON = "ICON";
    static Context mContext;

    public static List<Medicine> getMedicineArrayList() {
        return medicineArrayList;
    }

    public static void setMedicineArrayList(List<Medicine> medicineArrayList) {
        ReminderActivity.medicineArrayList = medicineArrayList;
    }

    static List<Medicine> medicineArrayList = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void findNextAlarm() {

        WorkManager.getInstance().cancelAllWorkByTag("alarms");
        long currentTime = Calendar.getInstance().getTimeInMillis();
        Log.i("TAG","current time" + currentTime);
        long smallest = currentTime;
        String medicineName = null;
        String uid = null;
        int refillLimit=0;
        int medAmount=0;
        int medicineIcon = 0;
        String scheduledAlarm = null;
        LocalDate localDate=LocalDate.now();
        Log.e("MedList","med list" +  medicineArrayList);

        for (int i = 0; i < medicineArrayList.size(); i++) {
            for (int alarm = 0; alarm < medicineArrayList.get(i).getTime().split(",").length; alarm++) {

                Log.i("TAG", "len after split is " + medicineArrayList.get(i).getTime().split(",").length);
                Log.i("TAG", "medicineList.get(i).getTime().split(,)[alarm] "  + medicineArrayList.get(i).getTime().split(",")[alarm]);

                String alarmTime = localDate.getDayOfMonth()+ "-" + localDate.getMonthValue() + "-" + localDate.getYear() + " " +
                        medicineArrayList.get(i).getTime().split(",")[alarm].split(":")[0] + ":" + medicineArrayList.get(i).getTime().split(",")[alarm].split(":")[1];

                Log.i("TAG", "In side findRest "+alarmTime);
                long timeInMills = ReminderWorkManager.convertDateAndTimeToFinalTimeInMills(alarmTime);
                Log.i("TAG", "In side findRestMills "+timeInMills+"  "+alarmTime+" "+(timeInMills-currentTime));
                Log.i("TAG","current time" + currentTime);
                if (timeInMills - currentTime >= 0 && timeInMills - currentTime < smallest) {
                    smallest = timeInMills - currentTime;
                    scheduledAlarm = alarmTime;
                    Log.i("TAG", "FinfResut If "+scheduledAlarm);
                    medicineName = medicineArrayList.get(i).getName();
                    uid = medicineArrayList.get(i).getUid();
                    refillLimit = medicineArrayList.get(i).getRefillLimit();
                    medAmount = medicineArrayList.get(i).getMedLeft();
                    medicineIcon = medicineArrayList.get(i).getImage();

                }

            }
        }

        if (scheduledAlarm != null) {
            Log.i("TAG", "In side smallest reminder method");
            long finalTime = ReminderWorkManager.convertFinalTime(scheduledAlarm);

            Data data = new Data.Builder()
                    .putString(NAME,medicineName)
                    .putString(UID,uid)
                    .putInt(LIMIT,refillLimit)
                    .putInt(AMOUNT,medAmount)
                    .putInt(ICON,medicineIcon)
                    .build();
            OneTimeWorkRequest reminderRequest = new OneTimeWorkRequest.Builder(ReminderWorkManager.class)
                    .setInitialDelay(finalTime, TimeUnit.MILLISECONDS)
                    .setInputData(data)
                    .addTag("alarms")
                    .build();
            androidx.work.WorkManager.getInstance(mContext).enqueue(reminderRequest);

        }

        //
    }
}
