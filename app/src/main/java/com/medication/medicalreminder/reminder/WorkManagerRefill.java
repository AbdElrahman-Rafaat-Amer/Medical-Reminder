package com.medication.medicalreminder.reminder;


import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.medication.medicalreminder.R;

public class WorkManagerRefill extends Worker {
    public static final String DATA = "DATA";
    Context mcontext;
    String medicineName = getInputData().getString(ReminderActivity.NAME);
    String medicineUid = getInputData().getString(ReminderActivity.UID);
    int medicineLimit = getInputData().getInt(ReminderActivity.LIMIT,-1);
    int medicineAmount = getInputData().getInt(ReminderActivity.AMOUNT,-1);
    String refillTime = getInputData().getString(ReminderActivity.REFILLTIME);
    int image = getInputData().getInt("image",-1);




    public WorkManagerRefill(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.mcontext = context;
    }
    @NonNull
    @Override
    public Result doWork() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                displayNotification(medicineName);
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
        Intent intent = new Intent(mcontext, RefillDialog.class);
        intent.putExtra(ReminderActivity.NAME,medicineName);
        intent.putExtra(ReminderActivity.UID, medicineUid);
        intent.putExtra(ReminderActivity.LIMIT, medicineLimit);
        intent.putExtra(ReminderActivity.AMOUNT,medicineAmount);
        intent.putExtra(ReminderActivity.REFILLTIME,refillTime);
        intent.putExtra("image",image);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, FLAG_UPDATE_CURRENT| FLAG_IMMUTABLE);




        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle("Remember to Refill " + keyword)
                .setContentText("We Care About You!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_medicine);

        assert notificationManager != null;
        notificationManager.notify(1, builder.build());
    }
}
