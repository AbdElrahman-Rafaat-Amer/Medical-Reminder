package com.medication.medicalreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class MedecineFragment extends Fragment {
RecyclerView recyclerView;
    MedicinePojoo[] medicieneArray={ new MedicinePojoo("First Cake","Cake  1Description",R.drawable.ic_one),
            new MedicinePojoo("Second Cake","Cake 2 Description",R.drawable.ic_two ),
            new MedicinePojoo("Third Cake","Cake 3 Description",R.drawable.ic_three),
            new MedicinePojoo("Second Cake","Cake 2 Description",R.drawable.ic_two ),
            new MedicinePojoo("Third Cake","Cake 3 Description",R.drawable.ic_three),
            new MedicinePojoo("Second Cake","Cake 2 Description",R.drawable.ic_two ),
            new MedicinePojoo("Third Cake","Cake 3 Description",R.drawable.ic_three)
    };


    public MedecineFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_medecine, container, false);
        initRecyclerView(view);
        return view;
    }
    private void initRecyclerView(View view){
       MedicineRecyclerViewAdapter adapter= new MedicineRecyclerViewAdapter(Arrays.asList(medicieneArray), getActivity());
        RecyclerView recyclerView= view.findViewById(R.id.MedicineRecsyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
       recyclerView.setAdapter(adapter);
    }
}