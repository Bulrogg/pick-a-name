package fr.fmi.pickaname.app.accepted;

import fr.fmi.pickaname.app.ApplicationModule;
import fr.fmi.pickaname.app.accepted.controller.AcceptedController;
import fr.fmi.pickaname.app.accepted.controller.AcceptedControllerDecorator;
import fr.fmi.pickaname.app.accepted.controller.AcceptedControllerImpl;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedPresenterImpl;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedView;
import fr.fmi.pickaname.core.accepted.AcceptedInteractor;
import fr.fmi.pickaname.core.accepted.AcceptedPresenter;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.repositories.configuration.ConfigurationRepositoryImpl;


public class AcceptedModule {

    private final ApplicationModule appModule;
    private final AcceptedView view;

    public AcceptedModule(final ApplicationModule appModule, final AcceptedView view) {
        this.appModule = appModule;
        this.view = view;
    }

    public AcceptedController getController() {
        final AcceptedInteractor interactor = new AcceptedInteractor(getPresenter(),
                                                                     getConfigurationRepository());
        final AcceptedController controller = new AcceptedControllerImpl(interactor);
        return new AcceptedControllerDecorator(controller, appModule.getAsyncExecutor());
    }

    private ConfigurationRepository getConfigurationRepository() {
        return new ConfigurationRepositoryImpl(appModule.getDeviceStorage(),
                                               appModule.getObjectMapper());
    }

    private AcceptedPresenter getPresenter() {
        return new AcceptedPresenterImpl(view, appModule.getContext());
    }
}
