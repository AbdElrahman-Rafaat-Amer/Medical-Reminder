package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
        presenter = new MedicinePresenter(Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext()), FirebaseOperation.getInstance()));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Medicine object = new Medicine( 0,medicine.getName(),medicine.getForm(),medicine.getStrength(), medicine.getReason(), medicine.getIsDaily(),
                        medicine.getOften(), medicine.getTime(), medicine.getStartDate(), medicine.getEndDate(), medicine.getMedLeft(), medicine.getRefillLimit(), medicine.getImage()
                );
                addMed(object);
              // MedicinePojoo medicinePojo = new MedicinePojoo("aya","last",7);
                Medicine medicineToFireBase = object;
                AddToFireBase(medicineToFireBase);
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


}