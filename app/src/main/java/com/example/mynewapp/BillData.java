package com.example.mynewapp;

public class BillData {


    private double amountM;
    private double litreM;
    private double amount;
    private double litre;

    public BillData(double amount, double litre, double amountM, double litreM) {
        this.amount = amount;
        this.litre = litre;
        this.amountM = amountM;
        this.litreM = litreM;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getLitre() {
        return litre;
    }

    public void setLitre(double litre) {
        this.litre = litre;
    }

    public double getAmountM() {
        return amountM;
    }

    public void setAmountM(double amountM) {
        this.amountM = amountM;
    }

    public double getLitreM() {
        return litreM;
    }

    public void setLitreM(double litreM) {
        this.litreM = litreM;
    }
}
