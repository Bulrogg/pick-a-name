package fr.fmi.pickaname.app.accepted;

import android.content.Context;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.accepted.controller.AcceptedController;
import fr.fmi.pickaname.app.accepted.controller.AcceptedControllerDecorator;
import fr.fmi.pickaname.app.accepted.controller.AcceptedControllerImpl;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedPresenterImpl;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedView;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.core.accepted.AcceptedInteractor;
import fr.fmi.pickaname.core.accepted.AcceptedPresenter;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;

@SuppressWarnings("unused")
@Module
class AcceptedModule {

    // TODO à retirer
    private final AcceptedView view;

    AcceptedModule(final AcceptedView view) {
        this.view = view;
    }

    @Provides
    @SingleIn(AcceptedComponent.class)
    AcceptedPresenter providePresenter(final Context context) {
        return new AcceptedPresenterImpl(view, context);
    }

    @Provides
    @SingleIn(AcceptedComponent.class)
    AcceptedInteractor provideInteractor(
            final AcceptedPresenter presenter,
            final ConfigurationRepository configurationRepository
    ) {
        return new AcceptedInteractor(presenter, configurationRepository);
    }

    @Provides
    @SingleIn(AcceptedComponent.class)
    AcceptedController providesController(
            final AcceptedInteractor interactor,
            final Executor executor
    ) {
        final AcceptedController controller = new AcceptedControllerImpl(interactor);
        return new AcceptedControllerDecorator(controller, executor);
    }
}
