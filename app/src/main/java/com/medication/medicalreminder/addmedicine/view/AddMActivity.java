package com.medication.medicalreminder.addmedicine.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import com.medication.medicalreminder.R;


public class AddMActivity extends AppCompatActivity {
    public static String typeofUser = "";
    private static final String TAG = "AddMActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mactivity);

//        getSupportActionBar().hide();
        typeofUser = getIntent().getStringExtra("TYPE");
        Log.i(TAG, "onCreate: typeofUser --------> " + typeofUser);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_demooo);
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            NavGraph navGraph = navHostFragment.getNavController().getNavInflater().inflate(R.navigation.nav_graph);
            navGraph.setStartDestination(R.id.firstFragment);
            navController.setGraph(navGraph);
        }
    }
}