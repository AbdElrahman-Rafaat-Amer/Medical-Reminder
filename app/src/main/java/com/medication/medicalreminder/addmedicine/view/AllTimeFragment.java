package com.medication.medicalreminder.addmedicine.view;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;


import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;

import java.util.List;


public class AllTimeFragment extends Fragment {


    private static  String TAG ;
    String  am_pm;
    int hou;
    TextView text;
    int often;
    int counterOften;
    int counter;
    Medicine medicine ;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TimePicker picker=view.findViewById(R.id.datePicker);
        text=view.findViewById(R.id.dose);
        picker.setIs24HourView(false);
        picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hours, int min) {


                if(hou>12)
                {
                    am_pm = "PM";
                    hou = hou - 12;
                }
                else
                {
                    am_pm="AM";
                }

                final String  stringNewTime = hours + ":" +min +":"+am_pm;

                medicine.setTime(stringNewTime);
            }
        });
        setTitleText();

        Button timetorefill = view.findViewById(R.id.timeTorefill);

       // timetorefill.setText(medicine.getOften());

       timetorefill.setOnClickListener(btnView -> {
           if (getActivity() != null) {


               NavController navController = Navigation.findNavController(btnView);
               navController.navigate(R.id.pillsNumberFragment);

           }
       });


    }
    private void setTitleText (){

        medicine= medicine.getInstance();
        String oftenStr = medicine.getOften();

        switch (oftenStr){
            case "Once Daily":
                often = 1;
                break;
            case "Twice Daily":
                often = 2;
                break;
            case "3 times daily":
                often =3 ;
                break;
            default:
                Log.i(TAG ,"ERROR");
        }
        counterOften= often;
        counter=often;
    }


}