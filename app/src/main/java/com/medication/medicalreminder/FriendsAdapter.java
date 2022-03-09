package com.medication.medicalreminder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    Context context;
    private ArrayList<Patient> patients;
    FriendsAdapterInterface adapterInterface;

    public FriendsAdapter(Context context, ArrayList<Patient> patients) {
        this.context = context;
        this.patients = patients;
        this.adapterInterface = (FriendsAdapterInterface) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.expandable_custom_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Patient patient = patients.get(position);
        holder.patientsNameTextView.setText(patient.getName());
        holder.pattensProfileImageView.setImageResource(patient.getProfileImageTest());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Select a user change the roles", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onClick: " + patient);
                adapterInterface.changeFriend(patient);
            }
        });

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView patientsNameTextView;
        CircleImageView pattensProfileImageView;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.patient_user_layout);
            patientsNameTextView = itemView.findViewById(R.id.nameTextView_patients);
            pattensProfileImageView = itemView.findViewById(R.id.circleImageView_patients);

        }
    }

}
