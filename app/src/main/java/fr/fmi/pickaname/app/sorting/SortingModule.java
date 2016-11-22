package fr.fmi.pickaname.app.sorting;

import android.content.Context;

import com.nicolasmouchel.executordecorator.ExecutorDecorator;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.common.HandlerExecutor;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.app.sorting.controller.SortingController;
import fr.fmi.pickaname.app.sorting.controller.SortingControllerImpl;
import fr.fmi.pickaname.app.sorting.presentation.SortingPresenterImpl;
import fr.fmi.pickaname.app.sorting.presentation.SortingView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;
import fr.fmi.pickaname.core.sort.SortingInteractor;
import fr.fmi.pickaname.core.sort.SortingPresenter;

@SuppressWarnings("unused")
@Module
class SortingModule {

    SortingModule() {
    }

    @Provides
    @SingleIn(SortingComponent.class)
    @ExecutorDecorator(mutable = true)
    SortingView providesView(final SortingViewDecorator decorator) {
        return decorator;
    }

    @Provides
    @SingleIn(SortingComponent.class)
    SortingViewDecorator providesViewDecorator(final HandlerExecutor handlerExecutor) {
        return new SortingViewDecorator(handlerExecutor);
    }

    @Provides
    @SingleIn(SortingComponent.class)
    SortingPresenter providePresenter(final SortingView view, final Context context) {
        return new SortingPresenterImpl(view, context);
    }

    @Provides
    @SingleIn(SortingComponent.class)
    SortingInteractor provideInteractor(
            final SortingPresenter presenter,
            final GetFirstNamesRepository getFirstNamesRepository,
            final ConfigurationRepository configurationRepository
    ) {
        return new SortingInteractor(presenter, getFirstNamesRepository, configurationRepository);
    }

    @Provides
    @SingleIn(SortingComponent.class)
    @ExecutorDecorator
    SortingController providesController(
            final SortingInteractor interactor,
            final Executor executor
    ) {
        final SortingController controller = new SortingControllerImpl(interactor);
        return new SortingControllerDecorator(executor, controller);
    }
}
