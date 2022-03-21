package com.medication.medicalreminder.addmedicine.view;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class RefilDateFragment extends Fragment implements View.OnClickListener {

    Button btnNext;
    TimePicker timePicker;
   // TextView textTitle, textDoseNum;
    String doseNum;
    String doseTimes = "";

    Medicine medicine;


    public RefilDateFragment() {
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
        return inflater.inflate(R.layout.fragment_refil_date, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timePicker = view.findViewById(R.id.time_picker_refill);
        btnNext = view.findViewById(R.id.btn_next_time_refill);

        timePicker.setHour(20);
        timePicker.setMinute(0);


        btnNext.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        setTime(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTime(View view){

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        String time = hour + ":" + minute;

        Medicine medicine = Medicine.getInstance();
        medicine.setTimeRefill(time);

        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.startDateFragment);

    }

}