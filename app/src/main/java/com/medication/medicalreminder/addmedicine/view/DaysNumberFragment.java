package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class DaysNumberFragment extends Fragment {


    Medicine medicine;
    public DaysNumberFragment() {
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
        View view = inflater.inflate(R.layout.fragment_days_number, container, false);

        medicine = Medicine.getInstance();
        Button once = view.findViewById(R.id.onceDaily);
        once.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                medicine.setOften("Once daily");

                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.allTimeFragment);


            }
        });
        Button twice = view.findViewById(R.id.twiceDaily);
        twice.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                medicine.setOften("Twice Daily");

                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.allTimeFragment);
            }
        });
        Button threeTimes = view.findViewById(R.id.threeTimesAday);
        threeTimes.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                 medicine.setOften("3 times Daily");

                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.allTimeFragment);

            }
        });
        return view;
    }
}