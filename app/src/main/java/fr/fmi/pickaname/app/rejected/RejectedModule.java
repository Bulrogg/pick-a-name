package fr.fmi.pickaname.app.rejected;

import fr.fmi.pickaname.app.ApplicationModule;
import fr.fmi.pickaname.app.rejected.controller.RejectedController;
import fr.fmi.pickaname.app.rejected.controller.RejectedControllerDecorator;
import fr.fmi.pickaname.app.rejected.controller.RejectedControllerImpl;
import fr.fmi.pickaname.app.rejected.presentation.RejectedPresenterImpl;
import fr.fmi.pickaname.app.rejected.presentation.RejectedView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.rejected.RejectedInteractor;
import fr.fmi.pickaname.core.rejected.RejectedPresenter;
import fr.fmi.pickaname.repositories.configuration.ConfigurationRepositoryImpl;

public class RejectedModule {

    private final ApplicationModule appModule;
    private final RejectedView view;

    public RejectedModule(final ApplicationModule appModule, final RejectedView view) {
        this.appModule = appModule;
        this.view = view;
    }

    public RejectedController getController() {
        final RejectedInteractor interactor = new RejectedInteractor(getPresenter(),
                                                                     getConfigurationRepository());
        final RejectedController controller = new RejectedControllerImpl(interactor);
        return new RejectedControllerDecorator(controller, appModule.getAsyncExecutor());
    }

    private ConfigurationRepository getConfigurationRepository() {
        return new ConfigurationRepositoryImpl(appModule.getDeviceStorage(),
                                               appModule.getObjectMapper());
    }

    private RejectedPresenter getPresenter() {
        return new RejectedPresenterImpl(view, appModule.getContext());
    }
}
