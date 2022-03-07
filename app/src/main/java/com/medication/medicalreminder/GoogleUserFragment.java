package com.medication.medicalreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class GoogleUserFragment extends Fragment {



    public GoogleUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_google_user, container, false);
        TextView name = view.findViewById(R.id.name);
        TextView email = view.findViewById(R.id.email);
        Button button = view.findViewById(R.id.button);
        return view;
    }
}