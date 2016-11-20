package fr.fmi.pickaname.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.app.common.HandlerExecutor;
import fr.fmi.pickaname.app.storage.DeviceStorageImpl;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;
import fr.fmi.pickaname.core.storage.DeviceStorage;
import fr.fmi.pickaname.repositories.configuration.ConfigurationRepositoryImpl;
import fr.fmi.pickaname.repositories.firstname.GetFirstNamesRepositoryImpl;

@SuppressWarnings("unused")
@Module
final class ApplicationModule {

    private final PickANameApplication app;

    ApplicationModule(final PickANameApplication app) {
        this.app = app;
    }

    @Provides
    public Executor getExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    public HandlerExecutor getHandlerExecutor() {
        return new HandlerExecutor();
    }

    @Provides
    public Context getContext() {
        return app;
    }

    @Provides
    @Singleton
    public MapperModule getMapperModule() {
        return new MapperModule();
    }

    @Provides
    public SharedPreferences getSharedPreference(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    public DeviceStorage getDeviceStorage(final SharedPreferences sharedPreferences) {
        return new DeviceStorageImpl(sharedPreferences);
    }

    @Provides
    public ObjectMapper getObjectMapper(final MapperModule mapperModule) {
        return mapperModule.getObjectMapper();
    }

    @Provides
    @Singleton
    public GetFirstNamesRepository getFirstNamesRepository(final ObjectMapper objectMapper) {
        return new GetFirstNamesRepositoryImpl(objectMapper);
    }

    @Provides
    @Singleton
    ConfigurationRepository provideConfigurationRepository(
            final DeviceStorage deviceStorage,
            final ObjectMapper objectMapper
    ) {
        return new ConfigurationRepositoryImpl(deviceStorage, objectMapper);
    }
}
