package com.medication.medicalreminder;

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
import androidx.recyclerview.widget.RecyclerView;

import com.medication.medicalreminder.model.MedicinePojoo;

import java.util.List;


public class MedicineRecyclerViewAdapter extends RecyclerView.Adapter<MedicineRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<MedicinePojoo> mediceneArray;

    public MedicineRecyclerViewAdapter(List<MedicinePojoo> mediceneArray, Context context) {
        this.context = context;
        this.mediceneArray = mediceneArray;
        Log.i("TAG", "RecyclerViewAdapter: this.mediceneArray " + this.mediceneArray);
        Log.i("TAG", "RecyclerViewAdapter: mediceneArray " + mediceneArray);
        Log.i("TAG", "RecyclerViewAdapter: mediceneArray.size() " + mediceneArray.size());
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
            medName = row.findViewById(R.id.medicineTxtView);
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
        MedicinePojoo medicinePojoo = mediceneArray.get(position);
        //  holder.getImgView().setImageResource(mediceneArray.get(position).getMedicineImage());
        holder.getMedName().setText(mediceneArray.get(position).getMedicineName());
        holder.getTakenDate().setText(mediceneArray.get(position).getTakenMediceneDate());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //JSONObject jsonObject = new JSONObject();
                
                    //jsonObject.put("medObject",medicinePojoo);
                    Intent displayIntent = new Intent(context , DisplayMedActivity.class);
                    displayIntent.putExtra("medicine", medicinePojoo.getMedicineName());
                    Log.i("TAG", "medicine pojo" + medicinePojoo);
                   // Log.i("TAG", "json obj" + jsonObject);
//                    Log.i("TAG", "json obj string" + jsonObject.toString());
                    context.startActivity(displayIntent);
                

            }
        });

    }
    @Override
    public int getItemCount() {
        Log.i("TAG", "getItemCount: " + mediceneArray.size());
        return mediceneArray.size();
    }
}

