package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;

public class RefillNumberFragment extends Fragment {
   Medicine medicine;
   Button refillButton ;
   EditText refilled;


    public RefillNumberFragment() {
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
        View view =inflater.inflate(R.layout.fragment_refill_number, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         refilled = view.findViewById(R.id.refillEdit);
         medicine = Medicine.getInstance();
         refillButton = view.findViewById(R.id.refillNumberButton);
         refillButton.setOnClickListener(btnView -> {

            medicine.setRefillLimit(Integer.parseInt(refilled.getText().toString()));
            NavController navController = Navigation.findNavController(btnView);
            navController.navigate(R.id.refilDateFragment);



        });
    }
}