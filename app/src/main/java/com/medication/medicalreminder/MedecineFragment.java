package com.medication.medicalreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedecineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MedecineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedecineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedecineFragment newInstance(String param1, String param2) {
        MedecineFragment fragment = new MedecineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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