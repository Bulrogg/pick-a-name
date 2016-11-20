package fr.fmi.pickaname.app.reinit;

import android.content.Context;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.app.reinit.controller.ReinitController;
import fr.fmi.pickaname.app.reinit.controller.ReinitControllerDecorator;
import fr.fmi.pickaname.app.reinit.controller.ReinitControllerImpl;
import fr.fmi.pickaname.app.reinit.presentation.ReinitPresenterImpl;
import fr.fmi.pickaname.app.reinit.presentation.ReinitView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.reinit.ReinitInteractor;
import fr.fmi.pickaname.core.reinit.ReinitPresenter;

@SuppressWarnings("unused")
@Module
class ReinitModule {

    // TODO à retirer
    private final ReinitView view;

    ReinitModule(final ReinitView view) {
        this.view = view;
    }

    @Provides
    @SingleIn(ReinitComponent.class)
    ReinitPresenter providePresenter(final Context context) {
        return new ReinitPresenterImpl(view, context);
    }

    @Provides
    @SingleIn(ReinitComponent.class)
    ReinitInteractor provideInteractor(
            final ReinitPresenter presenter,
            final ConfigurationRepository configurationRepository
    ) {
        return new ReinitInteractor(presenter, configurationRepository);
    }

    @Provides
    @SingleIn(ReinitComponent.class)
    ReinitController providesController(
            final ReinitInteractor interactor,
            final Executor executor
    ) {
        final ReinitController controller = new ReinitControllerImpl(interactor);
        return new ReinitControllerDecorator(controller, executor);
    }
}
