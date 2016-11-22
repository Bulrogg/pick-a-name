package fr.fmi.pickaname.app.rejected;

import com.nicolasmouchel.executordecorator.ExecutorDecorator;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.common.HandlerExecutor;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.app.rejected.controller.RejectedController;
import fr.fmi.pickaname.app.rejected.controller.RejectedControllerImpl;
import fr.fmi.pickaname.app.rejected.presentation.RejectedPresenterImpl;
import fr.fmi.pickaname.app.rejected.presentation.RejectedView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.rejected.RejectedInteractor;
import fr.fmi.pickaname.core.rejected.RejectedPresenter;

@SuppressWarnings("unused")
@Module
class RejectedModule {

    RejectedModule() {
    }

    @Provides
    @SingleIn(RejectedComponent.class)
    @ExecutorDecorator(mutable = true)
    RejectedView providesView(final RejectedViewDecorator decorator) {
        return decorator;
    }

    @Provides
    @SingleIn(RejectedComponent.class)
    RejectedViewDecorator providesViewDecorator(final HandlerExecutor handlerExecutor) {
        return new RejectedViewDecorator(handlerExecutor);
    }

    @Provides
    @SingleIn(RejectedComponent.class)
    RejectedPresenter providePresenter(final RejectedView view) {
        return new RejectedPresenterImpl(view);
    }

    @Provides
    @SingleIn(RejectedComponent.class)
    RejectedInteractor provideInteractor(
            final RejectedPresenter presenter,
            final ConfigurationRepository configurationRepository
    ) {
        return new RejectedInteractor(presenter, configurationRepository);
    }

    @Provides
    @SingleIn(RejectedComponent.class)
    @ExecutorDecorator
    RejectedController providesController(
            final RejectedInteractor interactor,
            final Executor executor
    ) {
        final RejectedController controller = new RejectedControllerImpl(interactor);
        return new RejectedControllerDecorator(executor, controller);
    }
}
