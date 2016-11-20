package fr.fmi.pickaname.app.rejected;

import android.content.Context;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import fr.fmi.pickaname.app.common.SingleIn;
import fr.fmi.pickaname.app.rejected.controller.RejectedController;
import fr.fmi.pickaname.app.rejected.controller.RejectedControllerDecorator;
import fr.fmi.pickaname.app.rejected.controller.RejectedControllerImpl;
import fr.fmi.pickaname.app.rejected.presentation.RejectedPresenterImpl;
import fr.fmi.pickaname.app.rejected.presentation.RejectedView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.rejected.RejectedInteractor;
import fr.fmi.pickaname.core.rejected.RejectedPresenter;

@SuppressWarnings("unused")
@Module
class RejectedModule {

    // TODO à retirer
    private final RejectedView view;

    RejectedModule(final RejectedView view) {
        this.view = view;
    }

    @Provides
    @SingleIn(RejectedComponent.class)
    RejectedPresenter providePresenter(final Context context) {
        return new RejectedPresenterImpl(view, context);
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
    RejectedController providesController(
            final RejectedInteractor interactor,
            final Executor executor
    ) {
        final RejectedController controller = new RejectedControllerImpl(interactor);
        return new RejectedControllerDecorator(controller, executor);
    }
}
