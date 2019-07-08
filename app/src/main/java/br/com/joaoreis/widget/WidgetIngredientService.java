package br.com.joaoreis.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetIngredientService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewFactory(this);
    }
}
