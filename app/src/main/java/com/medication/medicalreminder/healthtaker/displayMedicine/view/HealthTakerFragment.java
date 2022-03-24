package com.medication.medicalreminder.healthtaker.displayMedicine.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.addmedicine.view.AddMActivity;
import com.medication.medicalreminder.healthtaker.displayMedicine.presnter.HealthTakerMedicinePresenter;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HealthTakerFragment extends Fragment implements HealthTakerMedicinesViewInterface {

    private FloatingActionButton banAddMedicine;
    private RecyclerView recyclerView;
    private HealthTakerRecyclerViewAdapter recyclerViewAdapter;
    private View view;
    private List<Medicine> list = new ArrayList<>();
    private ArrayList<Medicine> spesificList = new ArrayList<>();
    private HorizontalCalendar horizontalCalendar;
    private HealthTakerMedicinePresenter presenter;
    private static final String TAG = "HealthTakerFragment";

    public HealthTakerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_updates, container, false);
        presenter = new HealthTakerMedicinePresenter(Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext()), FirebaseOperation.getInstance()), this);

        presenter.getStoredMedicineFireBaseOfHealthTaker();

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.dateTimeline_healthTaker)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
        initRecyclerView(view);
        banAddMedicine = view.findViewById(R.id.floatingAddMed_healthTaker);
        dealWithDatePickerTimeline();

        banAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onCreateView: inside add medicine button");
                Intent addMedicineIntent = new Intent(getActivity(), AddMActivity.class);
                addMedicineIntent.putExtra("TYPE", "HT");
                Log.i(TAG, "onClick: " + addMedicineIntent.getStringExtra("TYPE"));
                startActivity(addMedicineIntent);
            }
        });
        return view;
    }

    public void dealWithDatePickerTimeline() {
        Log.i(TAG, "dealWithDatePickerTimeline: ");
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSelected(Calendar date, int position) {
                Log.i(TAG, "onDateSelected: ");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
                Date today = date.getTime();
                getMedicene(formatter.format(today));
            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView, int dx, int dy) {
            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });
    }

    private void getMedicene(String datee) {
        Log.i(TAG, "datee coming " + datee);
        spesificList.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        try {
            //list.clear();
            Log.i(TAG, "size list inside getDate : " + list.size());
            for (int i = 0; i < list.size(); i++) {
                Medicine medicinePojoo1 = list.get(i);
                Log.i(TAG, "getMedicene: " + list.get(i));
                Date startDatePojo = sdf.parse(medicinePojoo1.getStartDate());
                Date endDatePojo = sdf.parse(medicinePojoo1.getEndDate());
                Date dateSelected = sdf.parse(datee);
                Log.i(TAG, "selectedDate: " + dateSelected);
                Log.i(TAG, "Startdate  pojo: " + startDatePojo);
                Log.i(TAG, "Enddate  pojo: " + endDatePojo);
                if (dateSelected.compareTo(startDatePojo) >= 0 && dateSelected.compareTo(endDatePojo) <= 0) {
                    //spesificList.clear();
                    spesificList.add(medicinePojoo1);
                    Log.i(TAG, "specificList inside if: " + spesificList.size());
                    Log.i(TAG, "getMedicene specific list: " + spesificList);
                } else {
                    Log.i(TAG, "getMedicene: elseeee");
                    // spesificList.clear();
                }
                recyclerViewAdapter.setList(spesificList);
                recyclerViewAdapter.notifyDataSetChanged();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView(View view) {
        recyclerViewAdapter = new HealthTakerRecyclerViewAdapter(list, getActivity());
        recyclerView = view.findViewById(R.id.recyclerViewHome_healthTaker);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.i(TAG, "onDateSelected: " + horizontalCalendar.getSelectedDate().getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
        Date today = new Date();
        getMedicene(formatter.format(today));
    }

    @Override
    public void showAllMedicines(List<Medicine> medicines) {
        list = medicines;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
        Date today = new Date();
        getMedicene(formatter.format(today));
    }


    @Override
    public void showErrorMessage(String message) {

    }

}
