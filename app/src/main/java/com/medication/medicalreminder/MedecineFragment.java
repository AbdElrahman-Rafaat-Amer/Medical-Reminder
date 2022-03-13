package com.medication.medicalreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medication.medicalreminder.model.MedicinePojoo;

import java.util.ArrayList;

public class MedecineFragment extends Fragment {
    MedicineRecyclerViewAdapter adapter;
    ArrayList<MedicinePojoo> list = new ArrayList<MedicinePojoo>();
//
//    MedicinePojoo[] medicieneArray={ new MedicinePojoo("First Medicine","Medicine  1Description",R.drawable.ic_one,"12-3-2022","17-3-2022" ),
//            new MedicinePojoo("Second Medicine","Medicine 2 Description",R.drawable.ic_two ,"12-3-2022","17-3-2022"),
//            new MedicinePojoo("Third Medicine","Medicine 3 Description",R.drawable.ic_three,"12-3-2022","17-3-2022"),
//            new MedicinePojoo("Second Medicine","Medicine 2 Description",R.drawable.ic_two ,"12-3-2022","17-3-2022"),
//            new MedicinePojoo("Medicine Cake","Medicine 3 Description",R.drawable.ic_three,"12-3-2022","17-3-2022"),
//            new MedicinePojoo("Medicine Cake","Medicine 2 Description",R.drawable.ic_two ,"12-3-2022","17-3-2022"),
//            new MedicinePojoo("Medicine Cake","Medicine 3 Description",R.drawable.ic_three,"12-3-2022","17-3-2022")
//    };
    public MedecineFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_medecine, container, false);
        initRecyclerView(view);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Medicines").child("Medicine Two");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                MedicinePojoo medicinePojoo1;
                for(DataSnapshot snapshot1: datasnapshot.getChildren()){
                    medicinePojoo1= snapshot1.getValue(MedicinePojoo.class);
                    list.add(medicinePojoo1);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }
    private void initRecyclerView(View view){
        adapter= new MedicineRecyclerViewAdapter(list, getActivity());
        RecyclerView recyclerView= view.findViewById(R.id.MedicineRecsyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}