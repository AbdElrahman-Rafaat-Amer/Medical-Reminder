package com.medication.medicalreminder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

public class AddMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mactivity);
        NavHostFragment navHostFragment= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_demooo);
        if (navHostFragment!=null) {
            NavController navController = navHostFragment.getNavController();
            NavGraph navGraph = navHostFragment.getNavController().getNavInflater().inflate(R.navigation.nav_graph);
            navGraph.setStartDestination(R.id.firstFragment);
            navController.setGraph(navGraph);
    }
}}