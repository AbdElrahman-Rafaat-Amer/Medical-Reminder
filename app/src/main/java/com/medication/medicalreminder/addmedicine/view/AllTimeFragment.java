package com.medication.medicalreminder.addmedicine.view;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class AllTimeFragment extends Fragment implements View.OnClickListener {


    private static  String TAG  ;
    Button btnNext;
    TimePicker timePicker;
    TextView textTitle, textDoseNum;
    String doseNum;
    String doseTimes = "";
    int often = 1;
    int oftenDec;
    int doseCount = 1;
    Medicine medicine;


    public AllTimeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_time, container, false);
        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNext = view.findViewById(R.id.btn_next_time);
        timePicker = view.findViewById(R.id.time_picker);
        textTitle = view.findViewById(R.id.title);
        textDoseNum = view.findViewById(R.id.dose_num);

        timePicker.setHour(20);
        timePicker.setMinute(0);


        btnNext.setOnClickListener(this);

        setTitleText();

        if(often > 1){
            doseNum = getString(R.string.pick_time_for_dose) + " " + doseCount + ".";
            textDoseNum.setText(doseNum);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void actionNext(View view) {

        if (often == 1) {

            setTime(view);

        } else {

            if (oftenDec == 1) {
                doseNum = getString(R.string.pick_time_for_dose) + doseCount + ".";
                textDoseNum.setText(doseNum);

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                String time = hour + ":" + minute;

                doseTimes += time;

                Medicine medicine = Medicine.getInstance();
                medicine.setTime(doseTimes);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.pillsNumberFragment);

            } else {
                doseCount++;
                doseNum = getString(R.string.pick_time_for_dose) + " " + doseCount + ".";
                textDoseNum.setText(doseNum);

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                String time = hour + ":" + minute;

                doseTimes += time + ",";
            }
        }
        oftenDec--;
    }
    private void setTitleText (){

        medicine= medicine.getInstance();
        switch (medicine.getOften()){
            case "Once daily":
                often = 1;
                Toast.makeText(getContext(), "onceDaily", Toast.LENGTH_SHORT).show();
                break;
            case "Twice Daily":
                often = 2;
                Toast.makeText(getContext(), "Twice Daily", Toast.LENGTH_SHORT).show();
                break;
            case "3 times Daily":
                often =3 ;
                Toast.makeText(getContext(), "3 times daily", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.i(TAG ,"ERROR");
        }
        oftenDec = often;
        //textTitle.setText(medicine.getName());

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTime(View view){

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        String time = hour + ":" + minute;

        Medicine medicine = Medicine.getInstance();
        medicine.setTime(time);

        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.pillsNumberFragment);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        actionNext(view);
    }
}