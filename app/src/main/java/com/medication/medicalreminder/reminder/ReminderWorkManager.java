package com.medication.medicalreminder.reminder;

import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.displaymedicine.view.DisplayMedView;
import com.medication.medicalreminder.model.Medicine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReminderWorkManager extends Worker {
    static List<Medicine> medicineList= new ArrayList<>();
    static List<String> timesArrayList = new ArrayList<>();
    static List<Long> finalTimesList = new ArrayList<>();
    static int hour;
    static int minutes;
    static int day =22;
    static int month =3;
    static int year = 2022;
    static Context mContext;
    static long minimum;
    static WorkManager workManager = WorkManager.getInstance();

    String medicineName = getInputData().getString(ReminderActivity.NAME);
    String medicineUid = getInputData().getString(ReminderActivity.UID);
    int medicineLimit = getInputData().getInt(ReminderActivity.LIMIT,-1);
    int medicineAmount = getInputData().getInt(ReminderActivity.AMOUNT,-1);



    public ReminderWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    //sort
    //one time req


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public Result doWork() {

        Log.i("TAG", "inside do work");
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                displayNotification(medicineName);
                //sortAndCallOneTimeRequest();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ReminderActivity.findNextAlarm();
                }
            }
        });
        return Result.success();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void displayNotification(String keyword) {

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }


        Intent intent = new Intent(mContext, ReminderDialog.class);
        intent.putExtra(ReminderActivity.NAME,medicineName);
        intent.putExtra(ReminderActivity.UID, medicineUid);
        intent.putExtra(ReminderActivity.LIMIT, medicineLimit);
        intent.putExtra(ReminderActivity.AMOUNT,medicineAmount);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, FLAG_UPDATE_CURRENT| FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle("Have you taken your "+ keyword + " yet?")
                .setContentText("Don't forget to mark it as taken.")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_medicine);

        assert notificationManager != null;
        notificationManager.notify(1, builder.build());

    }

    public  static long convertDateAndTimeToFinalTimeInMills(String timeAndDate){
        // String timeAndDate = day +"-" + month+"-" + year+" " + hour +":" + minutes ; ;// /-03-2022 12:34";//
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
        System.out.println("Date in milli :: " + timeInMilliseconds);
        //long finalTime = timeInMilliseconds - currentTime;
        //Log.i("TAG", "final time " + finalTime);
        return  timeInMilliseconds;
    }

    public  static long convertFinalTime(String timeAndDate){
        // String timeAndDate = day +"-" + month+"-" + year+" " + hour +":" + minutes ; ;// /-03-2022 12:34";//
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
        System.out.println("Date in milli :: " + timeInMilliseconds);
        long finalTime = timeInMilliseconds - currentTime;
        Log.i("TAG", "final time " + finalTime);
        return  finalTime;
    }
}
