package com.medication.medicalreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class dayilyFragment extends Fragment {
 String valueBoo= "";

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
        Button buttonYes = view.findViewById(R.id.yesButton);
        buttonYes.setOnClickListener(btnView -> {
            if (getActivity() != null) {
                  String yesNo = (String) buttonYes.getText();
                Bundle bundle = new Bundle();
                //bundle.putInt(String.valueOf(valueBoo),);
                bundle.putString(valueBoo,yesNo);
                NavController navController = Navigation.findNavController(btnView);
                navController.navigate(R.id.daysNumberFragment,bundle);


            }
        });
        return view;
    }
}