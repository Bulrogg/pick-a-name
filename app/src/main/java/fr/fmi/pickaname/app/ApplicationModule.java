package fr.fmi.pickaname.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.app.storage.DeviceStorageImpl;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;
import fr.fmi.pickaname.core.storage.DeviceStorage;
import fr.fmi.pickaname.repositories.firstname.GetFirstNamesRepositoryImpl;

public class ApplicationModule {

    private final Context context;
    private final Executor asyncExecutor;
    private final MapperModule mapperModule;
    private final GetFirstNamesRepository getFirstNamesRepository;

    public ApplicationModule(final Context context) {
        this.context = context;
        this.asyncExecutor = Executors.newSingleThreadExecutor();
        this.mapperModule = new MapperModule();
        this.getFirstNamesRepository = new GetFirstNamesRepositoryImpl(mapperModule.getObjectMapper());
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

    public GetFirstNamesRepository getFirstNamesRepository() {
        return getFirstNamesRepository;
    }

    public ObjectMapper getObjectMapper() {
        return mapperModule.getObjectMapper();
    }

}
