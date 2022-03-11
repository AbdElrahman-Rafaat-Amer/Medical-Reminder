package com.medication.medicalreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;


public class StrengthFragment extends Fragment {

     private String value = "";
     private String valueS="";
    private NumberPicker numberPickerOne;
    private  NumberPicker numberPickerTow;
    private String valueBundel="";



    public StrengthFragment() {
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
        View view = inflater.inflate(R.layout.fragment_strength, container, false);
        numberPickerTow= view.findViewById(R.id.strengthNumPicker);
        numberPickerTow.setMaxValue(100);
        numberPickerTow.setMinValue(0);
        numberPickerTow.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
             value= String.valueOf(newValue);
            }
        });
        numberPickerOne = view.findViewById(R.id.doseNumPicker);
        numberPickerOne.setMinValue(0);
        numberPickerOne.setMaxValue(2);
        numberPickerOne.setDisplayedValues( new String[] { "g", "IU", "mcg","meq","mg" } );
       numberPickerOne.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

           @Override
           public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
               valueS= String.valueOf(newValue);
           }
       });



        Button button = view.findViewById(R.id.bttnNext);
        button.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                Bundle bundle = new Bundle();
               // bundle.putInt(String.valueOf(EXTRA_AGE),20);
                bundle.putString(valueBundel,value+""+valueS);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.dayilyFragment,bundle);


            }
        });
   return view;
}


}