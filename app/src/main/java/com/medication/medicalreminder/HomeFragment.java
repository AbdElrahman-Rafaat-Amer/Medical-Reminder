package com.medication.medicalreminder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeFragment extends Fragment {
    // RecyclerView homeRecylerView;
    FloatingActionsMenu btnMenuFloating;
    FloatingActionButton btnAddMedecine;
    FloatingActionButton btnAddHealthTaker;
    ArrayList<MedicinePojoo> medicieneArray = new ArrayList<>();
    RecyclerView recyclerView;
    HomeRecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager linearLayoutManager;
    View view;
    ArrayList<MedicinePojoo> list = new ArrayList<MedicinePojoo>();
    ArrayList<MedicinePojoo> spesificList = new ArrayList<MedicinePojoo>();
    HorizontalCalendar horizontalCalendar;

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
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        horizontalCalendar = new HorizontalCalendar.Builder(view,R.id.dateTimeline)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
        initRecyclerView(view);

        // homeRecylerView = view.findViewById(R.id.recyclerViewHome);
        btnMenuFloating = view.findViewById(R.id.menuFloating);
        btnAddMedecine = view.findViewById(R.id.floatingAddMed);
        btnAddHealthTaker = view.findViewById(R.id.floatingAddHealthCareTaker);
        dealWithDatePickerTimeline();
        // Log.i("TAG", "onCreateView: " +TimeZone.getDefault());
        //Log.i("TAG", "onCreateView: ,Locale.getDefault() " + Locale.getDefault());
       /* datePickerTimeline.setActiveDate(Calendar.getInstance(TimeZone.getDefault(),Locale.getDefault()));
        dealWithDatePickerTimeline();*/
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
                Intent addMedIntent = new Intent(getActivity(), AddMActivity.class);
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
                    MedicinePojoo   medicinePojoo1= snapshot1.getValue(MedicinePojoo.class);
                    list.add(medicinePojoo1);
                }
                // recyclerViewAdapter.notifyDataSetChanged();
                Log.i("TAG", "onDataChange Read from Firebase: " + list);
                Log.i("TAG", "onDataChange Size of list " + list.size());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.UK);
                Date today = new Date();
                getMedicene(formatter.format(today));
                // dealWithDatePickerTimeline();
                // if(horizontalCalendar.getSelectedDate().getTime().compareTo(today)==0){
                //}
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
                // Log.i("TAG", "onChildChanged ??????: " + previousChildName);
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
        //getMedicineInSpecificDate();
        return view;
    }

    public void dealWithDatePickerTimeline() {
        //      .datesNumberOnScreen(7)
        Log.i("TAG", "dealWithDatePickerTimeline: ");
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSelected(Calendar date, int position) {

                //  datePickerTxtView.setText((CharSequence) date);
                //int day= date.get(Calendar.DAY_OF_MONTH);
//             int month = date.get(Calendar.MONTH);
//             int year= date.get(Calendar.YEAR);

                // String datee = day + "-" + month + "-" + year;
                //Log.i("TAG", "onDateSelected:" + datee);
                // getMedicineInSpecificDate(datee);
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
            // startDatePojo = new Date();
            // endDatePojo= new Date();
            // dateSelected = new Date();
            // Log.i("TAG", "medicinePojoo1.getStartDate()"+ medicinePojoo1.getStartDate());

            Log.i("TAG", "size list inside getDate : " + list.size());
            for (int i = 0; i < list.size();i++) {
                MedicinePojoo medicinePojoo1=list.get(i);
                Log.i("TAG", "getMedicene: " +list.get(i));
                Date  startDatePojo = sdf.parse(medicinePojoo1.getStartDate());
                Date endDatePojo= sdf.parse(medicinePojoo1.getEndDate());
                //Calendar calendar = Calendar.getInstance();
                // String date = DateFormat.getDateInstance().format(calendar.getTime());
                Date dateSelected = sdf.parse(datee);
                Log.i("TAG", "selectedDate: " + dateSelected);
                Log.i("TAG", "Startdate  pojo: " + startDatePojo);
                Log.i("TAG", "Enddate  pojo: " + endDatePojo);
                if (dateSelected.compareTo(startDatePojo)>= 0 && dateSelected.compareTo(endDatePojo) <= 0) {
                    //spesificList.clear();
                    spesificList.add(medicinePojoo1);
                    Log.i("TAG", "specificList inside if: " + spesificList.size());
                    //initRecyclerView();
                    //  recyclerViewAdapter.notifyDataSetChanged();
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
        recyclerViewAdapter = new HomeRecyclerViewAdapter(spesificList, getActivity());
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.i("TAG", "onDateSelected: "+ horizontalCalendar.getSelectedDate().getTime());

        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.UK);

        // Date today = new Date();
        //  horizontalCalendar.getSelectedDate().getTime();



        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.UK);
        Date today = new Date();
        getMedicene(formatter.format(today));
        // dealWithDatePickerTimeline();
    }
    private void initRecyclerView() {
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.i("TAG", "onDateSelected: "+ horizontalCalendar.getSelectedDate().getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.UK);
        Date today = new Date();
        getMedicene(formatter.format(today));
        // dealWithDatePickerTimeline();

    }
}
//    public void  getMedicineInSpecificDate() {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Medicines").child("Medicine Two");
//         //MedicinePojoo medicinePojoo = new MedicinePojoo("Omega3 ","20-10-2022",20,"14-3-2022","20-3-2022");
//        //   reference.setValue("inshalaah");
//        //reference.push().setValue(medicinePojoo);
//        //reference =FirebaseDatabase.getInstance().getReference().child("Database");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                for (DataSnapshot snapshot1 : datasnapshot.getChildren()) {
//                     medicinePojoo1 = snapshot1.getValue(MedicinePojoo.class);
//                   // spesificList.add(String.valueOf(medicinePojoo1));
//                    //medicinePojoo1.getTakenMediceneDate();
//                    Log.i("TAG", "Array start Date: " +medicinePojoo1.getStartDate());
//
//                    //spesificList.add(medicinePojoo1.getTakenMediceneDate());
//                  //  Log.i("TAG", "onDataChange: " +  medicinePojoo1.getTakenMediceneDate());
//                }
//                        // medicinePojooSpecific.getMedicineList();
//
//
//                        //  recyclerViewAdapter.notifyDataSetChanged();
//               // Log.i("TAG", "onDataChange Read Date from Firebase: " + spesificList);
//                Log.i("TAG", "onDataChange Read from Firebase: " + list.get(0));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//    }


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
                String date = day + "/" + newmb  onth + "/" + year;
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
