package com.medication.medicalreminder.displaymedicine.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.displaymedicine.persenter.DisplayMedPresenter;
import com.medication.medicalreminder.displaymedicine.persenter.DisplayMedPresenterInterface;
import com.medication.medicalreminder.editmedicine.view.EditMedicineView;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DisplayMedView extends AppCompatActivity implements DisplayMedInterface{
    private static final String TAG = "TAG";
    ImageButton editBtn, deleteBtn, backButton;
    TextView medName, reminder1, reminder2, reminder3, leftMed, refillLimit, medStrength, medForm;
    ImageView medIcon;
    DisplayMedPresenterInterface presenterInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_med);
        medName = findViewById(R.id.medName);
        medIcon = findViewById(R.id.medIcon);
        deleteBtn = findViewById(R.id.deleteButton);
        reminder1 = findViewById(R.id.reminderTime1);
        reminder2 = findViewById(R.id.reminderTime2);
        reminder3 = findViewById(R.id.reminderTime3);
        leftMed = findViewById(R.id.pillsLeftNum);
        refillLimit = findViewById(R.id.remindToRefillNum);
        medStrength = findViewById(R.id.medStrength);
        backButton = findViewById(R.id.backButton);
        editBtn = findViewById(R.id.editButton);
        medForm = findViewById(R.id.form_text);

        presenterInterface = new DisplayMedPresenter(Repository.getInstance(getApplicationContext(), ConcreteLocalSource.getInstance(getApplicationContext()),FirebaseOperation.getInstance()),
                FirebaseOperation.getInstance());

        Gson gson = new Gson();
        Medicine medicine = gson.fromJson(getIntent().getStringExtra(EditMedicineView.JSON), Medicine.class);

        //Medicine medicine = (Medicine) getIntent().getSerializableExtra("med pojo");
        medName.setText(medicine.getName());
        medIcon.setImageResource(medicine.getImage());
        medForm.setText(medicine.getForm());
       String times = medicine.getTime();
        Log.i("TAG", "time is " + times);

        //String[] timesArray = medicine.getTime().split(",");
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(times, ",");
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        Log.i(TAG, "time is " + tokens.get(0));
        if(tokens.size() == 1){
            reminder1.setText(tokens.get(0));
        }
        if(tokens.size() == 2){
            reminder1.setText(tokens.get(0));
            reminder2.setText(tokens.get(1));
        }
        if(tokens.size()== 3){
            reminder1.setText(tokens.get(0));
            reminder2.setText(tokens.get(1));
            reminder3.setText(tokens.get(2));
        }
        leftMed.setText(String.valueOf(medicine.getMedLeft()));
        refillLimit.setText(String.valueOf(medicine.getRefillLimit()));
        medStrength.setText(medicine.getStrength());



        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(getApplicationContext(), EditMedicineView.class);
                Gson gson = new Gson();
                String json = gson.toJson(medicine);
                editIntent.putExtra("json pojo", json);
                //editIntent.putExtra("medicine", medicine);
                startActivity(editIntent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DisplayMedView.this)
                        .setMessage("Are you sure you want to delete this medicine?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //-----room------///
                                deleteMedicine(medicine);
                                //----firebase---///
                                deleteFromFirebase(medicine);
                                finish();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        presenterInterface.deleteMedicine(medicine);
    }

    @Override
    public void deleteFromFirebase(Medicine medicine) {
        presenterInterface.deleteFromFirebase(medicine);
    }
}