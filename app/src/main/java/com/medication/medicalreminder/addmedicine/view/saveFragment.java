package com.medication.medicalreminder.addmedicine.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.medication.medicalreminder.MainActivity;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.addmedicine.persenter.MedicinePresenter;
import com.medication.medicalreminder.addmedicine.persenter.MedicinePresenterInterface;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;


public class saveFragment extends Fragment implements MedicineViewInterface {

    private Button save;
    private Medicine medicine;
    private MedicinePresenterInterface presenter;

    public saveFragment() {

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
        medicine = Medicine.getInstance();
        save = view.findViewById(R.id.lastSaveButton);
        presenter = new MedicinePresenter(Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext()), FirebaseOperation.getInstance()), this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setUid("");
                medicine.setId(0);             /*   Medicine object = new Medicine(0, medicine.getName(), medicine.getForm(), medicine.getStrength(), medicine.getReason(), medicine.getIsDaily(),
                        medicine.getOften(), medicine.getTime(), medicine.getStartDate(), medicine.getEndDate(), medicine.getMedLeft(), medicine.getRefillLimit(), medicine.getImage()
                        , medicine.getUid(), medicine.getTimeRefill());
         //       medicineToFireBase = object;
*/
                if (AddMActivity.typeofUser.equals("HT")) {
                    presenter.addMedicineHealthTaker(medicine);
                } else {
                    AddToFireBase(medicine);
                }
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });


    }


    @Override
    public void addMedicineHealthTaker(Medicine medicine) {
        presenter.addMedicineHealthTaker(medicine);

    }

    @Override
    public void AddToFireBase(Medicine medicine) {
        presenter.addMedToFireBase(medicine);
    }


}