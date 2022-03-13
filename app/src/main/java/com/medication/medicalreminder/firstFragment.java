package com.medication.medicalreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class firstFragment extends Fragment {
    private static final int EXTRA_AGE = 0;
    Button button;

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
         button = view.findViewById(R.id.btnSbumit);
         button.setOnClickListener(btnView -> {
             if (getActivity() != null) {

                 Bundle bundle = new Bundle();
                 bundle.putInt(String.valueOf(EXTRA_AGE),20);
                 NavController navController = Navigation.findNavController(btnView);
                 navController.navigate(R.id.medFormFragment,bundle);

             }

                /*NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_demo);
                if(navHostFragment != null){
                    NavController navController1 = navHostFragment.getNavController();
                }*/

         });
        return  view;
    }
}