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


public class dayilyFragment extends Fragment {

 Medicine medicine;

    public dayilyFragment() {
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

       View view =inflater.inflate(R.layout.fragment_dayily, container, false);

          medicine = medicine.getInstance();

          Button isDaily = view.findViewById(R.id.yesButton);

         // buttonYes.setText(medicine.getStrength());
          isDaily.setOnClickListener(btnView -> {
            if (getActivity() != null) {

                medicine.setIsDaily(isDaily.getText().toString());

                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.daysNumberFragment);


            }
        });
        return view;
    }
}