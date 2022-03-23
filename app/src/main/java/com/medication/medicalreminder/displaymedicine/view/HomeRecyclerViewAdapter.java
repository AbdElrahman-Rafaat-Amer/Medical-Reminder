package com.medication.medicalreminder.displaymedicine.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.medication.medicalreminder.R;
import com.medication.medicalreminder.model.Medicine;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    Context context;
    List <Medicine>  mediceneArray = new ArrayList<>();
    OnMedicineClickListener onMedicineClickListener;


    public HomeRecyclerViewAdapter(List<Medicine> mediceneArray, Context context, OnMedicineClickListener onMedicineClickListener) {
        this.context = context;
        this.mediceneArray= mediceneArray;
        this.onMedicineClickListener = onMedicineClickListener;
    }

    public void setList(List<Medicine> spesificList) {
        this.mediceneArray=spesificList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View row;
        TextView medName;
        TextView takenDate;
        ImageView imgView;
        TextView dateTxtView;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            row = convertView;
            medName = row.findViewById(R.id.medcineTxtView);
            takenDate = row.findViewById(R.id.takenTimeTxtView);
            imgView = row.findViewById(R.id.imageView);
            dateTxtView= row.findViewById(R.id.datePickerTxtView);
            constraintLayout = row.findViewById(R.id.constraint_layout);


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
        Medicine medicine = mediceneArray.get(position);
        holder.getMedName().setText(mediceneArray.get(position).getName());
        holder.getImgView().setImageResource(mediceneArray.get(position).getImage());



        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onMedicineClickListener.showDialog(medicine);


            }
        });

      /*  try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            final Date dateObj = sdf.parse(date);
            Log.i("TAG", "objectt: " + dateObj);
            String newDate =new SimpleDateFormat("K:mm").format(dateObj);
            holder.getTakenDate().setText(newDate);

        } catch (final ParseException e) {
            e.printStackTrace();
        }
*/


        //String newDate="";

        String date = mediceneArray.get(position).getTime();
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
            if (N == 0) {
                newDate = newDate + "\n" + 12 +":"+ C +" AM";

            } else {
                if (N > 12) {
                    N = N - 12;

                    newDate = newDate + "\n" + N +":"+ C + " PM";

                } else
                    newDate = newDate + "\n" + N +":"+C +" AM";

            }
        }
        holder.getTakenDate().setText(newDate);

    }
    @Override
    public int getItemCount() {
        Log.i("TAG", "getItemCount: " + mediceneArray.size());
        return mediceneArray.size();
    }
}