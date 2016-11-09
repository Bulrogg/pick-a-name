package fr.fmi.pickaname.app.reinit;

import fr.fmi.pickaname.app.ApplicationModule;
import fr.fmi.pickaname.app.reinit.controller.ReinitController;
import fr.fmi.pickaname.app.reinit.controller.ReinitControllerDecorator;
import fr.fmi.pickaname.app.reinit.controller.ReinitControllerImpl;
import fr.fmi.pickaname.app.reinit.presentation.ReinitPresenterImpl;
import fr.fmi.pickaname.app.reinit.presentation.ReinitView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.reinit.ReinitInteractor;
import fr.fmi.pickaname.core.reinit.ReinitPresenter;
import fr.fmi.pickaname.repositories.configuration.ConfigurationRepositoryImpl;


public class ReinitModule {

    private final ApplicationModule appModule;
    private final ReinitView view;

    public ReinitModule(final ApplicationModule appModule, final ReinitView view) {
        this.appModule = appModule;
        this.view = view;
    }

    public ReinitController getController() {
        final ReinitInteractor interactor = new ReinitInteractor(getPresenter(),
                                                                 getConfigurationRepository());
        final ReinitController controller = new ReinitControllerImpl(interactor);
        return new ReinitControllerDecorator(controller, appModule.getAsyncExecutor());
    }

    private ConfigurationRepository getConfigurationRepository() {
        return new ConfigurationRepositoryImpl(appModule.getDeviceStorage(),
                                               appModule.getObjectMapper());
    }

    private ReinitPresenter getPresenter() {
        return new ReinitPresenterImpl(view, appModule.getContext());
    }
}
