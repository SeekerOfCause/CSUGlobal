package com.example.mymortgagecalculator;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculateMonthly {

    protected static ArrayList<Monthly> calcMonthlyPayment(int in, double priceIn, double interestIn, double downPaymentIn) {

        ArrayList<Monthly> monthlyEntries = new ArrayList<>();

        float price = (float) priceIn;
        float interest = (float) interestIn / 12.0f;
        float downPayment = (float) downPaymentIn;
        float i = (float) in;

        float priceTrack;
        priceTrack = price;
        for (int k = 0; k < i; k++) {

            Monthly month;

            float monthlyPay = ((price - downPayment) * (interest) * (((float) Math.pow((1f + interest), i) / ((float) Math.pow((1f + interest), i) - 1f))));
            float monthlyPri = monthlyPay - ((priceTrack) * (interest));
            float monthlyInt = monthlyPay - monthlyPri;

            priceTrack -= monthlyPri;

            month = new Monthly(monthlyPay, monthlyInt, monthlyPri);
            monthlyEntries.add(month);

        }
        return monthlyEntries;
    }


}
