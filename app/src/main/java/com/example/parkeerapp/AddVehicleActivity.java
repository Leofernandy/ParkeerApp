package com.example.parkeerapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddVehicleActivity extends AppCompatActivity {

    Spinner sprBrand, sprModel , sprYear, sprColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sprBrand = findViewById(R.id.sprBrand);
        sprColor = findViewById(R.id.sprColor);
        sprModel = findViewById(R.id.sprModel);
        sprYear = findViewById(R.id.sprYear);

        ArrayAdapter<CharSequence> brandAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.brand_array,
                R.layout.spinner_item
        );
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprBrand.setAdapter(brandAdapter);

        ArrayAdapter<CharSequence> modelAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.model_array,
                R.layout.spinner_item
        );
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprModel.setAdapter(modelAdapter);

        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.year_array,
                R.layout.spinner_item
        );
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprYear.setAdapter(yearAdapter);

        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.color_array,
                R.layout.spinner_item
        );
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprColor.setAdapter(colorAdapter);

    }
}