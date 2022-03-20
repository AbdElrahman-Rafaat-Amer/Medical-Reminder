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

public class WorkManagerRefill extends Worker  {
    public static final String DATA = "DATA" ;
    private AlertDialog alertDialog;
    Context mcontext;
    Medicine medicine;




    public WorkManagerRefill(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.mcontext = context;
    }

    //showNotification();
    WorkManagerRefill workManager;
    @NonNull
    @Override
    public Result doWork() {
        //Context context = getApplicationContext();
        // showAlertDialog(context);
        //  Data inputdata = getInputData();
        //  String data = inputdata.getString(WorkManager.DATA);
        //Log.i("TAG", "doWork: number" + data);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                //  Intent intent = new Intent(mcontext, Dialog.class);
                //  intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                // mcontext.startActivity(intent);
                //showAlertDialog();
            //    if(medicine.getRefillLimit()>= medicine.getMedLeft())
                displayNotification("hagora");
            }
        });

        return Result.success();
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

    }

        public void getAllMedicine(NetworkDelegate networkDelegate) {

            List<Medicine> storedMedicine = new ArrayList<>();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList");
            //.getReference("Users").child("medicineList");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    for (DataSnapshot snapshot1 : datasnapshot.getChildren()) {
                        Medicine medicine = snapshot1.getValue(Medicine.class);

                        Log.i("TAG", "ON WORK MANAGERRR: " + medicine.getMedLeft());
                    }
                    Log.i("TAG", "onDataChange Read from Firebase: " + storedMedicine);
                    Log.i("TAG", "onDataChange Size of list " + storedMedicine.size());


                    networkDelegate.onSuccessGetMediciene(storedMedicine);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }

            });
            Log.i("TAG", "getAllMedicine: " + storedMedicine.size());

        }




//       Intent intent = new Intent(mcontext, Dialog.class);
//          intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        //mcontext.startActivity(intent);


        //Intent intent = new Intent(getApplicationContext(), Dialog.class);

        //put together the PendingIntent



        // Intent notificationIntent = new Intent(mcontext, Dialog.class);

        //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
        //      | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //notificationIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 5);

        //PendingIntent pendingintent = PendingIntent.getActivity(mcontext, 0,
        //      notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

        //   notification.setLatestEventInfo(context, title, message, pendingintent);
        //notificationIntent.flags |= Notification.FLAG_AUTO_CANCEL;

        //notificationIntent.setAction("dummy_unique_action_identifyer" + 5);

    private AlertDialog showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Success, You can switch account now and see hos list", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You refused invitatio To take care of him", Toast.LENGTH_SHORT).show();
                alertDialog.hide();
            }
        });
        alertDialog = builder.create();

        return null;
    }
}