package fr.fmi.pickaname.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;
import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.app.common.HandlerExecutor;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;
import fr.fmi.pickaname.core.storage.DeviceStorage;

@SuppressWarnings("unused")
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    final class Initializer {

        private Initializer() {
            // static class
        }

        static ApplicationComponent init(final PickANameApplication app) {
            return DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(app))
                    .build();
        }

    }

    void inject(PickANameApplication cosmoApplication);

    Executor executorProvider();

    HandlerExecutor handlerExecutorProvider();

    Context contextProvider();

    MapperModule mapperModuleProvider();

    SharedPreferences sharedPreferencesProvider();

    DeviceStorage deviceStorageProvider();

    ObjectMapper objectMapperProvider();

    GetFirstNamesRepository getFirstNamesRepositoryProvider();

}