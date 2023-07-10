package com.example.mymortgagecalculator;

import android.graphics.Color;
import android.view.View;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.color.MaterialColors;

import java.util.ArrayList;

public class GetLineData {

    protected static LineData getChartData(ArrayList<Monthly> resultsMonthlyArray, int i, View v) {

        LineData lineEntry = new LineData();
        ArrayList<Entry> paymentEntries = new ArrayList<>();
        ArrayList<Entry> interestEntries = new ArrayList<>();
        ArrayList<Entry> principalEntries = new ArrayList<>();
        float monthlyPayment;

        for (float j = 0; j < (float) resultsMonthlyArray.size() / 12; j++) {

            Monthly monthly = resultsMonthlyArray.get((int) j);

            monthlyPayment = (float) (monthly.getPayment());
            float monthlyInterest = (float) (monthly.getInterest());
            float monthlyPrincipal = (float) (monthly.getPrincipal());

            paymentEntries.add(new Entry(j, monthlyPayment * j * 12f));
            interestEntries.add(new Entry(j, monthlyInterest * j * 12));
            principalEntries.add(new Entry(j, monthlyPrincipal * j * 12));


        }

        LineDataSet monthlyData = new LineDataSet(paymentEntries, "Total Payments");
        LineDataSet interestData = new LineDataSet(interestEntries, "Total Interest");
        LineDataSet principalData = new LineDataSet(principalEntries, "Total Principal");

        switch (i) {

            case 0:
                monthlyData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.rgb(0, 180, 0)));
                interestData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.rgb(80, 180, 80)));
                principalData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.rgb(160, 180, 160)));

                break;
            case 1:
                monthlyData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.BLUE));
                interestData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.rgb(60, 60, 244)));
                principalData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.rgb(120, 120, 244)));

                break;
            case 2:
                monthlyData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.RED));
                interestData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.rgb(244, 60, 60)));
                principalData.setColor(MaterialColors.harmonizeWithPrimary(v.getRootView().findViewById(R.id.scrollView2).getContext(), Color.rgb(244, 120, 120)));
                break;
            default:
                monthlyData.setColor(Color.BLACK);
                interestData.setColor(Color.GRAY);
                principalData.setColor(Color.LTGRAY);
                break;
        }

        monthlyData.setDrawCircles(false);
        interestData.setDrawCircles(false);
        principalData.setDrawCircles(false);
        monthlyData.setDrawValues(false);
        interestData.setDrawValues(false);
        principalData.setDrawValues(false);
        monthlyData.setLineWidth(3.5f);
        interestData.setLineWidth(3f);
        principalData.setLineWidth(3f);
        lineEntry.addDataSet(monthlyData);
        lineEntry.addDataSet(interestData);
        lineEntry.addDataSet(principalData);

        return lineEntry;

    }

}
