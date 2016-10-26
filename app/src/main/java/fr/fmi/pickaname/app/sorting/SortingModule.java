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

    private final ApplicationModule applicationModule;
    private final SortingView view;

    public SortingModule(final ApplicationModule applicationModule, final SortingView view) {
        this.applicationModule = applicationModule;
        this.view = view;
    }

    public SortingController getController() {
        final SortingInteractor interactor = new SortingInteractor(getPresenter(),
                                                                   getFirstNamesRepository(),
                                                                   getConfigurationRepository());
        final SortingController controller = new SortingControllerImpl(interactor);
        return new SortingControllerDecorator(controller, applicationModule.getAsyncExecutor());
    }

    private ConfigurationRepository getConfigurationRepository() {
        return new ConfigurationRepositoryImpl(getObjectMapper());
    }

    private GetFirstNamesRepository getFirstNamesRepository() {
        return new GetFirstNamesRepositoryImpl(getObjectMapper());
    }

    private SortingPresenter getPresenter() {
        return new SortingPresenterImpl(view, applicationModule.getContext());
    }

    private ObjectMapper getObjectMapper() {
        return applicationModule.getMapperModule().getObjectMapper();
    }
}
