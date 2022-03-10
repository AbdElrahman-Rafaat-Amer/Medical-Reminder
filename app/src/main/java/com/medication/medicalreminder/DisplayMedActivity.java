package com.medication.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DisplayMedActivity extends AppCompatActivity {
    private CardView cardView;
    private ImageView playButton, pauseButton, stopButton, timeLabsButton, arrow;
    private TextView timeView, timeViewMS, timeLabs;
    private int timer = 0, ms = 0, lapCount = 0, hours, minutes, seconds;
    private String MS, time;
    private boolean running;
    ImageButton editBtn;
    TextView medName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_med);
        medName = findViewById(R.id.medName);

        String medicine = getIntent().getStringExtra("medicine");
        Log.i("TAG", "medicine " + medicine);
        medName.setText(medicine);
        /*MedicinePojoo medicinePojoo;
        try {
            JSONObject jsonObject = new JSONObject(medicine);

            Log.i("TAG", "json obj" + jsonObject);
            Log.i("TAG", "json obj string" + jsonObject.toString());
            medicinePojoo = (MedicinePojoo) jsonObject.get("medObject");



        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        editBtn = findViewById(R.id.editButton);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(getApplicationContext(), EditMedActivity.class);
                startActivity(editIntent);
            }
        });


    }
}