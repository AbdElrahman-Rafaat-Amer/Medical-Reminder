package com.medication.medicalreminder.displaymedicine.view;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
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
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.medication.medicalreminder.R;

public class ScheduleWorkManger extends Worker {
    Context mcontext;
    private NotificationManagerCompat notificationManagerCompat;
    public ScheduleWorkManger(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.mcontext = context;
    }

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
                //  Intent intent = new Intent(mcontext, Dialog.class);
                //  intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                // mcontext.startActivity(intent);
                //showAlertDialog();
                //    if(medicine.getRefillLimit()>= medicine.getMedLeft())
                displayNotification("SNOOZE");
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


        Intent intent = new Intent(mcontext, Dialog.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle("title" + keyword)
                .setContentText("body")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_medicine8);
        //.setSound(Uri.parse("android.resource://"+ mcontext.getPackageName() + "/" + R.raw.alarm_clock_1));
        // .setSound(Uri.parse("android.resource://" + mcontext.getPackageName() + "/" + R.raw.alarm_clock_1));//*see note
        assert notificationManager != null;
        notificationManager.notify(1, builder.build());

    }}
