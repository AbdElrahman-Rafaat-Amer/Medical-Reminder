package com.medication.medicalreminder;


import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.ArrayList;
import java.util.List;

public class WorkManagerRefill extends Worker {
    public static final String DATA = "DATA";
    Context mcontext;


    public WorkManagerRefill(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.mcontext = context;
    }

    WorkManagerRefill workManager;

    @NonNull
    @Override
    public Result doWork() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                displayNotification("hagora");
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

        assert notificationManager != null;
        notificationManager.notify(1, builder.build());

    }


}