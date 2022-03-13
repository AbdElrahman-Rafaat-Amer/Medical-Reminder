package com.medication.medicalreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class DaysNumberFragment extends Fragment {


    private static final String EXTRA_AGE = "";
    private String numberOfDays = "";

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

        Button once = view.findViewById(R.id.onceDaily);
        once.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                String onceText= (String) once.getText();
                Bundle bundle = new Bundle();
                //bundle.putInt(String.valueOf(valueBoo),);
                bundle.putString(numberOfDays,onceText);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.timeOnceFragment,bundle);


            }
        });
        Button twice = view.findViewById(R.id.twiceDaily);
        twice.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                String twiceText= (String) twice.getText();
                Bundle bundle = new Bundle();
                //bundle.putInt(String.valueOf(valueBoo),);
                bundle.putString(numberOfDays,twiceText);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.timeTowFirstFragment,bundle);


            }
        });
        Button threeTimes = view.findViewById(R.id.threeTimesAday);
        threeTimes.setOnClickListener(btnView -> {
            if (getActivity() != null) {


                String threeTimesTextText= (String) threeTimes.getText();
                Bundle bundle = new Bundle();
                //bundle.putInt(String.valueOf(valueBoo),);
                bundle.putString(numberOfDays,threeTimesTextText);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.thirdFirstFragment,bundle);

            }
        });
        return view;
    }
}