package com.medication.medicalreminder.reminder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.medication.medicalreminder.R;

public class ReminderDialog extends AppCompatActivity {
    Button takeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_dialog);
        takeButton= findViewById(R.id.take_button);
        this.setFinishOnTouchOutside(true);
        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
