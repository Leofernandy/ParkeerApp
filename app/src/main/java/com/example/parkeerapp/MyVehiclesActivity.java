package com.example.parkeerapp;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class MyVehiclesActivity extends AppCompatActivity {

    ImageView btnAdd;
    TextView txvEditVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_vehicles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddVehicles();
            }
        });

        ListView listView = findViewById(R.id.listVehicles);

        List<String> dummyVehicles = Arrays.asList(
                "BK 1234 ABC",
                "BK 5678 DEF",
                "BK 9999 XYZ"
        );

        VehicleAdapter adapter = new VehicleAdapter(this, dummyVehicles);
        listView.setAdapter(adapter);}



    public void toAddVehicles(){
        Intent intent = new Intent(this, AddVehicleActivity.class);
        startActivity(intent);
    }
}