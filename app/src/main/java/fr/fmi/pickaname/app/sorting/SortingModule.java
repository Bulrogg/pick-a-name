package fr.fmi.pickaname.app.sorting;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.fmi.pickaname.app.ApplicationModule;
import fr.fmi.pickaname.app.sorting.controller.SortingController;
import fr.fmi.pickaname.app.sorting.controller.SortingControllerDecorator;
import fr.fmi.pickaname.app.sorting.controller.SortingControllerImpl;
import fr.fmi.pickaname.app.sorting.presentation.SortingPresenterImpl;
import fr.fmi.pickaname.app.sorting.presentation.SortingView;
import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;
import fr.fmi.pickaname.core.sort.SortingInteractor;
import fr.fmi.pickaname.core.sort.SortingPresenter;
import fr.fmi.pickaname.repositories.configuration.ConfigurationRepositoryImpl;
import fr.fmi.pickaname.repositories.firstname.GetFirstNamesRepositoryImpl;


public class SortingModule {

    private final ApplicationModule appModule;
    private final SortingView view;

    public SortingModule(final ApplicationModule appModule, final SortingView view) {
        this.appModule = appModule;
        this.view = view;
    }

    public SortingController getController() {
        final SortingInteractor interactor = new SortingInteractor(getPresenter(),
                                                                   getFirstNamesRepository(),
                                                                   getConfigurationRepository());
        final SortingController controller = new SortingControllerImpl(interactor);
        return new SortingControllerDecorator(controller, appModule.getAsyncExecutor());
    }

    private ConfigurationRepository getConfigurationRepository() {
        return new ConfigurationRepositoryImpl(appModule.getDeviceStorage(), getObjectMapper());
    }

    private GetFirstNamesRepository getFirstNamesRepository() {
        return new GetFirstNamesRepositoryImpl(getObjectMapper());
    }

    private SortingPresenter getPresenter() {
        return new SortingPresenterImpl(view, appModule.getContext());
    }

    private ObjectMapper getObjectMapper() {
        return appModule.getMapperModule().getObjectMapper();
    }
}
