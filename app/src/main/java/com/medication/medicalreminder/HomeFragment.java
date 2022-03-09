package com.medication.medicalreminder;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class HomeFragment extends Fragment {
    RecyclerView homeRecylerView;
    FloatingActionsMenu btnMenuFloating;
    FloatingActionButton btnAddMedecine;
    FloatingActionButton btnAddHealthTaker;
    DatePickerTimeline datePickerTimeline;
    TextView datePickerTxtView;
    ArrayList<MedicinePojoo> medicieneArray = new ArrayList<>();
    RecyclerView recyclerView;
    HomeRecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager linearLayoutManager;
    public HomeFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicieneArray.add(new MedicinePojoo("First Cake", "Cake  1Description", R.drawable.ic_one));
        medicieneArray.add(new MedicinePojoo("Second Cake", "Cake 2 Description", R.drawable.ic_two));
        medicieneArray.add(new MedicinePojoo("Third Cake", "Cake 3 Description", R.drawable.ic_three));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initRecyclerView(view);
        homeRecylerView = view.findViewById(R.id.recyclerViewHome);
        btnMenuFloating = view.findViewById(R.id.menuFloating);
        btnAddMedecine = view.findViewById(R.id.floatingAddMed);
        btnAddHealthTaker = view.findViewById(R.id.floatingAddHealthCareTaker);
        datePickerTimeline = view.findViewById(R.id.dateTimeline);
        datePickerTxtView = view.findViewById(R.id.datePickerTxtView);
       // datePickerTimeline.setActiveDate(Calendar.getInstance());

        Log.i("TAG", "onCreateView: " +TimeZone.getDefault());
        Log.i("TAG", "onCreateView: ,Locale.getDefault() " + Locale.getDefault());
        datePickerTimeline.setActiveDate(Calendar.getInstance(TimeZone.getDefault(),Locale.getDefault()));
        dealWithDatePickerTimeline();
        homeRecylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                btnAddMedecine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Code To Add Medicine
                    }
                });
            }
        });

        btnAddHealthTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code To Add HealthTaker
            }
        });

        return view;
    }



    public void dealWithDatePickerTimeline() {
        datePickerTimeline.setInitialDate(2022, 2, 1);
        datePickerTimeline.setActiveDate(Calendar.getInstance());

        // datePickerTimeline.setDayTextColor(-16711936);
        // datePickerTimeline.setMonthTextColor(-16711936);
        datePickerTimeline.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                int newmonth = month + 1;
                String date = day + "/" + newmonth + "/" + year;
                Log.i("TAG", "onDateSelected: " + date);
                datePickerTxtView.setText(date);

                // Send date to Remot Source
                // Source get Data medicine in the selected day
                // rtuen List of MedicinePojoo
                // get returned list and send it to adpter
                //recyclerViewAdapter.setList(returned list);
                //recyclerViewAdapter.setNotfChanged();
                medicieneArray.add(new MedicinePojoo(date, "dayOfWeek" + dayOfWeek, 5));
                initRecyclerView();
            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {
            }
        });

        Date[] dates = {Calendar.getInstance().getTime()};
        Log.i("TAG", "dealWithDatePickerTimeline: " + Calendar.getInstance().getTime());
       //datePickerTimeline.deactivateDates(dates);
      //  datePickerTimeline.setActiveDate(Calendar.getInstance());


       // datePickerTimeline.setDateTextColor(Color.RED);
        //datePickerTimeline.setDayTextColor(Color.RED);
        //datePickerTimeline.setMonthTextColor(Color.RED);

       // pp:disabledColor (color) -> default -> Grey
    }
    private void initRecyclerView(View view) {
        recyclerViewAdapter = new HomeRecyclerViewAdapter(medicieneArray, getActivity());
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initRecyclerView() {
        recyclerViewAdapter = new HomeRecyclerViewAdapter(medicieneArray, getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}