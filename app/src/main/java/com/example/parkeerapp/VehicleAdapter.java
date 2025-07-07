package com.example.parkeerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class VehicleAdapter extends BaseAdapter {
    private Context context;
    private List<String> dummyList;

    public VehicleAdapter(Context context, List<String> dummyList) {
        this.context = context;
        this.dummyList = dummyList;
    }

    @Override
    public int getCount() {
        return dummyList.size();
    }

    @Override
    public Object getItem(int position) {
        return dummyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView imageVehicle;
        TextView textPlate, textCarName, textColor, txvEditVehicle, textDefault;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_vehicles, parent, false);
            holder = new ViewHolder();
            holder.imageVehicle = convertView.findViewById(R.id.imageVehicle);
            holder.textPlate = convertView.findViewById(R.id.textPlate);
            holder.textCarName = convertView.findViewById(R.id.textCarName);
            holder.textColor = convertView.findViewById(R.id.textColor);
            holder.txvEditVehicle = convertView.findViewById(R.id.txvEditVehicle);  // Pastikan ID-nya benar
            holder.textDefault = convertView.findViewById(R.id.textDefault);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set dummy data
        String plateNumber = dummyList.get(position);
        holder.textPlate.setText(plateNumber);
        holder.textCarName.setText("Toyota Agya");
        holder.textColor.setText("Black");
        holder.imageVehicle.setImageResource(R.drawable.agya_black);

        // Tambahkan click listener untuk tombol EDIT
        holder.txvEditVehicle.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditVehicleActivity.class);
            intent.putExtra("plate", plateNumber);
            intent.putExtra("carName", "Toyota Agya");
            intent.putExtra("color", "Black");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Tambahkan ini jika context bukan activity
            context.startActivity(intent);
        });

        return convertView;
    }

}
