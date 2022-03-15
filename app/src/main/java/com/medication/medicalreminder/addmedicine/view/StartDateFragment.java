package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class StartDateFragment extends Fragment {
   Button startDate;
   DatePicker pickerStart;
   Medicine medicine;



    public StartDateFragment() {
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
        return inflater.inflate(R.layout.fragment_start_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        medicine = Medicine.getInstance();
        pickerStart= view.findViewById(R.id.startDate);
        startDate = view.findViewById(R.id.startButton);
        startDate.setOnClickListener(btnView -> {

            medicine.setStartDate( pickerStart.getDayOfMonth()+"-"+ (pickerStart.getMonth() + 1)+"-"+pickerStart.getYear());

            NavController navController = Navigation.findNavController(btnView);
            navController.navigate(R.id.endDateFragment);



        });
    }
}