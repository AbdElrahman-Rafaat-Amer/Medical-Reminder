package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Doses;
import com.medication.medicalreminder.model.Medicine;


public class StrengthFragment extends Fragment {

     private String value = "";
     private String valueS="";
    private NumberPicker numberPickerOne;
    private  NumberPicker numberPickerTow;
    private String valueBundel="";
    Medicine medicine;



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

        medicine= medicine.getInstance();

        numberPickerTow= view.findViewById(R.id.strengthNumPicker);
        numberPickerTow.setMaxValue(100);
        numberPickerTow.setMinValue(0);
        numberPickerTow.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                // value= String.valueOf(newValue);
                medicine.setStrengthNum(newValue);
            }
        });
        Doses.initDoses();
        numberPickerOne = view.findViewById(R.id.doseNumPicker);
        numberPickerOne.setMaxValue(Doses.getDosesArrayList().size() - 1);
        numberPickerOne.setMinValue(0);
        numberPickerOne.setDisplayedValues(Doses.DosesNames());
        numberPickerOne.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                medicine.setStrengthDose(Doses.getDosesArrayList().get(newValue).getName());
                medicine.setStrength(medicine.getStrengthNum() + " " + medicine.getStrengthDose());
            }
        });



        Button strength = view.findViewById(R.id.bttnNext);
        strength.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.reasonFragment);


            }
        });
        return view;
    }


}