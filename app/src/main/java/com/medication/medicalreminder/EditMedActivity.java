package com.medication.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

public class EditMedActivity extends AppCompatActivity{
        private static final String TAG = "TAG";
        int hour, minute;
        Button timeButton;
        NumberPicker numberPicker;
        NumberPicker numberPickerDose;
        String[] doseStrengthArray;
        TextView timeLabs;
        CardView cardView;
        ImageView arrow;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_med);

            Spinner spinner = (Spinner) findViewById(R.id.remonderTimesSpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.reminder_times, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);

            timeButton = findViewById(R.id.timeButton);
            numberPicker = findViewById(R.id.strengthNumPicker);
            numberPickerDose = findViewById(R.id.doseNumPicker);

            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(100);



            doseStrengthArray = getResources().getStringArray(R.array.doseStrength);
            numberPickerDose.setMinValue(0);
            numberPickerDose.setMaxValue(4);
            numberPickerDose.setDisplayedValues(doseStrengthArray);


       /* // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://medical-reminder-8b9ee-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("This is Ghada from Android Studio!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.i(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

           /* timeLabs = (TextView) findViewById(R.id.timeLapse);
            cardView = findViewById(R.id.card_view);
            arrow = findViewById(R.id.arrow);*/

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);




       /* arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timeLabs.getVisibility() == View.GONE) {
                    arrow.setImageResource(R.drawable.arrow_up);
                    timeLabs.setVisibility(View.VISIBLE);
                    //timeLabs.setText("");
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

                } else {
                    arrow.setImageResource(R.drawable.arrow_down);
                    timeLabs.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                }

            }
        });*/


        }



        public void popTime(View view) {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
            {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                {
                    hour = selectedHour;
                    minute = selectedMinute;
                    timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                }
            };

            int style = AlertDialog.THEME_HOLO_DARK;

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, false);

            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();
        }
}
