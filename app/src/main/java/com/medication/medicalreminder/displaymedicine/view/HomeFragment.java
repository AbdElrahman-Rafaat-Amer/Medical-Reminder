package com.medication.medicalreminder.displaymedicine.view;

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
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.addmedicine.view.AddMActivity;
import com.medication.medicalreminder.displaymedicine.persenter.HomeMedicienePresenter;
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

public class HomeFragment extends Fragment implements AllMedicinesViewInterface {
    FloatingActionsMenu btnMenuFloating;
    FloatingActionButton btnAddMedecine;
    FloatingActionButton btnAddHealthTaker;
    RecyclerView recyclerView;
    HomeRecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager linearLayoutManager;
    View view;
    List<Medicine> list = new ArrayList<>();
    ArrayList<Medicine> spesificList = new ArrayList<>();
    HorizontalCalendar horizontalCalendar;
    HomeMedicienePresenter presenter;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        presenter = new HomeMedicienePresenter(Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext()), FirebaseOperation.getInstance()),this);

        // Check of internet
         presenter.getStoredMedicineFireBase();
        //ROOM
        /*presenter.getAllMedicines().observe(getViewLifecycleOwner(), new Observer<List<Medicine>>() {
            @Override
            public void onChanged(List<Medicine> medicines) {
                showAllMedicines(medicines);
            }
        });
*/

        
        Calendar startDate = Calendar.getInstance();

        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.dateTimeline)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
        initRecyclerView(view);
        btnMenuFloating = view.findViewById(R.id.menuFloating);
        btnAddMedecine = view.findViewById(R.id.floatingAddMed);
        btnAddHealthTaker = view.findViewById(R.id.floatingAddHealthCareTaker);
        dealWithDatePickerTimeline();
        btnAddHealthTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code To Add HealthTaker
            }
        });
        btnAddMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onCreateView: inside add medecine button");
                Intent addMedIntent = new Intent(getActivity(), AddMActivity.class);
                startActivity(addMedIntent);
            }
        });
        return view;
    }

    public void dealWithDatePickerTimeline() {
        Log.i("TAG", "dealWithDatePickerTimeline: ");
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSelected(Calendar date, int position) {
                Log.i("TAG", "onDateSelected: ");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.UK);

                Date today = date.getTime();
                getMedicene(formatter.format(today));
                //  getMedicene(String.valueOf(today));
            }
            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {
            }
            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });
    }
    private void getMedicene(String datee) {
        Log.i("TAG", "datee coming " + datee);
        spesificList.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy",Locale.US);
        try {
            //list.clear();
            Log.i("TAG", "size list inside getDate : " + list.size());
            for (int i = 0; i < list.size();i++) {
                Medicine medicinePojoo1=list.get(i);
                Log.i("TAG", "getMedicene: " +list.get(i));
                Date  startDatePojo = sdf.parse(medicinePojoo1.getStartDate());
                Date endDatePojo= sdf.parse(medicinePojoo1.getEndDate());
                Date dateSelected = sdf.parse(datee);
                Log.i("TAG", "selectedDate: " + dateSelected);
                Log.i("TAG", "Startdate  pojo: " + startDatePojo);
                Log.i("TAG", "Enddate  pojo: " + endDatePojo);
                if (dateSelected.compareTo(startDatePojo)>= 0 && dateSelected.compareTo(endDatePojo) <= 0) {
                    //spesificList.clear();
                    spesificList.add(medicinePojoo1);
                    Log.i("TAG", "specificList inside if: " + spesificList.size());
                    Log.i("TAG", "getMedicene specific list: " + spesificList);
                } else {
                    Log.i("TAG", "getMedicene: elseeee");
                    // spesificList.clear();
                }
                recyclerViewAdapter.setList(spesificList);
                recyclerViewAdapter.notifyDataSetChanged();
            }

        }catch(ParseException e){
            e.printStackTrace();
        }
    }

    private void initRecyclerView(View view) {
        recyclerViewAdapter = new HomeRecyclerViewAdapter(list, getActivity());
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.i("TAG", "onDateSelected: "+ horizontalCalendar.getSelectedDate().getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.UK);
        Date today = new Date();
        getMedicene(formatter.format(today));
    }

    @Override
    public void showAllMedicines(List<Medicine> medicines) {
        list= medicines;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.UK);
        Date today = new Date();
        getMedicene(formatter.format(today));
    }


    @Override
    public void showErrorMessage(String message) {

    }
}
