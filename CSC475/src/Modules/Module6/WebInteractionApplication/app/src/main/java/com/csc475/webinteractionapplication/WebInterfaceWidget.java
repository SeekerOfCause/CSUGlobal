package com.csc475.webinteractionapplication;


import static android.provider.Settings.Secure.getString;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class WebInterfaceWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Icon widget = Icon.createWithResource(context, R.mipmap.ic_widget_icon);
        // Construct the RemoteViews object
        AppWidgetProviderInfo info = new AppWidgetProviderInfo();
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.web_interface_widget);
        views.setOnClickResponse(views.getLayoutId(), RemoteViews.RemoteResponse.fromFillInIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://form.jotform.com/232098305355153"))));
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}