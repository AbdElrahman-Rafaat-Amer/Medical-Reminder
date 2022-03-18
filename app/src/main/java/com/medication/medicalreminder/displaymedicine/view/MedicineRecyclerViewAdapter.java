package com.medication.medicalreminder.displaymedicine.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.medication.medicalreminder.R;
import com.medication.medicalreminder.editmedicine.view.EditMedicineView;
import com.medication.medicalreminder.model.Medicine;

import java.util.ArrayList;
import java.util.List;


public class MedicineRecyclerViewAdapter extends RecyclerView.Adapter<MedicineRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Medicine> roomMedArray = new ArrayList<>();

    public MedicineRecyclerViewAdapter(ArrayList<Medicine> list, FragmentActivity activity) {
        roomMedArray=list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View row;
        TextView medName;
        TextView takenDate;
        ImageView imgView;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            row = convertView;
            medName = row.findViewById(R.id.medcineTxtView);
            takenDate = row.findViewById(R.id.takenTimeTxtView);
            imgView = row.findViewById(R.id.imageView);
            constraintLayout = row.findViewById(R.id.custom_raw_constraint_layout);
        }
        public View getRow() {
            return row;
        }

        public TextView getMedName() {
            return medName;
        }

        public TextView getTakenDate() {
            return takenDate;
        }

        public ImageView getImgView() {
            return imgView;
        }
    }
    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_row_show_medicine_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicine medicine= roomMedArray.get(position);
         holder.getImgView().setImageResource(medicine.setImage(medicine.getImage()));

        holder.getMedName().setText(medicine.getName());
        holder.getTakenDate().setText(medicine.getTime());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent displayIntent = new Intent(context , DisplayMedView.class);
                Gson gson = new Gson();
                String json = gson.toJson(medicine);
                displayIntent.putExtra(EditMedicineView.JSON,json);
                context.startActivity(displayIntent);
            }
        });
    }

    public void setList(List<Medicine> roomMedArray){
        this.roomMedArray= roomMedArray;
    }
    @Override
    public int getItemCount() {
        return roomMedArray.size();
    }
}

