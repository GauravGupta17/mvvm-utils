package com.gaurav.dask

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.lifecycle.ViewModel
import com.gaurav.dask.ui.task.TaskVM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Implementation of App Widget functionality.
 */
class TaskWidget : AppWidgetProvider(),KoinComponent {
    private val taskVM:TaskVM by inject()
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {

        }
        // Enter relevant functionality for when the first widget is created


    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.task_widget)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}