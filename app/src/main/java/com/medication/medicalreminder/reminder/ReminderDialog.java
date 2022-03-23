package com.medication.medicalreminder.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.medication.medicalreminder.R;

import org.w3c.dom.Text;

public class ReminderDialog extends AppCompatActivity {
    Button takeButton;
    TextView medicineNameText;
    ImageView medicineIcon;

    Intent incomeIntent = getIntent();
    String name = incomeIntent.getExtras().getString(ReminderActivity.NAME);
    int icon = Integer.parseInt(incomeIntent.getStringExtra(ReminderActivity.ICON));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_dialog);
        takeButton= findViewById(R.id.take_button);
        medicineNameText = findViewById(R.id.medName);
        medicineIcon = findViewById(R.id.med_icon);

        medicineIcon.setImageResource(icon);
        medicineNameText.setText(name);

        this.setFinishOnTouchOutside(true);
        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
