package com.medication.medicalreminder.displaymedicine.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.displaymedicine.persenter.MedicinePresenter;
import com.medication.medicalreminder.displaymedicine.persenter.MedicinePresenterInterface;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;

import java.util.ArrayList;
import java.util.List;

public class MedecineFragment extends Fragment implements AllMedicinesViewInterface {
    MedicineRecyclerViewAdapter adapter;
    ArrayList<Medicine> list = new ArrayList<Medicine>();
    MedicinePresenterInterface presenter;

    public MedecineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medecine, container, false);
        initRecyclerView(view);

        presenter = new MedicinePresenter(Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext()), FirebaseOperation.getInstance()), this);

        presenter.getStoredMedicineFireBase();
        return view;
    }

    private void initRecyclerView(View view) {
        adapter = new MedicineRecyclerViewAdapter(list, getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.MedicineRecsyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showAllMedicines(List<Medicine> medicines) {
        Log.i("TAG", "showAllMedicines: done");
        adapter.setList(medicines);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {

    }

}