package com.medication.medicalreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class TimeOnceFragment extends Fragment {
NumberPicker hour;
NumberPicker min;
NumberPicker PmAm;
String TimeValueForOnce;
String ValueOfTimeOnce;


    public TimeOnceFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_time_once, container, false);

        hour= view.findViewById(R.id.hourNumPicker);
        hour.setMaxValue(55);
        hour.setMinValue(00);
        hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForOnce= String.valueOf(newValue);
            }
        });
        min= view.findViewById(R.id.minsNumPicker);
        min.setMaxValue(12);
        min.setMinValue(1);
        min.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForOnce= TimeValueForOnce +String.valueOf(newValue);
            }
        });

        PmAm = view.findViewById(R.id.amNumPicker);
        PmAm.setMinValue(0);
        PmAm.setMaxValue(2);
        PmAm.setDisplayedValues( new String[] { "AM","PM" } );
        PmAm.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForOnce= TimeValueForOnce+ String.valueOf(newValue);
            }
        });


        Button buttonThree = view.findViewById(R.id.btnNextTos);
        buttonThree.setOnClickListener(btnView -> {
            if (getActivity() != null) {


                Bundle bundle = new Bundle();
                //bundle.putInt(String.valueOf(valueBoo),);
                bundle.putString(ValueOfTimeOnce,TimeValueForOnce);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.refillFragment,bundle);


            }
        });
        return  view;
    }
}