package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class ImageFragment extends Fragment {
   ImageButton medIcon1;
   ImageButton medIcon2;
   ImageButton medIcon3;
   ImageButton medIcon4;
   ImageButton medIcon5;
   ImageButton medIcon6;
   ImageButton medIcon7;
   ImageButton medIcon8;
   Medicine medicine;


    public ImageFragment() {
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
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medIcon1= view.findViewById(R.id.medIcon1);
        medIcon2= view.findViewById(R.id.medIcon2);
        medIcon3= view.findViewById(R.id.medIcon3);
        medIcon4= view.findViewById(R.id.medIcon4);
        medIcon5= view.findViewById(R.id.medIcon5);
        medIcon6= view.findViewById(R.id.medIcon6);
        medIcon7= view.findViewById(R.id.medIcon7);
        medIcon8= view.findViewById(R.id.medIcon8);

        medIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setImage(R.id.medIcon1);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.id.medIcon2);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.id.medIcon3);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.id.medIcon4);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setImage(R.id.medIcon5);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);

            }
        });
        medIcon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setImage(R.id.medIcon6);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.id.medIcon7);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.id.medIcon8);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
    }
}