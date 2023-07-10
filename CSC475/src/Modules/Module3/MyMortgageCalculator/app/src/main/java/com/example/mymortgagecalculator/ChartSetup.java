package com.example.mymortgagecalculator;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.google.android.material.color.MaterialColors;

public class ChartSetup {

    protected static void setChart(LineChart chart, TextView title, View v) {

        title.setVisibility(View.VISIBLE);

        chart.getDescription().setEnabled(false);
        chart.setVisibility(View.VISIBLE);
        chart.invalidate();

        YAxis yAxis = chart.getAxisLeft();
        XAxis xAxis = chart.getXAxis();
        xAxis.setTextColor(MaterialColors.harmonizeWithPrimary(v.findViewById(R.id.scrollView2).getContext(), Color.LTGRAY));
        yAxis.setTextColor(MaterialColors.harmonizeWithPrimary(v.findViewById(R.id.scrollView2).getContext(), Color.LTGRAY));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setGridBackgroundColor(MaterialColors.harmonizeWithPrimary(v.findViewById(R.id.scrollView2).getContext(), Color.LTGRAY));
        chart.setDrawGridBackground(false);
        chart.getLegend().setTextColor(MaterialColors.harmonizeWithPrimary(v.findViewById(R.id.scrollView2).getContext(), Color.LTGRAY));
        chart.getAxisRight().setEnabled(false);
        chart.setBackgroundColor(MaterialColors.harmonizeWithPrimary(v.findViewById(R.id.scrollView2).getContext(), Color.rgb(80, 60, 80)));
        yAxis.setDrawLabels(true);
        yAxis.setAxisLineColor(MaterialColors.harmonizeWithPrimary(v.findViewById(R.id.scrollView2).getContext(), Color.LTGRAY));
        xAxis.setAxisLineColor(MaterialColors.harmonizeWithPrimary(v.findViewById(R.id.scrollView2).getContext(), Color.LTGRAY));

        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

    }

}
