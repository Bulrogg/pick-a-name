package fr.fmi.pickaname.app.sorting;

import android.content.Context;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.app.sorting.controller.SortingController;
import fr.fmi.pickaname.app.sorting.controller.SortingControllerDecorator;
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

    // TODO à retirer
    private final SortingView view;

    SortingModule(final SortingView view) {
        this.view = view;
    }

    @Provides
    @SingleIn(SortingComponent.class)
    SortingPresenter providePresenter(final Context context) {
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
    SortingController providesController(
            final SortingInteractor interactor,
            final Executor executor
    ) {
        final SortingController controller = new SortingControllerImpl(interactor);
        return new SortingControllerDecorator(controller, executor);
    }
}
