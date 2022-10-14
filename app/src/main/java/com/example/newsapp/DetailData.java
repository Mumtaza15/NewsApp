package com.example.newsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DetailData extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button searchButton;
    EditText datePicker;
    Spinner kategori;
    TextView batasUmur;
    int kelayakan;

    public static final String MESSAGE_EXTRA = "MESSAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        searchButton = findViewById(R.id.btn_search);
        datePicker = findViewById(R.id.btn_datepicker);
        kategori = findViewById(R.id.label_spinner);
        batasUmur = findViewById(R.id.target_usia);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(datePicker.getText().toString())) {
                    Toast.makeText(DetailData.this, "ISI TANGGAL LAHIR TERLEBIH DAHULU!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(DetailData.this,"BERHASIL MENCARI!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), KategoriBerita.class);

                    String message = kategori.getSelectedItem().toString();
                    int pesan = kelayakan;
                    intent.putExtra(MESSAGE_EXTRA, message);
                    intent.putExtra("kodeUmur", pesan);

                    startActivity(intent);
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this,R.array.labels_array, android.R.layout.simple_spinner_item);
        kategori.setAdapter(adapter);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void processDatePickerResult(int day, int month, int year) {
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month);
        String year_string = Integer.toString(year);

        String dateMessage = day_string + "/" + month_string + "/" + year_string;

        datePicker.setText(dateMessage);

        kelayakan = (2022-year);
        String umur = Integer.toString(kelayakan);
        batasUmur.setText("Umur Anda: "+umur);
    }

}