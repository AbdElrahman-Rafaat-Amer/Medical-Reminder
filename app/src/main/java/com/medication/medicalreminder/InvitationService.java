package com.medication.medicalreminder;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.model.UserPojo;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class InvitationService extends Service {
    private static final String TAG = "InvitationService";
    public static final String CHANNEL_ID = "id";
    private String name = "";
  /* Intent intent = new Intent(getApplicationContext(), InvitationService.class);
    startService(intent);*/


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        name = intent.getStringExtra("USERNAME");
      /*  Intent notifyIntent = new Intent(this, MainActivity.class);
        // Set the Activity to start in a new, empty task
        notifyIntent.setFlags(FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, FLAG_UPDATE_CURRENT
        );*/

        Runnable r = new Runnable() {
            @Override
            public void run() {

             //   readData(notifyPendingIntent);
             //   showNotification(notifyPendingIntent, name);
                displayNotification("TEST");
            }
        };
        Thread thread = new Thread(r);
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    public void showNotification(){
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        NotificationCompat.Builder notificationCompat= new NotificationCompat.Builder(getApplicationContext(),"14")
                .setSmallIcon(R.drawable.ic_medicine8)
                .setContentIntent(pendingIntent);
        //.setCustomContentView(R.layout.custom_dialog);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(4,notificationCompat.build());
    }


    private void displayNotification(String keyword) {
        Log.i(TAG, "displayNotification: ");
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }


        Intent intent = new Intent(getApplicationContext(), Dialog.class);
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

    }

    public void showNotification(PendingIntent intent, String senderName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "chanel";
            String desc = "Add Health Taker Request";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(desc);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Invitation")
                .setContentText(senderName + " Send you invitation to access his list of medicines and care him")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(intent)
                .setAutoCancel(true).build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, notification);
    }

}
