package com.example.mynewapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Logger;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<BillData> items;
    private final TextView amountTotal;
    private final TextView litresTotal;

    String[] months = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    public MyAdapter(List<BillData> items, TextView amountTotal, TextView litresTotal) {
        this.items = items;
        this.amountTotal = amountTotal;
        this.litresTotal = litresTotal;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_layout,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final BillData item = items.get(position);

        Log.d("manoj"," : "+item.getAmount());
        holder.amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if(!charSequence.toString().isEmpty()) {
                        item.setAmount(Double.parseDouble(charSequence.toString()));
                        holder.amount.setError(null);
                    }
                } catch (Exception e){
                    item.setAmount(0);
                    holder.amount.setError((CharSequence) e);
                }
               sumAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        holder.amountM.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if(!charSequence.toString().isEmpty()) {
                        item.setAmountM(Double.parseDouble(charSequence.toString()));
                        holder.amountM.setError(null);
                    }
                } catch (Exception e){
                    item.setAmountM(0);
                    holder.amountM.setError((CharSequence) e);
                }
                sumAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        holder.litres.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               try{
                   if(!charSequence.toString().isEmpty()) {
                       item.setLitre(Double.parseDouble(charSequence.toString()));
                   }
               } catch (Exception e){
                   //
               }
               sumLitres();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.litresM.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    if(!charSequence.toString().isEmpty()) {
                        item.setLitreM(Double.parseDouble(charSequence.toString()));
                    }
                } catch (Exception e){
                    //
                }
                sumLitres();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (AppVariables.selectedBillCycle.equalsIgnoreCase("First Bill")) {
            holder.serialNo.setText(months[AppVariables.selectedMonth]+"/"+ String.format("%02d",position+1)+"\n"+AppVariables.selectedYear);
        } else if (AppVariables.selectedBillCycle.equalsIgnoreCase("Second Bill")) {
            holder.serialNo.setText(months[AppVariables.selectedMonth]+"/"+ String.format("%02d",position+16)+"\n"+AppVariables.selectedYear);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void sumLitres() {
        double sum = 0.0;
        for(BillData item : items) {
            sum += item.getLitre()+item.getLitreM();
        }
        litresTotal.setText(""+sum);
    }

    private void sumAmount() {
        double sum = 0.0;
        for (BillData item : items) {
            sum += item.getAmount()+item.getAmountM();
        }
        amountTotal.setText("" + sum);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView serialNo;
        public EditText amount;
        public EditText litres;
        public EditText amountM;
        public EditText litresM;
        public ViewHolder(View view) {
             super(view);
            amount = view.findViewById(R.id.amount);
            litres = view.findViewById(R.id.litres);
            amountM = view.findViewById(R.id.amountM);
            litresM = view.findViewById(R.id.litresM);
            serialNo = view.findViewById(R.id.serialNo);
        }
    }
}
