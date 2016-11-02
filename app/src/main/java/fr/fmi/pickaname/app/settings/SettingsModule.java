package fr.fmi.pickaname.app.settings;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.fmi.pickaname.app.ApplicationModule;
import fr.fmi.pickaname.app.settings.controller.SettingsController;
import fr.fmi.pickaname.app.settings.controller.SettingsControllerDecorator;
import fr.fmi.pickaname.app.settings.controller.SettingsControllerImpl;
import fr.fmi.pickaname.app.settings.presentation.SettingsPresenterImpl;
import fr.fmi.pickaname.app.settings.presentation.SettingsView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.settings.SettingsInteractor;
import fr.fmi.pickaname.core.settings.SettingsPresenter;
import fr.fmi.pickaname.repositories.configuration.ConfigurationRepositoryImpl;


public class SettingsModule {

    private final ApplicationModule appModule;
    private final SettingsView view;

    public SettingsModule(final ApplicationModule appModule, final SettingsView view) {
        this.appModule = appModule;
        this.view = view;
    }

    public SettingsController getController() {
        final SettingsController controller = new SettingsControllerImpl(getSettingsInteractor());
        return new SettingsControllerDecorator(controller, appModule.getAsyncExecutor());
    }

    private SettingsPresenter getPresenter() {
        return new SettingsPresenterImpl(view, appModule.getContext());
    }

    private ObjectMapper getObjectMapper() {
        return appModule.getMapperModule().getObjectMapper();
    }

    private SettingsInteractor getSettingsInteractor() {
        return new SettingsInteractor(getPresenter(), getConfigurationRepository());
    }

    private ConfigurationRepository getConfigurationRepository() {
        return new ConfigurationRepositoryImpl(appModule.getDeviceStorage(), getObjectMapper());
    }
}
