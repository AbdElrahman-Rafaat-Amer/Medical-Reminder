package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class ImageFragment extends Fragment {
    ImageView medIcon1;
    ImageView medIcon2;
    ImageView medIcon3;
    ImageView medIcon4;
    ImageView medIcon5;
    ImageView medIcon6;
    ImageView medIcon7;
    ImageView medIcon8;
    Medicine medicine;

    Button save;


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
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        medIcon1= view.findViewById(R.id.medIcon11);
        medIcon2= view.findViewById(R.id.medIcon22);
        medIcon3= view.findViewById(R.id.medIcon33);
        medIcon4= view.findViewById(R.id.medIcon44);
        medIcon5= view.findViewById(R.id.medIcon55);
        medIcon6= view.findViewById(R.id.medIcon66);
        medIcon7= view.findViewById(R.id.medIcon77);
        medIcon8= view.findViewById(R.id.medIcon88);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        medicine= Medicine.getInstance();
        medIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setImage(R.drawable.ic_medicine1);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.drawable.ic_medicine2);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.drawable.ic_medicine3);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.drawable.ic_medicine4);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setImage(R.drawable.ic_medicine5);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);

            }
        });
        medIcon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setImage(R.drawable.ic_medicine6);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.drawable.ic_medicine7);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });
        medIcon8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setImage(R.drawable.ic_medicine8);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.saveFragment);
            }
        });

    }
}