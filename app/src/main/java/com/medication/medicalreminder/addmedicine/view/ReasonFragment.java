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


public class ReasonFragment extends Fragment {
    Button reasonButton;
    EditText reasonText;
    Medicine medicine;


    public ReasonFragment() {
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
        return inflater.inflate(R.layout.fragment_reason2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reasonText  = view.findViewById(R.id.reasonText);
        reasonButton = view.findViewById(R.id.reason);
        medicine = Medicine.getInstance();


        reasonButton .setOnClickListener(btnView -> {
            if (getActivity() != null) {
                medicine.setReason(reasonText.getText().toString());
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.dayilyFragment);


            }
        });


    }
}