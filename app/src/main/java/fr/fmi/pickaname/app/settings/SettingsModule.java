package fr.fmi.pickaname.app.settings;

import android.content.Context;

import com.nicolasmouchel.executordecorator.ExecutorDecorator;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.common.HandlerExecutor;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.app.settings.controller.SettingsController;
import fr.fmi.pickaname.app.settings.controller.SettingsControllerImpl;
import fr.fmi.pickaname.app.settings.presentation.SettingsPresenterImpl;
import fr.fmi.pickaname.app.settings.presentation.SettingsView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.settings.SettingsInteractor;
import fr.fmi.pickaname.core.settings.SettingsPresenter;

@SuppressWarnings("unused")
@Module
class SettingsModule {

    SettingsModule() {
    }

    @Provides
    @SingleIn(SettingsComponent.class)
    @ExecutorDecorator(mutable = true)
    SettingsView providesView(final SettingsViewDecorator decorator) {
        return decorator;
    }

    @Provides
    @SingleIn(SettingsComponent.class)
    SettingsViewDecorator providesViewDecorator(final HandlerExecutor handlerExecutor) {
        return new SettingsViewDecorator(handlerExecutor);
    }

    @Provides
    @SingleIn(SettingsComponent.class)
    SettingsPresenter providePresenter(final SettingsView view, final Context context) {
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
    @ExecutorDecorator
    SettingsController providesController(
            final SettingsInteractor interactor,
            final Executor executor
    ) {
        final SettingsController controller = new SettingsControllerImpl(interactor);
        return new SettingsControllerDecorator(executor, controller);
    }
}
