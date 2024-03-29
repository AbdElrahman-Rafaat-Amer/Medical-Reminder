package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class pillsNumberFragment extends Fragment {
    Medicine medicine;
    EditText pillNumber;

    public pillsNumberFragment() {
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
       // medication=Medication.getInstance();
        View view =inflater.inflate(R.layout.fragment_pills_number, container, false);

        medicine = Medicine.getInstance();

         pillNumber = view.findViewById(R.id.pillsEdit);
        //String pillLeft = pillNumber.getText().toString().trim();
        //int finalValue=Integer.parseInt(pillLeft);
       // medicine.setMedLeft(Integer.parseInt(pillNumber.getText().toString()));

        Button pillNumberBtn = view.findViewById(R.id.pillNumberButton);
        pillNumberBtn.setOnClickListener(btnView -> {
            if ( isValidateNumber()) {

                medicine.setMedLeft(Integer.parseInt(pillNumber.getText().toString()));
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.refillNumberFragment);
            }


        });
        return view;
    }


    private boolean isValidateNumber() {
        String pillsInput = pillNumber.getText().toString().trim();
        boolean isValidate;
        if (pillsInput.isEmpty() ) {
            pillNumber.setError("Enter your Amount of pills");
            isValidate = false;
        } else {

            pillNumber.setError(null);
            isValidate = true;
        }

        return isValidate;
    }
}