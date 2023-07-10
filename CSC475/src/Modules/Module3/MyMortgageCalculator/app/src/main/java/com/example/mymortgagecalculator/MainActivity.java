package com.example.mymortgagecalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;


import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int[] lengthArray = new int[1];

    private boolean optionChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwitchMaterial lengthOpt = findViewById(R.id.lengthOptionButton);
        lengthOpt.setChecked(false);
        lengthOpt.setOnCheckedChangeListener((buttonView, isChecked) -> onClicker(isChecked));

        Slider lengthEntry = findViewById(R.id.mortgageLengthSeek);

        lengthEntry.setStepSize(5f);
        lengthEntry.setValueFrom(0f);
        lengthEntry.setValueTo(30f);
        lengthEntry.setValue(15);
        lengthEntry.setEnabled(false);

        TableLayout results = findViewById(R.id.resultTable);
        results.setVisibility(View.GONE);

        LinearLayout chartView = findViewById(R.id.chartView);
        chartView.setVisibility(View.GONE);


        ScrollView scroll = findViewById(R.id.scrollView2);
        scroll.setBackgroundColor(MaterialColors.harmonizeWithPrimary(this, Color.rgb(40, 30, 40)));

        Button clearButton = findViewById(R.id.calcClearButton);
        Button calculateButton = findViewById(R.id.calcCalculateButton);

        EditText priceEntry = findViewById(R.id.purchasePriceEntry);
        EditText downPaymentEntry = findViewById(R.id.downPaymentEntry);
        EditText interestEntry = findViewById(R.id.interestEntry);



        TextView optionText = findViewById(R.id.lengthOptionSelected);
        optionText.setText(R.string.option_not_checked);

        TextView resultTitle = findViewById(R.id.costsTitle);
        TextView resultTotalTitle = findViewById(R.id.totalCostTitle);

        LineChart chart2 = findViewById(R.id.chart2);
        LineChart chart3 = findViewById(R.id.chart3);
        chart2.setVisibility(View.GONE);
        chart3.setVisibility(View.GONE);


        clearButton.setOnClickListener(e -> clearInput());

        calculateButton.setOnClickListener(e -> {


            lengthArray = new int[1];

            float price;
            float downPayment;
            float interest;


            try {
                price = Float.parseFloat(String.valueOf(priceEntry.getEditableText()));
                downPayment = Float.parseFloat(String.valueOf(downPaymentEntry.getEditableText()));
                interest = Float.parseFloat(String.valueOf(interestEntry.getEditableText()));
                interest = interest / 100f;
                if (optionChecked) {
                    lengthArray[0] = (int) lengthEntry.getValue() * 12;
                } else {
                    lengthArray = new int[]{120, 240, 360};
                }
                chartView.setVisibility(View.VISIBLE);
                results.setVisibility(View.VISIBLE);
                resultTitle.setText(getString(R.string.lengthTitle));
                resultTotalTitle.setText(R.string.monthlyPayment);
                setupCalculation(price, interest, downPayment);


            } catch (Exception exception) {

                Toast toasting = new Toast(this);
                toasting.setText("Please check your entries");
                toasting.show();

            }


        });

    }

    public void setupCalculation(float price, float interest, float downPayment) {

        TextView title1 = findViewById(R.id.chart1text);
        TextView title2 = findViewById(R.id.chart2title);
        TextView title3 = findViewById(R.id.chart3text);
        TextView ten = findViewById(R.id.tenYearTitle);
        TextView twenty = findViewById(R.id.twentyYearTitle);
        TextView thirty = findViewById(R.id.thirtyYearTitle);
        TextView tenTotal = findViewById(R.id.tenYearTotal);
        TextView twentyTotal = findViewById(R.id.twentyYearTotal);
        TextView thirtyTotal = findViewById(R.id.thirtyYearTotal);
        LineChart chart = findViewById(R.id.chart);
        LineChart chart2 = findViewById(R.id.chart2);
        LineChart chart3 = findViewById(R.id.chart3);

        for (int i = 0; i < lengthArray.length; i++) {
            switch (i) {
                case 0:
                    ArrayList<Monthly> monthlyArray0 = new ArrayList<>(CalculateMonthly.calcMonthlyPayment(lengthArray[0], price, interest, downPayment));
                    ten.setText(String.format(getString(R.string.yearTitleString), lengthArray[0] / 12));
                    tenTotal.setText(monthlyArray0.get(0).monthlyString(1));
                    chart.setData(GetLineData.getChartData(monthlyArray0, i, this.getCurrentFocus()));
                    ChartSetup.setChart(chart, title1, this.getCurrentFocus().getRootView());
                    title1.setText(String.format(getString(R.string.yearTitleString), lengthArray[0] / 12));
                    break;
                case 1:
                    ArrayList<Monthly> monthlyArray1 = new ArrayList<>(CalculateMonthly.calcMonthlyPayment(lengthArray[1], price, interest, downPayment));
                    twenty.setText(String.format(getString(R.string.yearTitleString), lengthArray[1] / 12));
                    twentyTotal.setText(monthlyArray1.get(0).monthlyString(1));
                    chart2.setVisibility(View.VISIBLE);
                    chart2.setData(GetLineData.getChartData(monthlyArray1, i, this.getCurrentFocus()));
                    ChartSetup.setChart(chart2, title2, this.getCurrentFocus().getRootView());
                    title2.setText(String.format(getString(R.string.yearTitleString), lengthArray[1] / 12));
                    break;
                case 2:
                    ArrayList<Monthly> monthlyArray2 = new ArrayList<>(CalculateMonthly.calcMonthlyPayment(lengthArray[2], price, interest, downPayment));
                    thirty.setText(String.format(getString(R.string.yearTitleString), lengthArray[2] / 12));
                    System.out.println(lengthArray[2]);
                    thirtyTotal.setText(monthlyArray2.get(0).monthlyString(1));
                    chart3.setVisibility(View.VISIBLE);
                    chart3.setData(GetLineData.getChartData(monthlyArray2, i, this.getCurrentFocus()));
                    ChartSetup.setChart(chart3, title3, this.getCurrentFocus().getRootView());
                    title3.setText(String.format(getString(R.string.yearTitleString), lengthArray[2] / 12));
                    break;
                default:
                    break;
            }

        }
        //
        float max;
        if (lengthArray.length == 3) {
            max = chart3.getAxisLeft().getAxisMaximum();
            YAxis chart2Y = chart2.getAxisLeft();
            YAxis chartY = chart.getAxisLeft();
            chartY.setAxisMaximum(max);
            chart2Y.setAxisMaximum(max);
        }

    }


    public void clear() {
        TextView resultTenYearTitle = findViewById(R.id.tenYearTitle);
        TextView resultTenYearTotal = findViewById(R.id.tenYearTotal);
        TextView resultTwentyYearTitle = findViewById(R.id.twentyYearTitle);
        TextView resultTwentyYearTotal = findViewById(R.id.twentyYearTotal);
        TextView resultThirtyYearTitle = findViewById(R.id.thirtyYearTitle);
        TextView resultThirtyYearTotal = findViewById(R.id.thirtyYearTotal);
        TextView title1 = findViewById(R.id.chart1text);
        TextView title2 = findViewById(R.id.chart2title);
        TextView title3 = findViewById(R.id.chart3text);
        TableLayout results = findViewById(R.id.resultTable);

        LinearLayout chartView = findViewById(R.id.chartView);

        LineChart chart1 = findViewById(R.id.chart);
        chart1.setData(null);
        title1.setVisibility(View.GONE);

        chart1.setVisibility(View.GONE);
        LineChart chart2 = findViewById(R.id.chart2);
        chart2.setData(null);
        title2.setVisibility(View.GONE);

        chart2.setVisibility(View.GONE);
        LineChart chart3 = findViewById(R.id.chart3);
        chart3.setData(null);
        title3.setVisibility(View.GONE);

        chart3.setVisibility(View.GONE);

        chartView.setVisibility(View.GONE);
        results.setVisibility(View.GONE);
        resultTenYearTitle.setText("");
        resultTenYearTotal.setText("");
        resultTwentyYearTitle.setText("");
        resultTwentyYearTotal.setText("");
        resultThirtyYearTitle.setText("");
        resultThirtyYearTotal.setText("");
        lengthArray = null;
    }

    private void onClicker(boolean bool) {
        TextView text = findViewById(R.id.lengthOptionSelected);
        text.setText(R.string.option_not_checked);
        findViewById(R.id.mortgageLengthSeek).setEnabled(bool);
        optionChecked = bool;
        clear();
    }

    private void clearInput() {
        EditText price = findViewById(R.id.purchasePriceEntry);
        price.setText("");
        EditText down = findViewById(R.id.downPaymentEntry);
        down.setText("");
        EditText interest = findViewById(R.id.interestEntry);
        interest.setText("");
        Slider seek = findViewById(R.id.mortgageLengthSeek);
        seek.setValue(15);
        clear();
    }

}