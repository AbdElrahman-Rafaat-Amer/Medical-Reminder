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


public class timeTowFirstFragment extends Fragment {
    NumberPicker hour;
    NumberPicker min;
    NumberPicker PmAm;
    String TimeValueForTowOne;
    private String Value;


    public timeTowFirstFragment() {
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
       View view= inflater.inflate(R.layout.fragment_time_tow_first, container, false);
        hour= view.findViewById(R.id.hourNumPickerTowOne);
        hour.setMaxValue(55);
        hour.setMinValue(00);
        hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForTowOne= String.valueOf(newValue);
            }
        });
        min= view.findViewById(R.id.minsNumPickerTowOne);
        min.setMaxValue(12);
        min.setMinValue(1);
        min.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForTowOne= TimeValueForTowOne +String.valueOf(newValue);
            }
        });

        PmAm = view.findViewById(R.id.amNumPickerTowOne);
        PmAm.setMinValue(0);
        PmAm.setMaxValue(2);
        PmAm.setDisplayedValues( new String[] { "AM","PM" } );
        PmAm.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForTowOne= TimeValueForTowOne+ String.valueOf(newValue);
            }
        });
        Button buttonToTow = view.findViewById(R.id.btnNextTos);
        buttonToTow.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                Bundle bundle = new Bundle();
               // bundle.putInt(Value, Integer.parseInt("5"));
                bundle.putString(Value,TimeValueForTowOne);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.timeTowSecondFragment,bundle);

            }
        });

        return view;
    }
}