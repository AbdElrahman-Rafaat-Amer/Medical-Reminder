package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;


public class firstFragment extends Fragment {

    Button button;
    Medicine medicine;
    EditText medName;

    public firstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_first, container, false);
       medName= view.findViewById(R.id.nameOfMed);

         medicine= medicine.getInstance();


         button = view.findViewById(R.id.nameButton);
         button.setOnClickListener(btnView -> {
             if (getActivity() != null) {
                 if (isValidateName()){

                 medicine.setName(medName.getText().toString());
                 NavController navController = Navigation.findNavController(btnView);
                 navController.navigate(R.id.medFormFragment);
                 }

             }


         });
        return  view;
    }

    private boolean isValidateName() {
        String nameInput = medName.getText().toString().trim();
        boolean isValidate;
        if (nameInput.isEmpty()) {
            medName.setError("Enter medicine name");
            isValidate = false;
        } else {

                medName.setError(null);
                isValidate = true;
            }

        return isValidate;
    }
}