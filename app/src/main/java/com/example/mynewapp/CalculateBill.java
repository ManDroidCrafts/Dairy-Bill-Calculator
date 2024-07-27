package com.example.mynewapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.YearMonth;
import java.util.ArrayList;

public class CalculateBill extends AppCompatActivity {

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bill);

        RecyclerView recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        TextView amountTotal = findViewById(R.id.amountTotal);
        TextView litresTotal = findViewById(R.id.litresTotal);
        Button calculate = findViewById(R.id.calculate1);
        ArrayList<BillData> list = new ArrayList<>();

        YearMonth yearMonth = YearMonth.of(AppVariables.selectedYear,AppVariables.selectedMonth+1);
        int daysInMonth = yearMonth.lengthOfMonth();

        int billDays;
        if (AppVariables.selectedBillCycle.equalsIgnoreCase("First Bill"))
            billDays = 15;
        else billDays =(daysInMonth-15);

        for (int i = 1;i <= billDays; i++) {
            list.add(new BillData(0.0,0.0, 0.0, 0.0));
        }

        MyAdapter myAdapter = new MyAdapter(list,amountTotal,litresTotal);
        recyclerView.setAdapter(myAdapter);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountTotal.setVisibility(View.VISIBLE);
                litresTotal.setVisibility(View.VISIBLE);
                //Toast.makeText(view.getContext(), "Cycle selected is" + AppVariables.selectedBillCycle,Toast.LENGTH_LONG).show();
            }
        });
    }
}