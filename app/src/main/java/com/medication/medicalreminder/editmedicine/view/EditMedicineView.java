package com.medication.medicalreminder.editmedicine.view;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import com.google.gson.Gson;
import com.medication.medicalreminder.Doses;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.displaymedicine.view.DisplayMedView;
import com.medication.medicalreminder.editmedicine.persenter.EditMedicinePresenter;
import com.medication.medicalreminder.editmedicine.persenter.EditMedicinePresenterInterface;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;

import java.util.Calendar;
import java.util.Locale;

public class EditMedicineView extends AppCompatActivity implements AdapterView.OnItemSelectedListener, EditMedicinesViewInterface {
    private static final String TAG = "TAG";
    public static final String JSON = "json";
    NumberPicker numberPicker, numberPickerDose;
    TextView enterAmountText, enterRemainText, startDate, endDate, refillTime, refillTimeText;
    CardView cardView;
    ImageView refillArrow, instructionArrow, strengthArrow, remindersArrow, iconsArrow, icon1, icon2, icon3, icon4,
            icon5, icon6, icon7, icon8;
    String medName;
    EditText medNameEditText, medAmountEditText, medRemainingEditText;
    Button doneButton, setTimeButton1, setTimeButton2, setTimeButton3;
    /*RadioGroup radioGroup;
    RadioButton radioButton;*/
    boolean pressed = false;
    Spinner spinner;
   // Medicine medicinePojoo;
    EditMedicinePresenterInterface presenterInterface;
    Medicine medicine;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_med);
        //medicinePojoo = Medicine.getInstance();
        initParams();
        initDosePicker();
        initNumberPicker();
        initSpinner();
        //initRadioGroup();
        //initIcons();
        collapse();


        presenterInterface = new EditMedicinePresenter(Repository.getInstance(getApplicationContext(), ConcreteLocalSource.getInstance(getApplicationContext()), FirebaseOperation.getInstance()),
                FirebaseOperation.getInstance());

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("json pojo");
         medicine = gson.fromJson(strObj, Medicine.class);
        //medicine.setId(1);

        refillTime.setText("15:00");
        medNameEditText.setText(medicine.getName());
       // medicinePojoo.setImage(medicine.getImage());
       // medicinePojoo.setId(medicine.getId());
       // medicinePojoo.setUid(medicine.getUid());
        Log.i(TAG, "time is " + medicine.getTime());

      String[] timesArray = medicine.getTime().split(",");
        Log.i(TAG, "time is " + timesArray[0]);
        if(timesArray.length == 1){
            setTimeButton1.setText(timesArray[0]);
        }
        if(timesArray.length == 2){
            setTimeButton1.setText(timesArray[0]);
            setTimeButton2.setText(timesArray[1]);
        }
        if(timesArray.length == 3){
            setTimeButton1.setText(timesArray[0]);
            setTimeButton2.setText(timesArray[1]);
            setTimeButton3.setText(timesArray[2]);
        }

        selectSpinnerValue(spinner, medicine.getOften());
        medAmountEditText.setText(String.valueOf(medicine.getMedLeft()));
        medRemainingEditText.setText(String.valueOf(medicine.getRefillLimit()));
       // medicinePojoo.setOften(medicine.getOften());
       // medicinePojoo.setStrength(medicine.getStrength());
        startDate.setText(medicine.getStartDate());
        endDate.setText(medicine.getEndDate());
        chooseIcon();


        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectStartDate();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectEndtDate();
            }
        });

        refillTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popTime4();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed = true;
                medName = medNameEditText.getText().toString().trim();
                medicine.setName(medName);
                medicine.setMedLeft(Integer.parseInt(medAmountEditText.getText().toString().trim()));
                medicine.setRefillLimit(Integer.parseInt(medRemainingEditText.getText().toString().trim()));
               /* medicinePojoo.setName(medName);
                medicinePojoo.setMedLeft(Integer.parseInt(medAmountEditText.getText().toString().trim()));
                medicinePojoo.setRefillLimit(Integer.parseInt(medRemainingEditText.getText().toString().trim()));*/
                //Log.i("TAG" , Integer.parseInt(medNameEditText.getText().toString()));
          /*      timesArray[0] = setTimeButton1.getText().toString();
                timesArray[1] = setTimeButton2.getText().toString();
                timesArray[2] = setTimeButton3.getText().toString();
                String time="";
                for(int i=0;i<timesArray.length;i++){
                time  += timesArray[i] + ", ";
                }
                medicinePojoo.setTime(time);
                Log.i("TAG", timesArray.toString());*/
               /* medicinePojoo.setStartDate(startDate.getText().toString().trim());
                medicinePojoo.setEndDate(endDate.getText().toString().trim());*/
                String time="";
                if(timesArray.length == 1){
                    timesArray[0]= setTimeButton1.getText().toString();
                   // time+= timesArray[0];
                }
                if(timesArray.length == 2){
                    timesArray[0] = setTimeButton1.getText().toString();
                    timesArray[1] = setTimeButton2.getText().toString();
                   // time+=timesArray[0] + "," + timesArray[1];
                }
                if(timesArray.length== 3){
                    timesArray[0] = setTimeButton1.getText().toString();
                    timesArray[1] = setTimeButton2.getText().toString();
                    timesArray[2] = setTimeButton1.getText().toString();
                   // time+=timesArray[0] + "," + timesArray[1] + "," + timesArray[2];
                }
               /* if(medicine.getOften().equalsIgnoreCase("once daily")){
                    timesArray[0]= setTimeButton1.getText().toString();
                    time+= timesArray[0];
                }
                if(medicine.getOften().equalsIgnoreCase("twice daily")){
                    timesArray[0] = setTimeButton1.getText().toString();
                    timesArray[1] = setTimeButton2.getText().toString();
                    time+=timesArray[0] + "," + timesArray[1];
                }
                if(medicine.getOften().equalsIgnoreCase("3 timed daily")){
                    timesArray[0] = setTimeButton1.getText().toString();
                    timesArray[1] = setTimeButton2.getText().toString();
                    timesArray[2] = setTimeButton1.getText().toString();
                    time+=timesArray[0] + "," + timesArray[1] + "," + timesArray[2];
                }*/

                for(int i=0;i<timesArray.length;i++){
                    time  += timesArray[i] + ",";}

                medicine.setTime(time);
                medicine.setStartDate(startDate.getText().toString().trim());
                medicine.setEndDate(endDate.getText().toString().trim());


                if (isValidName() && isValidAmount() && isValidRefillLimit()) {
                    /*if (isNetworkAvailable()) {*///update in FIREBASE
                        Toast.makeText(getApplicationContext(), "you are online", Toast.LENGTH_SHORT).show();
                        /*updateFireBase(medicinePojoo);*/
                    updateFireBase(medicine);
                    /*} else {*///update in ROOM

                        Toast.makeText(getApplicationContext(), "you are offline", Toast.LENGTH_SHORT).show();
                        /*updateMedicines(medicinePojoo);*/
                    updateMedicines(medicine);
                    //}

                    Toast.makeText(getApplicationContext(), "Medicine have been updated", Toast.LENGTH_LONG).show();
                    /*Intent toDisplayIntent = new Intent(getApplicationContext(), DisplayMedView.class);
                    Gson gson = new Gson();
                    *//*String json = gson.toJson(medicinePojoo);*//*
                    String json = gson.toJson(medicine);
                    toDisplayIntent.putExtra(JSON, json);
                    startActivity(toDisplayIntent);*/
                    finish();

                    Toast.makeText(getApplicationContext(), medName, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (!pressed) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit without saving changes?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            EditMedicineView.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            super.onBackPressed();
            finish();
        }
    }

    public void popTime(View v) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                setTimeButton1.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        //int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
        /*int mHour;
        int mMinute;
        final Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String status = "AM";
                if (hourOfDay > 11) {
                    // If the hour is greater than or equal to 12
                    // Then the current AM PM status is PM
                    status = "PM";
                }
                // Initialize a new variable to hold 12 hour format hour value
                int hour_of_12_hour_format;
                if (hourOfDay > 11) {

                    // If the hour is greater than or equal to 12
                    // Then we subtract 12 from the hour to make it 12 hour format time
                    hour_of_12_hour_format = hourOfDay - 12;
                } else {
                    hour_of_12_hour_format = hourOfDay;
                }
                String time = *//*hourOfDay*//*hour_of_12_hour_format + ":" + minute + " " + status*//*AM_PM*//*;
                setTimeButton1.setText(time);
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();*/
    }

    public void popTime2(View v) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                setTimeButton2.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        //int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void popTime3(View v) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                setTimeButton3.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        //int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }


    public void popTime4() {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                refillTime.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        //int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private void initParams() {
        numberPicker = findViewById(R.id.strengthNumPicker);
        numberPickerDose = findViewById(R.id.doseNumPicker);
        medNameEditText = findViewById(R.id.medNameEditText);
        doneButton = findViewById(R.id.done_button);
        medAmountEditText = findViewById(R.id.amountEditText);
        medRemainingEditText = findViewById(R.id.remainEditText);
        setTimeButton1 = findViewById(R.id.timeButton1);
        setTimeButton2 = findViewById(R.id.timeButton2);
        setTimeButton3 = findViewById(R.id.timeButton3);
       // radioGroup = findViewById(R.id.radioGroup);
        refillArrow = findViewById(R.id.refillArrow);
        //instructionArrow = findViewById(R.id.instructions_arrow);
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
        startDate = findViewById(R.id.start_date_text);
        endDate = findViewById(R.id.end_date_text);
        refillTime  =findViewById(R.id.refill_time);
        refillTimeText = findViewById(R.id.refill_time_text);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initNumberPicker() {
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
               /* medicinePojoo.setStrengthNum(newValue);
                medicinePojoo.setStrength(medicinePojoo.getStrengthNum() + " " + medicinePojoo.getStrengthDose());*/
                medicine.setStrengthNum(newValue);
                medicine.setStrength(medicine.getStrengthNum() + " " + medicine.getStrengthDose());
                Log.i("TAG", "" + medicine.getStrength());
                Toast.makeText(getApplicationContext(), "" + medicine.getStrength(), Toast.LENGTH_SHORT).show();
                //choosenStrength= newValue;
            }
        });


    }

    private void initDosePicker() {
        Doses.initDoses();
        numberPickerDose.setMaxValue(Doses.getDosesArrayList().size() - 1);
        numberPickerDose.setMinValue(0);
        numberPickerDose.setDisplayedValues(Doses.DosesNames());
        numberPickerDose.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                /*medicinePojoo.setStrengthDose(Doses.getDosesArrayList().get(newValue).getName());
                medicinePojoo.setStrength(medicinePojoo.getStrengthNum() + " " + medicinePojoo.getStrengthDose());*/
                medicine.setStrengthDose(Doses.getDosesArrayList().get(newValue).getName());
                medicine.setStrength(medicine.getStrengthNum() + " " + medicine.getStrengthDose());
                Toast.makeText(getApplicationContext(), "" + medicine.getStrength(), Toast.LENGTH_SHORT).show();
                //doseTextView.setText();
            }
        });
    }

    private void initSpinner() {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.reminder_times, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    private void selectSpinnerValue(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(myString)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    private void collapse() {
        refillArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterAmountText.getVisibility() == View.GONE) {
                    refillArrow.setImageResource(R.drawable.arrow_up);
                    enterAmountText.setVisibility(View.VISIBLE);
                    enterRemainText.setVisibility(View.VISIBLE);
                    medAmountEditText.setVisibility(View.VISIBLE);
                    medRemainingEditText.setVisibility(View.VISIBLE);
                    refillTime.setVisibility(View.VISIBLE);
                    refillTimeText.setVisibility(View.VISIBLE);
                    //timeLabs.setText("");
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

                } else {
                    refillArrow.setImageResource(R.drawable.arrow_down);
                    enterAmountText.setVisibility(View.GONE);
                    enterRemainText.setVisibility(View.GONE);
                    medAmountEditText.setVisibility(View.GONE);
                    medRemainingEditText.setVisibility(View.GONE);
                    refillTime.setVisibility(View.GONE);
                    refillTimeText.setVisibility(View.GONE);
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                }

            }
        });


        /*instructionArrow.setOnClickListener(new View.OnClickListener() {
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
        });*/

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

                /*if (spinner.getVisibility() == View.GONE) {
                    remindersArrow.setImageResource(R.drawable.arrow_up);
                    spinner.setVisibility(View.VISIBLE);
                    startDate.setVisibility(View.VISIBLE);
                    endDate.setVisibility(View.VISIBLE);
                    if (medicinePojoo.getOften().equals("Once daily")) {
                        setTimeButton1.setVisibility(View.VISIBLE);
                        setTimeButton2.setVisibility(View.GONE);
                        setTimeButton3.setVisibility(View.GONE);
                    } else if (medicinePojoo.getOften().equals("Twice daily")) {
                        setTimeButton1.setVisibility(View.VISIBLE);
                        setTimeButton2.setVisibility(View.VISIBLE);
                        setTimeButton3.setVisibility(View.GONE);
                    } else if (medicinePojoo.getOften().equals("3 Times a day")) {
                        setTimeButton1.setVisibility(View.VISIBLE);
                        setTimeButton2.setVisibility(View.VISIBLE);
                        setTimeButton3.setVisibility(View.VISIBLE);
                    }*/
                if (spinner.getVisibility() == View.GONE) {
                    remindersArrow.setImageResource(R.drawable.arrow_up);
                    spinner.setVisibility(View.VISIBLE);
                    startDate.setVisibility(View.VISIBLE);
                    endDate.setVisibility(View.VISIBLE);
                    if (medicine.getOften().equalsIgnoreCase("Once daily")) {
                        setTimeButton1.setVisibility(View.VISIBLE);
                        setTimeButton2.setVisibility(View.GONE);
                        setTimeButton3.setVisibility(View.GONE);
                    } else if (medicine.getOften().equalsIgnoreCase("Twice daily")) {
                        setTimeButton1.setVisibility(View.VISIBLE);
                        setTimeButton2.setVisibility(View.VISIBLE);
                        setTimeButton3.setVisibility(View.GONE);
                    } else if (medicine.getOften().equalsIgnoreCase("3 Times Daily")) {
                        setTimeButton1.setVisibility(View.VISIBLE);
                        setTimeButton2.setVisibility(View.VISIBLE);
                        setTimeButton3.setVisibility(View.VISIBLE);
                    }
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

                } else {
                    remindersArrow.setImageResource(R.drawable.arrow_down);
                    spinner.setVisibility(View.GONE);
                    startDate.setVisibility(View.GONE);
                    endDate.setVisibility(View.GONE);
                    setTimeButton1.setVisibility(View.GONE);
                    setTimeButton2.setVisibility(View.GONE);
                    setTimeButton3.setVisibility(View.GONE);
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
/*
    private void initRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                      radioButton = findViewById(checkedId);
                                                      String checkedRadioButton = (String) radioButton.getText();
                                                      Toast.makeText(getApplicationContext(), checkedRadioButton, Toast.LENGTH_SHORT).show();
                                                  }
                                              }
        );
    }*/

    private void chooseIcon() {

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*medicinePojoo.setImage(R.id.medIcon1);*/
                medicine.setImage(R.id.medIcon1);
            }
        });
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //medicinePojoo.setImage(R.id.medIcon2);
                medicine.setImage(R.id.medIcon2);
            }
        });
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //medicinePojoo.setImage(R.id.medIcon3);
                medicine.setImage(R.id.medIcon3);

            }
        });
        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // medicinePojoo.setImage(R.id.medIcon4);
                medicine.setImage(R.id.medIcon4);

            }
        });
        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   medicinePojoo.setImage(R.id.medIcon5);
                medicine.setImage(R.id.medIcon5);
            }
        });
        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  medicinePojoo.setImage(R.id.medIcon6);
                medicine.setImage(R.id.medIcon6);
            }
        });
        icon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //medicinePojoo.setImage(R.id.medIcon7);
                medicine.setImage(R.id.medIcon7);
            }
        });
        icon8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   medicinePojoo.setImage(R.id.medIcon8);
                medicine.setImage(R.id.medIcon8);
            }
        });
    }

    //spinner selected item
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

       /* medicinePojoo.setOften(adapterView.getItemAtPosition(i).toString());
        Toast.makeText(adapterView.getContext(), medicinePojoo.getOften(), Toast.LENGTH_SHORT).show();
        if (medicinePojoo.getOften().equals("Once daily")) {
            setTimeButton1.setVisibility(View.VISIBLE);
            setTimeButton2.setVisibility(View.GONE);
            setTimeButton3.setVisibility(View.GONE);
        } else if (medicinePojoo.getOften().equals("Twice daily")) {
            setTimeButton1.setVisibility(View.VISIBLE);
            setTimeButton2.setVisibility(View.VISIBLE);
            setTimeButton3.setVisibility(View.GONE);
        } else if (medicinePojoo.getOften().equals("3 Times a day")) {
            setTimeButton1.setVisibility(View.VISIBLE);
            setTimeButton2.setVisibility(View.VISIBLE);
            setTimeButton3.setVisibility(View.VISIBLE);
        }*/
        medicine.setOften(adapterView.getItemAtPosition(i).toString());
        Toast.makeText(adapterView.getContext(), medicine.getOften(), Toast.LENGTH_SHORT).show();
        if (medicine.getOften().equalsIgnoreCase("Once daily")) {
            setTimeButton1.setVisibility(View.VISIBLE);
            setTimeButton2.setVisibility(View.GONE);
            setTimeButton3.setVisibility(View.GONE);
        } else if (medicine.getOften().equalsIgnoreCase("Twice daily")) {
            setTimeButton1.setVisibility(View.VISIBLE);
            setTimeButton2.setVisibility(View.VISIBLE);
            setTimeButton3.setVisibility(View.GONE);
        } else if (medicine.getOften().equalsIgnoreCase("3 Times Daily")) {
            setTimeButton1.setVisibility(View.VISIBLE);
            setTimeButton2.setVisibility(View.VISIBLE);
            setTimeButton3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void selectStartDate() {                                                                     //this method performs the date picker task
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                startDate.setText(day + "-" + (month + 1) + "-" + year);                             //sets the selected date as test for button
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void selectEndtDate() {                                                                     //this method performs the date picker task
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                endDate.setText(day + "-" + (month + 1) + "-" + year);                             //sets the selected date as test for button
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private boolean isValidName() {
        String nameInput = medNameEditText.getText().toString().trim();
        boolean isValidate;
        if (nameInput.isEmpty()) {
            medNameEditText.setError(getResources().getString(R.string.name_error));
            isValidate = false;
        } else {
            medNameEditText.setError(null);
            isValidate = true;
        }
        return isValidate;
    }

    private boolean isValidAmount() {
        String amountInput = medAmountEditText.getText().toString().trim();
        boolean isValidate;
        if (amountInput.isEmpty()) {
            medAmountEditText.setError(getResources().getString(R.string.amount_error));
            isValidate = false;
        } else {
            medAmountEditText.setError(null);
            isValidate = true;
        }
        return isValidate;
    }

    private boolean isValidRefillLimit() {
        String refillInput = medRemainingEditText.getText().toString().trim();
        boolean isValidate;
        if (refillInput.isEmpty()) {
            medRemainingEditText.setError(getResources().getString(R.string.refill_error));
            isValidate = false;
        } else {
            medRemainingEditText.setError(null);
            isValidate = true;
        }
        return isValidate;
    }

    @Override
    public void updateMedicines(Medicine medicine) {
        Log.i("TAG", "inside edit view activity");
        presenterInterface.update(medicine);
    }

    @Override
    public void updateFireBase(Medicine medicine) {
        presenterInterface.updateFireBase(medicine);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;

        }

        return isAvailable;
    }

}