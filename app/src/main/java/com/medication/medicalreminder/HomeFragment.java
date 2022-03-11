package com.medication.medicalreminder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

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
    View view;
    ArrayList<MedicinePojoo> list = new ArrayList<MedicinePojoo>();
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
         view = inflater.inflate(R.layout.fragment_home, container, false);

        initRecyclerView(view);
        homeRecylerView = view.findViewById(R.id.recyclerViewHome);
        btnMenuFloating = view.findViewById(R.id.menuFloating);
        btnAddMedecine = view.findViewById(R.id.floatingAddMed);
        btnAddHealthTaker = view.findViewById(R.id.floatingAddHealthCareTaker);
        //datePickerTimeline = view.findViewById(R.id.dateTimeline);
       // datePickerTxtView = view.findViewById(R.id.datePickerTxtView);
       // datePickerTimeline.setActiveDate(Calendar.getInstance());
        dealWithDatePickerTimeline();
        Log.i("TAG", "onCreateView: " +TimeZone.getDefault());
        Log.i("TAG", "onCreateView: ,Locale.getDefault() " + Locale.getDefault());
       /* datePickerTimeline.setActiveDate(Calendar.getInstance(TimeZone.getDefault(),Locale.getDefault()));
        dealWithDatePickerTimeline();
*/
        btnAddHealthTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code To Add HealthTaker
            }
        });
        Log.i("TAG", "onCreateView: BEFORE add medecine button");

        btnAddMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onCreateView: inside add medecine button");
                Intent addMedIntent = new Intent(getActivity(), AddMedActivity.class);
                startActivity(addMedIntent);
            }
        });


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Medicines").child("Medicine Two");
       // MedicinePojoo medicinePojoo = new MedicinePojoo("Conjestal","18-10-2022",20);
        //   reference.setValue("inshalaah");
         //reference.push().setValue(medicinePojoo);
        //reference =FirebaseDatabase.getInstance().getReference().child("Database");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot1: datasnapshot.getChildren()){
                    MedicinePojoo medicinePojoo1= snapshot1.getValue(MedicinePojoo.class);
                    list.add(medicinePojoo1);
                }
                recyclerViewAdapter.notifyDataSetChanged();
                Log.i("TAG", "onDataChange Read from Firebase: " + list);
                //Log.i("TAG", "onDataChange Read from Firebase: " + list.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.i("TAG", "onChildChanged ??????: " + previousChildName);
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
    public void dealWithDatePickerTimeline() {

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view,R.id.dateTimeline)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

             //  .range(startDate, endDate)
          //      .datesNumberOnScreen(7)
            //    .build();
       // HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

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

/*
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
                list.add(new MedicinePojoo(date, "dayOfWeek" + dayOfWeek, 5));
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
    }*/
    private void initRecyclerView(View view) {
        recyclerViewAdapter = new HomeRecyclerViewAdapter(list, getActivity());
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initRecyclerView() {
        recyclerViewAdapter = new HomeRecyclerViewAdapter(list, getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}