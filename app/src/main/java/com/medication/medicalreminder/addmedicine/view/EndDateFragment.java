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

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class EndDateFragment extends Fragment {
    Button endDate;
    DatePicker pickerEnd;
    Medicine medicine;

    public EndDateFragment() {
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
        return inflater.inflate(R.layout.fragment_end_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        medicine = Medicine.getInstance();
        pickerEnd = view.findViewById(R.id.lastDate);
        endDate = view.findViewById(R.id.lastButton);

        medicine.setImage(R.drawable.ic_launcher_background);

        endDate.setOnClickListener(btnView -> {

            medicine.setEndDate( pickerEnd.getDayOfMonth()+"-"+ (pickerEnd.getMonth() + 1)+"-"+pickerEnd.getYear());

            NavController navController = Navigation.findNavController(btnView);
            navController.navigate(R.id.saveFragment);



        });

    }
}