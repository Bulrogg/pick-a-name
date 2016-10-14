package fr.fmi.pickaname.app.setting;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.fmi.pickaname.app.ApplicationModule;
import fr.fmi.pickaname.app.setting.controller.SettingsController;
import fr.fmi.pickaname.app.setting.controller.SettingsControllerDecorator;
import fr.fmi.pickaname.app.setting.controller.SettingsControllerImpl;
import fr.fmi.pickaname.app.setting.presentation.SettingsPresenterImpl;
import fr.fmi.pickaname.app.setting.presentation.SettingsView;
import fr.fmi.pickaname.core.settings.SettingsPresenter;


public class SettingsModule {

    private final ApplicationModule applicationModule;
    private final SettingsView view;

    public SettingsModule(final ApplicationModule applicationModule, final SettingsView view) {
        this.applicationModule = applicationModule;
        this.view = view;
    }

    public SettingsController getController() {
        final SettingsController controller = new SettingsControllerImpl();
        return new SettingsControllerDecorator(controller, applicationModule.getAsyncExecutor());
    }

    private SettingsPresenter getPresenter() {
        return new SettingsPresenterImpl(view, applicationModule.getContext());
    }

    private ObjectMapper getObjectMapper() {
        return applicationModule.getMapperModule().getObjectMapper();
    }
}
