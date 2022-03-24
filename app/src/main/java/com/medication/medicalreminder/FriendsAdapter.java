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

import com.medication.medicalreminder.model.UserPojo;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    Context context;
    private ArrayList<UserPojo> users;

    public FriendsAdapter(Context context, ArrayList<UserPojo> users) {
        this.context = context;
        this.users = users;
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
        UserPojo user = users.get(position);
        if (!user.getRequesterName().equals("NULL")) {
            holder.patientsNameTextView.setText(user.getRequesterName());
            holder.pattensProfileImageView.setImageResource(R.drawable.ic_profile_image);
        }


    }

    @Override
    public int getItemCount() {
        return users.size();
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
