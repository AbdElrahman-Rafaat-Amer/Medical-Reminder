package com.medication.medicalreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;


public class ThirdFirstFragment extends Fragment {
    NumberPicker hour;
    NumberPicker min;
    NumberPicker PmAm;
    String TimeValueForThreeOne;
    private String ValueOfTimeFirstThird;

    public ThirdFirstFragment() {
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
        View view =inflater.inflate(R.layout.fragment_third_first, container, false);
        hour= view.findViewById(R.id.minsNumPickerThirdFirst);
        hour.setMaxValue(55);
        hour.setMinValue(00);
        hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForThreeOne= String.valueOf(newValue);
            }
        });
        min= view.findViewById(R.id.hourNumPickerThirdFirst);
        min.setMaxValue(12);
        min.setMinValue(1);
        min.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForThreeOne= TimeValueForThreeOne +String.valueOf(newValue);
            }
        });

        PmAm = view.findViewById(R.id.amNumPickerThirdFirst);
        PmAm.setMinValue(0);
        PmAm.setMaxValue(2);
        PmAm.setDisplayedValues( new String[] { "AM","PM" } );
        PmAm.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                TimeValueForThreeOne= TimeValueForThreeOne+ String.valueOf(newValue);
            }
        });
        Button buttonToTow = view.findViewById(R.id.btnNextTos);
        buttonToTow.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                Bundle bundle = new Bundle();
                // bundle.putInt(Value, Integer.parseInt("5"));
                bundle.putString(ValueOfTimeFirstThird,TimeValueForThreeOne);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.thirdSecondFragment,bundle);

            }
        });

        return  view;
    }
}