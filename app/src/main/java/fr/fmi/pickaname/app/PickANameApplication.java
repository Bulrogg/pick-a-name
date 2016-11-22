package fr.fmi.pickaname.app;

import android.app.Application;
import android.content.Context;

public class PickANameApplication extends Application {

    private ApplicationComponent component;

    public static PickANameApplication get(final Context context) {
        return (PickANameApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = ApplicationComponent.Initializer.init(this);
        component.inject(this);
    }

    public ApplicationComponent getComponent() {
        return component;
    }

}
