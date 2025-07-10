package com.example.parkeerapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;

import com.example.parkeerapp.model.Vehicle;

import io.realm.Realm;

public class EditVehicleActivity extends AppCompatActivity {

    EditText edtPlate;
    Spinner sprBrand, sprModel, sprYear, sprColor;
    Button btnSave;
    TextView txvDelete;
    Realm realm;
    Vehicle currentVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi view
        edtPlate = findViewById(R.id.edtPlate);
        sprBrand = findViewById(R.id.sprBrand);
        sprModel = findViewById(R.id.sprModel);
        sprYear = findViewById(R.id.sprYear);
        sprColor = findViewById(R.id.sprColor);
        btnSave = findViewById(R.id.btnSaveVehicle);
        txvDelete = findViewById(R.id.txvDeleteVehicle);
        ImageView imvLeftArrow = findViewById(R.id.imvLeftArrow);

        // Tombol back
        imvLeftArrow.setOnClickListener(v -> finish());

        // Setup spinner dengan hint
        sprBrand.setAdapter(createHintedAdapter(R.array.brand_array));
        sprModel.setAdapter(createHintedAdapter(R.array.model_array));
        sprYear.setAdapter(createHintedAdapter(R.array.year_array));
        sprColor.setAdapter(createHintedAdapter(R.array.color_array));

        // Ambil data dari Realm
        realm = Realm.getDefaultInstance();
        String plate = getIntent().getStringExtra("plate");
        if (plate != null) {
            currentVehicle = realm.where(Vehicle.class).equalTo("plate", plate).findFirst();
            if (currentVehicle != null) {
                populateFields(currentVehicle);
            }
        }

        // Tombol simpan perubahan
        btnSave.setOnClickListener(v -> {
            realm.executeTransaction(r -> {
                currentVehicle.setBrand(sprBrand.getSelectedItem().toString());
                currentVehicle.setModel(sprModel.getSelectedItem().toString());
                currentVehicle.setYear(sprYear.getSelectedItem().toString());
                currentVehicle.setColor(sprColor.getSelectedItem().toString());
            });
            Toast.makeText(this, "Kendaraan berhasil diperbarui", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Tombol hapus kendaraan
        txvDelete.setOnClickListener(v -> {
            realm.executeTransaction(r -> {
                currentVehicle.deleteFromRealm();
            });
            Toast.makeText(this, "Kendaraan dihapus", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    // Fungsi bantu untuk isi form
    private void populateFields(Vehicle vehicle) {
        edtPlate.setText(vehicle.getPlate());
        edtPlate.setEnabled(false); // tidak bisa ubah plate

        setSpinnerSelection(sprBrand, vehicle.getBrand());
        setSpinnerSelection(sprModel, vehicle.getModel());
        setSpinnerSelection(sprYear, vehicle.getYear());
        setSpinnerSelection(sprColor, vehicle.getColor());
    }

    private void setSpinnerSelection(Spinner spinner, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).toString().equalsIgnoreCase(value)) {
                spinner.setSelection(i);
                return;
            }
        }
    }

    // Adapter spinner hint
    private ArrayAdapter<CharSequence> createHintedAdapter(int arrayResId) {
        return new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, getResources().getTextArray(arrayResId)) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }
        };
    }
}
