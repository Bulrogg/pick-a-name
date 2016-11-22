package fr.fmi.pickaname.app.accepted;

import com.nicolasmouchel.executordecorator.ExecutorDecorator;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.accepted.controller.AcceptedController;
import fr.fmi.pickaname.app.accepted.controller.AcceptedControllerImpl;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedPresenterImpl;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedView;
import fr.fmi.pickaname.app.common.HandlerExecutor;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.core.accepted.AcceptedInteractor;
import fr.fmi.pickaname.core.accepted.AcceptedPresenter;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;

@SuppressWarnings("unused")
@Module
class AcceptedModule {

    AcceptedModule() {
    }

    @Provides
    @SingleIn(AcceptedComponent.class)
    @ExecutorDecorator(mutable = true)
    AcceptedView providesView(final AcceptedViewDecorator decorator) {
        return decorator;
    }

    @Provides
    @SingleIn(AcceptedComponent.class)
    AcceptedViewDecorator providesViewDecorator(final HandlerExecutor handlerExecutor) {
        return new AcceptedViewDecorator(handlerExecutor);
    }

    @Provides
    @SingleIn(AcceptedComponent.class)
    AcceptedPresenter providePresenter(final AcceptedView view) {
        return new AcceptedPresenterImpl(view);
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
    @ExecutorDecorator
    AcceptedController providesController(
            final AcceptedInteractor interactor,
            final Executor executor
    ) {
        final AcceptedController controller = new AcceptedControllerImpl(interactor);
        return new AcceptedControllerDecorator(executor, controller);
    }
}
