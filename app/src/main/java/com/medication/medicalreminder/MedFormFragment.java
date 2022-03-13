package com.medication.medicalreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class MedFormFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final int EXTRA_AGE = 0;
    // create array of Strings
    // and store name of courses
    String[] items = new String[]{
            "Pill", "Solution",
            "Injection", "Powder",
            "Drops", "Inhaler","Other" };


    Spinner dropDown;

    public MedFormFragment() {
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
        View view =inflater.inflate(R.layout.fragment_med_form, container, false);
        Button buttonPill = view.findViewById(R.id.pill);
        buttonPill.setOnClickListener(btnView -> {
         if (getActivity() != null) {

             Bundle bundle = new Bundle();
             bundle.putInt(String.valueOf(EXTRA_AGE),20);
             NavController navController = Navigation.findNavController(btnView);
             navController.navigate(R.id.strengthFragment,bundle);


        }
        });
        Button buttonSol = view.findViewById(R.id.Solution);
        buttonSol.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                Bundle bundle = new Bundle();
                bundle.putInt(String.valueOf(EXTRA_AGE),20);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.strengthFragment,bundle);


            }
        });
        Button buttonInje = view.findViewById(R.id.injection);
        buttonInje.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                Bundle bundle = new Bundle();
                bundle.putInt(String.valueOf(EXTRA_AGE),20);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.strengthFragment,bundle);


            }
        });
        Button buttonPoweder = view.findViewById(R.id.Poweder);
        buttonPoweder.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                Bundle bundle = new Bundle();
                bundle.putInt(String.valueOf(EXTRA_AGE),20);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.strengthFragment,bundle);


            }
        });
        Button buttonDrop = view.findViewById(R.id.Drops);
        buttonDrop.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                Bundle bundle = new Bundle();
                bundle.putInt(String.valueOf(EXTRA_AGE),20);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.strengthFragment,bundle);


            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}