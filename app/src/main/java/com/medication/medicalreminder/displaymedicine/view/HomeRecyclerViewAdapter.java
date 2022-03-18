package com.medication.medicalreminder.displaymedicine.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    Context context;
    List <Medicine>  mediceneArray = new ArrayList<>();



    public HomeRecyclerViewAdapter(List<Medicine> mediceneArray, Context context) {
        this.context = context;
        this.mediceneArray= mediceneArray;
    }

    public void setList(List<Medicine> spesificList) {
        this.mediceneArray=spesificList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View row;
        TextView medName;
        TextView takenDate;
        ImageView imgView;
        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            row = convertView;
            medName = row.findViewById(R.id.medcineTxtView);
            takenDate = row.findViewById(R.id.takenTimeTxtView);
            imgView = row.findViewById(R.id.imageView);
            //imgView.setImageResource(700);
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
        //  holder.getImgView().setImageResource(mediceneArray.get(position).getMedicineImage());
        holder.getMedName().setText(mediceneArray.get(position).getName());
        holder.getImgView().setImageResource(mediceneArray.get(position).getImage());
        String date = mediceneArray.get(position).getTime();
        /*String newDate="";
        String date = mediceneArray.get(position).getTime();
        StringTokenizer stringTokenizer= new StringTokenizer(date,",");

        while (stringTokenizer.hasMoreTokens()) {
            //StringTokenizer stringTokenizer1= new StringTokenizer(date,":");

            int x = Integer.parseInt((String) stringTokenizer.nextElement());
            if (x == 24) {
                newDate = newDate + "\n" + 12 + " AM";

            } else {
                if (x > 12) {
                    x = x - 12;
                    newDate = newDate + "\n" + x + " PM";

                } else
                    newDate = newDate + "\n" + x + " PM";

            }
        }*/

        holder.getTakenDate().setText(date);
        //holder.medName.setText(medicinePojoo.getMedicineName());

    }
    @Override
    public int getItemCount() {
        Log.i("TAG", "getItemCount: " + mediceneArray.size());
        return mediceneArray.size();
    }
}