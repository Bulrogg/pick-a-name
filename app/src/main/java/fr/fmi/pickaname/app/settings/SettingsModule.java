package fr.fmi.pickaname.app.settings;

import android.content.Context;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.app.settings.controller.SettingsController;
import fr.fmi.pickaname.app.settings.controller.SettingsControllerDecorator;
import fr.fmi.pickaname.app.settings.controller.SettingsControllerImpl;
import fr.fmi.pickaname.app.settings.presentation.SettingsPresenterImpl;
import fr.fmi.pickaname.app.settings.presentation.SettingsView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.settings.SettingsInteractor;
import fr.fmi.pickaname.core.settings.SettingsPresenter;

@SuppressWarnings("unused")
@Module
class SettingsModule {

    // TODO à retirer
    private final SettingsView view;

    SettingsModule(final SettingsView view) {
        this.view = view;
    }

    @Provides
    @SingleIn(SettingsComponent.class)
    SettingsPresenter providePresenter(final Context context) {
        return new SettingsPresenterImpl(view, context);
    }

    @Provides
    @SingleIn(SettingsComponent.class)
    SettingsInteractor provideInteractor(
            final SettingsPresenter presenter,
            final ConfigurationRepository configurationRepository
    ) {
        return new SettingsInteractor(presenter, configurationRepository);
    }

    @Provides
    @SingleIn(SettingsComponent.class)
    SettingsController providesController(
            final SettingsInteractor interactor,
            final Executor executor
    ) {
        final SettingsController controller = new SettingsControllerImpl(interactor);
        return new SettingsControllerDecorator(controller, executor);
    }
}
