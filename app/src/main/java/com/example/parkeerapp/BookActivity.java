package com.example.parkeerapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookActivity extends AppCompatActivity {

    Button btnBook;

    Spinner sprPlate;

    final Calendar myCalendar = Calendar.getInstance();

    EditText edtTglMsk , edtTglKlr , edtJamMsk , edtJamKlr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        //Pilih Tanggal Masuk dan Klr

        edtTglMsk = findViewById(R.id.edtTglMsk);
        edtTglKlr = findViewById(R.id.edtTglKlr);

        edtTglMsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(BookActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR,year);
                        myCalendar.set(Calendar.MONTH,month);
                        myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                        String myFormat = "dd-MMM-yyyy";
                        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                        edtTglMsk.setText(dateFormat.format(myCalendar.getTime()));
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtTglKlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(BookActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR,year);
                        myCalendar.set(Calendar.MONTH,month);
                        myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                        String myFormat = "dd-MMM-yyyy";
                        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                        edtTglKlr.setText(dateFormat.format(myCalendar.getTime()));
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        // Pilih Jam Msk dan Klr

        edtJamMsk = findViewById(R.id.edtJamMsk);
        edtJamKlr = findViewById(R.id.edtJamKlr);

        edtJamMsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(BookActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                myCalendar.set(Calendar.MINUTE, minute);

                                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
                                edtJamMsk.setText(timeFormat.format(myCalendar.getTime()));
                            }
                        },
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE),
                        true
                ).show();

            }
        });

        edtJamKlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(BookActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                myCalendar.set(Calendar.MINUTE, minute);

                                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
                                edtJamKlr.setText(timeFormat.format(myCalendar.getTime()));
                            }
                        },
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE),
                        true
                ).show();

            }
        });

        btnBook = findViewById(R.id.btnBook);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPaymentSuccess();
            }
        });


        // Bagian Terms n Conditions paling bwh
        TextView txvTerms = findViewById(R.id.txvTerms);
        String fullText = "By paying, you agree to Parkeer’s Terms & Conditions";
        SpannableString spannable = new SpannableString(fullText);

        // Ubah warna bagian "Parkeer’s Terms & Conditions"
        int start = fullText.indexOf("Parkeer’s Terms & Conditions");
        int end = start + "Parkeer’s Terms & Conditions".length();
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#001F54")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txvTerms.setText(spannable);


        sprPlate = findViewById(R.id.sprPlate);

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.plate_array,
                R.layout.spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        sprPlate.setAdapter(adapter);
    }

    public void toPaymentSuccess(){
        Intent intent = new Intent(this, PaymentSuccessActivity.class);
        startActivity(intent);
    }
}