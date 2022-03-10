package com.medication.medicalreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MedInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedInfoFragment newInstance(String param1, String param2) {
        MedInfoFragment fragment = new MedInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_med_info, container, false);
        NumberPicker medNumPicker = view.findViewById(R.id.strengthNumPicker);
        NumberPicker medDoseNumPicker = view.findViewById(R.id.doseNumPicker);

        medNumPicker.setMinValue(0);
        medNumPicker.setMaxValue(100);

        String[] doseStrengthArray = getResources().getStringArray(R.array.doseStrength);
        medDoseNumPicker.setMinValue(0);
        medDoseNumPicker.setMaxValue(4);
        medDoseNumPicker.setDisplayedValues(doseStrengthArray);

        Spinner medSpinner = (Spinner) view.findViewById(R.id.medFormSpinner);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.medForm, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        medSpinner.setAdapter(adapter);

        return view;
    }
}