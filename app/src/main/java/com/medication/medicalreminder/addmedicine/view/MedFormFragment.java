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


public class MedFormFragment extends Fragment  {

    private static final int EXTRA_AGE = 0;

    String[] items = new String[]{
            "Pill", "Solution",
            "Injection", "Powder",
            "Drops", "Inhaler","Other" };

    Medicine medicine ;



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
        medicine= Medicine.getInstance();

        Button buttonForm = view.findViewById(R.id.pill);

       // buttonForm.setText(medicine.getName());

        buttonForm.setOnClickListener(btnView -> {
         if (getActivity() != null) {

             medicine.setForm(buttonForm.getText().toString());
             NavController navController = Navigation.findNavController(btnView);
             navController.navigate(R.id.strengthFragment);


        }
        });


        Button buttonSol = view.findViewById(R.id.Solution);
        buttonSol.setOnClickListener(btnView -> {
            if (getActivity() != null) {

               medicine.setForm(buttonSol.getText().toString());
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.strengthFragment);


            }
        });
        Button buttonInje = view.findViewById(R.id.injection);
        buttonInje.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                medicine.setForm(buttonInje.getText().toString());
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.strengthFragment);


            }
        });
        Button buttonPoweder = view.findViewById(R.id.Poweder);
        buttonPoweder.setOnClickListener(btnView -> {
            if (getActivity() != null) {
                medicine.setForm(buttonPoweder.getText().toString());
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.strengthFragment);


            }
        });
        Button buttonDrop = view.findViewById(R.id.Drops);
        buttonDrop.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                medicine.setForm(buttonDrop.getText().toString());
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.strengthFragment);


            }
        });

        return view;
    }


}