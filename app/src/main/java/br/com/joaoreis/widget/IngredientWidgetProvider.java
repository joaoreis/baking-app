package br.com.joaoreis.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.List;

import br.com.joaoreis.bakingapp.R;
import br.com.joaoreis.bakingapp.service.models.Ingredient;
import br.com.joaoreis.bakingapp.service.models.Recipe;

import static br.com.joaoreis.bakingapp.Constants.EXTRA_RECIPE;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidgetProvider extends AppWidgetProvider {

    private static List<Ingredient> ingredients;

    public static List<Ingredient> getIngredients() {
        return ingredients;
    }

    public static void updateAllWidgets(Context context, AppWidgetManager appWidgetManager,
                                        int[] appWidgetIds, Recipe recipe) {
        for (int widgetId : appWidgetIds) {
            updateAppWidget(context,appWidgetManager, widgetId, recipe);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Recipe recipe) {

        int layoutId = R.layout.ingredient_widget_provider;
        RemoteViews views = new RemoteViews(context.getPackageName(), layoutId);
        Intent intent = new Intent(context, WidgetIngredientService.class);
        views.setRemoteAdapter(R.id.widget_list, intent);
        if (recipe != null) {
            ingredients = recipe.getIngredients();
            views.setTextViewText(R.id.recipe_title, recipe.getName());
        }
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.widget_list);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void sendRefreshBroadcast(Context context, Recipe recipe) {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_RECIPE, recipe);
        intent.putExtras(bundle);
        intent.setComponent(new ComponentName(context, IngredientWidgetProvider.class));
        context.sendBroadcast(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action != null && action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            // refresh all your widgets
            Recipe recipe = intent.getParcelableExtra(EXTRA_RECIPE);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] widgetIds = appWidgetManager.getAppWidgetIds(intent.getComponent());
            IngredientWidgetProvider.updateAllWidgets(context,appWidgetManager,widgetIds,recipe);
        }
        super.onReceive(context, intent);
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

