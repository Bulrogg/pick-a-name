package fr.fmi.pickaname.app;

import android.app.Application;
import android.content.Context;

public class PickANameApplication extends Application {

    private ApplicationModule applicationModule;

    public static ApplicationModule getApplicationModule(final Context context) {
        final Context applicationContext = context.getApplicationContext();
        return ((PickANameApplication) applicationContext).getApplicationModule();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationModule = new ApplicationModule(this);
    }

    public ApplicationModule getApplicationModule() {
        return applicationModule;
    }
}
