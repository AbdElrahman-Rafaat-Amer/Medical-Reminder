package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EndDateFragment extends Fragment {
    Button endDate;
    DatePicker pickerEnd;
    Medicine medicine;
    TextView dateErrorText;

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
        dateErrorText = view.findViewById(R.id.date_error);


        endDate.setOnClickListener(btnView -> {
            String end = pickerEnd.getDayOfMonth() + "-" + (pickerEnd.getMonth() + 1) + "-" + pickerEnd.getYear();
            if (isValidEndDate(medicine.getStartDate(), end)) {

                medicine.setEndDate(pickerEnd.getDayOfMonth() + "-" + (pickerEnd.getMonth() + 1) + "-" + pickerEnd.getYear());

                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.imageFragment);
            }


        });

    }

    private boolean isValidEndDate(String start, String end) {
        boolean isValidate;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Date date = null;
        Date date2 = null;
        try {
            date = format.parse(start);
            System.out.println("parsed date" + date);
            date2 = format.parse(end);
            System.out.println("parsed date" + date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date2.compareTo(date) <= 0) {
            dateErrorText.setVisibility(View.VISIBLE);

            isValidate = false;
        } else {
            endDate.setText(end);
            medicine.setEndDate(end);

            medicine.setStartDate(start);
            dateErrorText.setVisibility(View.GONE);
            isValidate = true;
        }
        return isValidate;
    }
}