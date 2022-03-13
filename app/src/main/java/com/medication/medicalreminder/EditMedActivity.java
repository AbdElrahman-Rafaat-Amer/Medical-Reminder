package com.medication.medicalreminder;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.model.MedicinePojoo;

import java.util.Calendar;
import java.util.HashMap;

public class EditMedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "TAG";
    NumberPicker numberPicker;
    NumberPicker numberPickerDose;
    TextView enterAmountText, enterRemainText;
    CardView cardView;
    ImageView refillArrow, instructionArrow, strengthArrow, remindersArrow, iconsArrow, icon1, icon2, icon3, icon4,
            icon5, icon6, icon7, icon8;
    String choosenDose, medName, medAmount, medRemaining;
    int choosenStrength;
    EditText medNameEditText, medAmountEditText, medRemainingEditText;
    Button doneButton, setTimeButton;
    RadioGroup radioGroup;
    RadioButton radioButton;
    boolean pressed= false;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_med);
        initParams();
        initDosePicker();
        initNumberPicker();
        initSpinner();
        initRadioGroup();
        initIcons();
        collapse();

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedicinePojoo medicinePojoo = new MedicinePojoo();
                pressed=true;
                medName = medNameEditText.getText().toString().trim();
                medAmount = medAmountEditText.getText().toString().trim();
                medRemaining = medRemainingEditText.getText().toString().trim();
                if(medName!=null && medAmount != null && medRemaining != null){
                    //SEND HERE DATA TO FIREBASE
                    Toast.makeText(getApplicationContext(),medName + medAmount + medRemaining,Toast.LENGTH_LONG).show();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ghada").child("First User").child("First User Second Med");
                    HashMap hashMap = new HashMap();
                    hashMap.put("name", medName);

                    medicinePojoo.setMedicineName(medName);
                    reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getApplicationContext(), "Medicine have been updated",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                    /*Intent toDisplayIntent = new Intent();
                    toDisplayIntent.putExtra("medObject" , medicinePojoo);
                    startActivity(toDisplayIntent);*/


            }
        });

    }

    @Override
    public void onBackPressed() {
        if(!pressed){
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit without saving changes?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            EditMedActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }else {
            super.onBackPressed();
            finish();
        }
    }

    public void popTime(View v){
        int mHour;
        int mMinute;
        final Calendar calendar = Calendar.getInstance ();
        mHour = calendar.get ( Calendar.HOUR );
        mMinute = calendar.get ( Calendar.MINUTE );
        final TimePickerDialog timePickerDialog = new TimePickerDialog ( this, new TimePickerDialog.OnTimeSetListener () {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String status = "AM";
                if(hourOfDay > 11)
                {
                    // If the hour is greater than or equal to 12
                    // Then the current AM PM status is PM
                    status = "PM";
                }
                // Initialize a new variable to hold 12 hour format hour value
                int hour_of_12_hour_format;
                if(hourOfDay > 11){

                    // If the hour is greater than or equal to 12
                    // Then we subtract 12 from the hour to make it 12 hour format time
                    hour_of_12_hour_format = hourOfDay - 12;
                }
                else {
                    hour_of_12_hour_format = hourOfDay;
                }
                String time = /*hourOfDay*/hour_of_12_hour_format + ":" + minute+" "+ status/*AM_PM*/;
                setTimeButton.setText (time);
                Toast.makeText(getApplicationContext(),time,Toast.LENGTH_LONG).show();
            }
        }, mHour, mMinute, false );
        timePickerDialog.show ();
    }

    private void initParams(){
        numberPicker = findViewById(R.id.strengthNumPicker);
        numberPickerDose = findViewById(R.id.doseNumPicker);
        medNameEditText = findViewById(R.id.medNameEditText);
        doneButton = findViewById(R.id.done_button);
        medAmountEditText = findViewById(R.id.amountEditText);
        medRemainingEditText = findViewById(R.id.remainEditText);
        setTimeButton = findViewById(R.id.timeButton);
        radioGroup = findViewById(R.id.radioGroup);
        refillArrow = findViewById(R.id.refillArrow);
        instructionArrow = findViewById(R.id.instructions_arrow);
        strengthArrow = findViewById(R.id.setStrengthArrow);
        remindersArrow = findViewById(R.id.reminderTimesArrow);
        iconsArrow = findViewById(R.id.icons_arrow);
        enterAmountText = findViewById(R.id.enterAmountText);
        enterRemainText = findViewById(R.id.enterRemainText);
        cardView = findViewById(R.id.refillCardview);
        icon1 = findViewById(R.id.medIcon1);
        icon2 = findViewById(R.id.medIcon2);
        icon3 = findViewById(R.id.medIcon3);
        icon4 = findViewById(R.id.medIcon4);
        icon5 = findViewById(R.id.medIcon5);
        icon6 = findViewById(R.id.medIcon6);
        icon7 = findViewById(R.id.medIcon7);
        icon8 = findViewById(R.id.medIcon8);
        spinner = findViewById(R.id.reminders_spinner);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initNumberPicker(){
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                choosenStrength= newValue;
            }
        });


    }

    private void initDosePicker(){
        Doses.initDoses();
        numberPickerDose.setMaxValue(Doses.getDosesArrayList().size() - 1);
        numberPickerDose.setMinValue(0);
        numberPickerDose.setDisplayedValues(Doses.DosesNames());
        numberPickerDose.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                choosenDose = Doses.getDosesArrayList().get(newValue).getName();
                Toast.makeText(getApplicationContext(), choosenDose,Toast.LENGTH_SHORT).show();
                //doseTextView.setText();
            }
        });
    }

    private void initSpinner(){

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.reminder_times, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    private void collapse(){
        refillArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterAmountText.getVisibility() == View.GONE) {
                    refillArrow.setImageResource(R.drawable.arrow_up);
                    enterAmountText.setVisibility(View.VISIBLE);
                    enterRemainText.setVisibility(View.VISIBLE);
                    medAmountEditText.setVisibility(View.VISIBLE);
                    medRemainingEditText.setVisibility(View.VISIBLE);
                    //timeLabs.setText("");
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

                } else {
                    refillArrow.setImageResource(R.drawable.arrow_down);
                    enterAmountText.setVisibility(View.GONE);
                    enterRemainText.setVisibility(View.GONE);
                    medAmountEditText.setVisibility(View.GONE);
                    medRemainingEditText.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                }

            }
        });


        instructionArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioGroup.getVisibility() == View.GONE) {
                    instructionArrow.setImageResource(R.drawable.arrow_up);
                    radioGroup.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

                } else {
                    instructionArrow.setImageResource(R.drawable.arrow_down);
                    radioGroup.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                }

            }
        });

        strengthArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberPicker.getVisibility() == View.GONE) {
                    strengthArrow.setImageResource(R.drawable.arrow_up);
                    numberPicker.setVisibility(View.VISIBLE);
                    numberPickerDose.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

                } else {
                    strengthArrow.setImageResource(R.drawable.arrow_down);
                    numberPicker.setVisibility(View.GONE);
                    numberPickerDose.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                }

            }
        });

        remindersArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getVisibility() == View.GONE) {
                    remindersArrow.setImageResource(R.drawable.arrow_up);
                    spinner.setVisibility(View.VISIBLE);
                    setTimeButton.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

                } else {
                    remindersArrow.setImageResource(R.drawable.arrow_down);
                    spinner.setVisibility(View.GONE);
                    setTimeButton.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                }

            }
        });

        iconsArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (icon1.getVisibility() == View.GONE) {
                    iconsArrow.setImageResource(R.drawable.arrow_up);
                    icon1.setVisibility(View.VISIBLE);
                    icon2.setVisibility(View.VISIBLE);
                    icon3.setVisibility(View.VISIBLE);
                    icon4.setVisibility(View.VISIBLE);
                    icon5.setVisibility(View.VISIBLE);
                    icon6.setVisibility(View.VISIBLE);
                    icon7.setVisibility(View.VISIBLE);
                    icon8.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

                } else {
                    iconsArrow.setImageResource(R.drawable.arrow_down);
                    icon1.setVisibility(View.GONE);
                    icon2.setVisibility(View.GONE);
                    icon3.setVisibility(View.GONE);
                    icon4.setVisibility(View.GONE);
                    icon5.setVisibility(View.GONE);
                    icon6.setVisibility(View.GONE);
                    icon7.setVisibility(View.GONE);
                    icon8.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                }

            }
        });
    }

    private void initRadioGroup(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId)
                                                  {
                                                      radioButton =  findViewById(checkedId);
                                                      String checkedRadioButton = (String) radioButton.getText();
                                                      Toast.makeText(getApplicationContext(), checkedRadioButton, Toast.LENGTH_SHORT).show();
                                                  }
                                              }
        );
    }

    private void initIcons(){
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageId = R.id.medIcon1;
                Toast.makeText(getApplicationContext(), "icon 1 is choosed " + imageId,Toast.LENGTH_LONG).show();
            }
        });
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageId = R.id.medIcon2;
                Toast.makeText(getApplicationContext(), "icon 2 is choosed " + imageId,Toast.LENGTH_LONG).show();

            }
        });
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageId = R.id.medIcon3;
                Toast.makeText(getApplicationContext(), "icon 3 is choosed " + imageId,Toast.LENGTH_LONG).show();

            }
        });
        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageId = R.id.medIcon4;
                Toast.makeText(getApplicationContext(), "icon 4 is choosed " + imageId,Toast.LENGTH_LONG).show();

            }
        });
        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageId = R.id.medIcon5;
                Toast.makeText(getApplicationContext(), "icon 5 is choosed " + imageId,Toast.LENGTH_LONG).show();

            }
        });
        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageId = R.id.medIcon6;
                Toast.makeText(getApplicationContext(), "icon 6 is choosed " + imageId,Toast.LENGTH_LONG).show();

            }
        });
        icon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageId = R.id.medIcon8;
                Toast.makeText(getApplicationContext(), "icon 7 is choosed " + imageId,Toast.LENGTH_LONG).show();

            }
        });
        icon8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageId = R.id.medIcon8;
                Toast.makeText(getApplicationContext(), "icon 8 is choosed " + imageId,Toast.LENGTH_LONG).show();

            }
        });
    }

    //spinner selected item
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), selected,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

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

//Toolbar toolbar = findViewById(R.id.toolbar);
//setSupportActionBar(toolbar);

      /* public void popTime(View view) {
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
        }*/
