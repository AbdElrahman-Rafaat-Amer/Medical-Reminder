package com.medication.medicalreminder.displaymedicine.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.addmedicine.view.AddMActivity;
import com.medication.medicalreminder.displaymedicine.persenter.HomeMedicienePresenter;
import com.medication.medicalreminder.healthtaker.view.AddHealthTaker;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.reminder.ReminderActivity;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.roomdatabase.ConcreteLocalSource;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeFragment extends Fragment implements AllMedicinesViewInterface, OnMedicineClickListener {
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
    private String TAG = "HomeFragment";
    long time;
    Long finalTime;
    long currentTime;
    Button reschedule, takeButton;
    TextView medicineName;
    ImageView medicineIcon;
    TextView selectedDate;
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
        selectedDate= view.findViewById(R.id.datePickerTxtView);
        presenter = new HomeMedicienePresenter(Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext()), FirebaseOperation.getInstance()), this);

        // Check of internet
        presenter.getStoredMedicineFireBase();

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
                startActivity(new Intent(getContext(), AddHealthTaker.class));
            }
        });
        btnAddMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onCreateView: inside add medecine button");
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
                String selected = date.DAY_OF_MONTH + "-" + date.MONTH + "-" + date.YEAR;
                selectedDate.setText(selected);
                //////////////////////////

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
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
                ///----reminders----////
                ReminderActivity.setMedicineArrayList(spesificList);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ReminderActivity.findNextAlarm();
                }
                ///----reminders----////
            }

        }catch(ParseException e){
            e.printStackTrace();
        }
    }

    private void initRecyclerView(View view) {
        recyclerViewAdapter = new HomeRecyclerViewAdapter(list, getActivity(), this);
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

    @Override
    public void showDialog(Medicine medicine) {
        showDateTimeDialog(medicine);
    }

    public void showDateTimeDialog(Medicine medicine) {

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_schedule);
        reschedule = dialog.findViewById(R.id.reschedule_Button);
        takeButton = dialog.findViewById(R.id.take_Button);
        medicineName = dialog.findViewById(R.id.med_name_dialog);
        medicineIcon = dialog.findViewById(R.id.med_icon_dialog);

        medicineIcon.setImageResource(medicine.getImage());
        medicineName.setText(medicine.getName());

        dialog.show();
        reschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

                                // date_time_in.setText(simpleDateFormat.format(calendar.getTime()));

                                time = calendar.getTimeInMillis();
                                Log.i("selected time ", "selected Time " + "" + time);

                                currentTime = Calendar.getInstance().getTimeInMillis();

                                finalTime = time - currentTime;


                                OneTimeWorkRequest downloadRequest = new OneTimeWorkRequest.Builder(ScheduleWorkManger.class)
                                        // .setInputData(data)

                                        .setInitialDelay(finalTime, TimeUnit.MILLISECONDS)
                                        .build();
                                WorkManager.getInstance(getContext()).enqueue(downloadRequest);
                            }
                        };

                        new TimePickerDialog(getContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
                    }
                };

                new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                dialog.dismiss();
            }

        });
        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int medLeft = medicine.getMedLeft();
                medLeft--;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("medicineList").child(medicine.getUid());
                reference.child("medLeft").setValue(medLeft);
            }
        });

    }
}
