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

import com.google.gson.Gson;
import com.medication.medicalreminder.displaymedicine.view.DisplayMedView;
import com.medication.medicalreminder.editmedicine.view.EditMedicineView;
import com.medication.medicalreminder.model.Medicine;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class MedicineRecyclerViewAdapter extends RecyclerView.Adapter<MedicineRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Medicine> roomMedArray = new ArrayList<>();

    public MedicineRecyclerViewAdapter(List<Medicine> mediceneArray, Context context) {
        this.context = context;
        this.roomMedArray = mediceneArray;
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
        // MedicinePojoo medicinePojoo = mediceneArray.get(position);

        Medicine medicine= roomMedArray.get(position);
        holder.getImgView().setImageResource(medicine.setImage(medicine.getImage()));

        holder.getMedName().setText(medicine.getName());
        //holder.getTakenDate().setText(medicine.getTime());
        String date = medicine.getTime();
        StringTokenizer stringTokenizer= new StringTokenizer(date,",");
        String newDate = "";
        while (stringTokenizer.hasMoreTokens()) {
            //StringTokenizer stringTokenizer= new StringTokenizer(date,":");
            String x = (String) stringTokenizer.nextElement();
            String[] separated = x.split(":");
            int N = Integer.parseInt(separated[0]);
            String C =separated[1];
            if (C.equals("0")){
                C= "00";
                Log.i("TAG", "onBindViewHolder: " + C);
            }
            Log.i("TAG", "onBindViewHolder:  seperated " + separated[0]);
            Log.i("TAG", "onBindViewHolder: separated " + separated[1]);
            if (N == 24) {
                newDate = newDate + "\n" + 12 + C +" AM";

            } else {
                if (N > 12) {
                    N = N - 12;

                    newDate = newDate + "\n" + N +":"+ C + " PM";

                } else
                    newDate = newDate + "\n" + N +":"+C +" PM";

            }
        }
        holder.getTakenDate().setText(newDate);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent displayIntent = new Intent(context , DisplayMedView.class);
                Gson gson = new Gson();
                String json = gson.toJson(medicine);
                displayIntent.putExtra(EditMedicineView.JSON,json);
                context.startActivity(displayIntent);

                //JSONObject jsonObject = new JSONObject();

                //jsonObject.put("medObject",medicinePojoo);
              /*  Intent displayIntent = new Intent(context , DisplayMedView.class);
                displayIntent.putExtra("medicine", medicine.getName());
                Log.i("TAG", "medicine pojo" + medicine);
                 Log.i("TAG", "json obj" + jsonObject);
                  Log.i("TAG", "json obj string" + jsonObject.toString());
                context.startActivity(displayIntent);*/


            }
        });
//        //  holder.getImgView().setImageResource(mediceneArray.get(position).getMedicineImage());
//        holder.getMedName().setText(mediceneArray.get(position).getMedicineName());
//        holder.getTakenDate().setText(mediceneArray.get(position).getTakenMediceneDate());
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //JSONObject jsonObject = new JSONObject();
//
//                    //jsonObject.put("medObject",medicinePojoo);
//                    Intent displayIntent = new Intent(context , DisplayMedActivity.class);
//                    displayIntent.putExtra("medicine", medicinePojoo.getMedicineName());
//                    Log.i("TAG", "medicine pojo" + medicinePojoo);
//                   // Log.i("TAG", "json obj" + jsonObject);
////                    Log.i("TAG", "json obj string" + jsonObject.toString());
//                    context.startActivity(displayIntent);
//
//
//            }
//        });

    }

    public void setList(List<Medicine> roomMedArray){
        this.roomMedArray= roomMedArray;

    }
    @Override
    public int getItemCount() {
        // Log.i("TAG", "getItemCount: " + mediceneArray.size());
        return roomMedArray.size();
        //  return mediceneArray.size();
    }
}