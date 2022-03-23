package com.medication.medicalreminder.reminder;


import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.healthtaker.view.EditHealthTakerRequest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotficationHealthTaker extends Worker  {
    Context mcontext;

    public NotficationHealthTaker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.mcontext = context;
    }

    //showNotification();
    NotficationHealthTaker workManager;
    @NonNull
    @Override
    public Result doWork() {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {

                displayNotification("You are invited to be a health taker");
                Toast.makeText(mcontext, "HELLO", Toast.LENGTH_SHORT).show();
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


        Intent intent = new Intent(mcontext, EditHealthTakerRequest.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, FLAG_UPDATE_CURRENT | FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle("Invitation Request")
                .setContentText(keyword)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_sick);
        assert notificationManager != null;
        notificationManager.notify(1, builder.build());

    }
}