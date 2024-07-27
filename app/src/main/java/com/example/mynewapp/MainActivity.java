package com.example.mynewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonProceed = findViewById(R.id.proceed);
        datePicker = findViewById(R.id.datePicker);
        Spinner spinner = findViewById(R.id.selectBillCycle);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.billingCycles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 showMonthAndYearPicker();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String selectedCycle = adapterView.getItemAtPosition(pos).toString();
                AppVariables.selectedBillCycle = selectedCycle;
                //Toast.makeText(MainActivity.this,"You are selected :" + selectedCycle,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppVariables.selectedDate.isEmpty() && !AppVariables.selectedBillCycle.equalsIgnoreCase("Select Bill Cycle")) {
                    Intent intent = new Intent(MainActivity.this, CalculateBill.class);
                    startActivity(intent);
                } else if (AppVariables.selectedDate.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please Select Valid Date",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Please Select Valid Bill Cycle",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void showMonthAndYearPicker() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_month_year_picker);

        final NumberPicker monthPicker = dialog.findViewById(R.id.monthPicker);
        final NumberPicker yearPicker = dialog.findViewById(R.id.yearPicker);
        Button btnSet = dialog.findViewById(R.id.btnSet);

        String[] months = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(months.length-1);
        monthPicker.setDisplayedValues(months);

        int year = Calendar.getInstance().get(Calendar.YEAR);
        yearPicker.setMinValue(year-1);
        yearPicker.setMaxValue(year);


        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppVariables.selectedMonth = monthPicker.getValue();
                AppVariables.selectedYear = yearPicker.getValue();
                AppVariables.selectedDate = months[monthPicker.getValue()] + "/" + AppVariables.selectedYear;
                datePicker.setText(AppVariables.selectedDate);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showDatePickerDialog() {
        //Get current Date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int dayOfMonth, int monthSelected, int yearSelected) {
                String selectedDate = yearSelected + "/" + (monthSelected+1) + "/" + dayOfMonth;
                datePicker.setText(selectedDate);
            }
        },year,month,day);

        datePickerDialog.show();
    }
}