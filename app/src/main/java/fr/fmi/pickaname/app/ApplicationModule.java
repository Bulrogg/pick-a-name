package fr.fmi.pickaname.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.app.storage.DeviceStorageImpl;
import fr.fmi.pickaname.core.storage.DeviceStorage;

public class ApplicationModule {

    private final Context context;
    private final Executor asyncExecutor;
    private final MapperModule mapperModule;

    public ApplicationModule(final Context context) {
        this.context = context;
        this.asyncExecutor = Executors.newSingleThreadExecutor();
        this.mapperModule = new MapperModule();
    }

    public Executor getAsyncExecutor() {
        return asyncExecutor;
    }

    public Context getContext() {
        return context;
    }

    public MapperModule getMapperModule() {
        return mapperModule;
    }

    public SharedPreferences getSharedPreference() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public DeviceStorage getDeviceStorage() {
        return new DeviceStorageImpl(getSharedPreference());
    }

}
