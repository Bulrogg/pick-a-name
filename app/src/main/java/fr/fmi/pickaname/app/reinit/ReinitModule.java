package fr.fmi.pickaname.app.reinit;

import android.content.Context;

import com.nicolasmouchel.executordecorator.ExecutorDecorator;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.common.HandlerExecutor;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.app.reinit.controller.ReinitController;
import fr.fmi.pickaname.app.reinit.controller.ReinitControllerImpl;
import fr.fmi.pickaname.app.reinit.presentation.ReinitPresenterImpl;
import fr.fmi.pickaname.app.reinit.presentation.ReinitView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.reinit.ReinitInteractor;
import fr.fmi.pickaname.core.reinit.ReinitPresenter;

@SuppressWarnings("unused")
@Module
class ReinitModule {

    ReinitModule() {
    }

    @Provides
    @SingleIn(ReinitComponent.class)
    @ExecutorDecorator(mutable = true)
    ReinitView providesView(final ReinitViewDecorator decorator) {
        return decorator;
    }

    @Provides
    @SingleIn(ReinitComponent.class)
    ReinitViewDecorator providesViewDecorator(final HandlerExecutor handlerExecutor) {
        return new ReinitViewDecorator(handlerExecutor);
    }

    @Provides
    @SingleIn(ReinitComponent.class)
    ReinitPresenter providePresenter(final ReinitView view, final Context context) {
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
    @ExecutorDecorator
    ReinitController providesController(
            final ReinitInteractor interactor,
            final Executor executor
    ) {
        final ReinitController controller = new ReinitControllerImpl(interactor);
        return new ReinitControllerDecorator(executor, controller);
    }
}
