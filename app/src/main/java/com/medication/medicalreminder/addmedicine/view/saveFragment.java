package com.medication.medicalreminder.addmedicine.view;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.medication.medicalreminder.R;

import com.medication.medicalreminder.addmedicine.persenter.MedicinePresenter;
import com.medication.medicalreminder.addmedicine.persenter.MedicinePresenterInterface;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;


public class saveFragment extends Fragment implements MedicineViewInterface {

    Button save ;
    Medicine medicine;
    MedicinePresenterInterface presenter;
    Medicine medicineToFireBase;

    public saveFragment() {
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
        return inflater.inflate(R.layout.fragment_save, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medicine= Medicine.getInstance();
        save = view.findViewById(R.id.lastSaveButton);
        presenter = new MedicinePresenter(Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext()), FirebaseOperation.getInstance()),this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setUid("");
                Medicine object = new Medicine( 0,medicine.getName(),medicine.getForm(),medicine.getStrength(), medicine.getReason(), medicine.getIsDaily(),
                        medicine.getOften(), medicine.getTime(), medicine.getStartDate(), medicine.getEndDate(), medicine.getMedLeft(), medicine.getRefillLimit(), medicine.getImage()
                  ,medicine.getUid(),medicine.getTimeRefill());
                medicineToFireBase = object;

                addMed(object);

              // MedicinePojoo medicinePojo = new MedicinePojoo("aya","last",7);



                //AddToFireBase(medicineToFireBase);
            }
        });


    }


    @Override
    public void addMed(Medicine medicine) {

        presenter.addMedicine(medicine);

    }

    @Override
    public void AddToFireBase(Medicine medicine) {
        presenter.addMedToFireBase(medicine);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void sendId(long id) {
        medicineToFireBase.setId(Math.toIntExact( id));
        Log.i("TAG","med name inside send id " + medicineToFireBase.getName());
        Log.i("TAG"," inside send id is " + id);
        //Toast.makeText(getActivity(), "this is the return id " + id, Toast.LENGTH_LONG).show();
        AddToFireBase(medicineToFireBase);
    }



}