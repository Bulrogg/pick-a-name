package fr.fmi.pickaname.app.sort;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.fmi.pickaname.app.ApplicationModule;
import fr.fmi.pickaname.app.sort.controller.SortController;
import fr.fmi.pickaname.app.sort.controller.SortControllerDecorator;
import fr.fmi.pickaname.app.sort.controller.SortControllerImpl;
import fr.fmi.pickaname.app.sort.presentation.SortPresenterImpl;
import fr.fmi.pickaname.app.sort.presentation.SortView;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;
import fr.fmi.pickaname.core.sort.SortInteractor;
import fr.fmi.pickaname.core.sort.SortPresenter;
import fr.fmi.pickaname.repositories.firstname.GetFirstNamesRepositoryImpl;


public class SortModule {

    private final ApplicationModule applicationModule;
    private final SortView view;

    public SortModule(final ApplicationModule applicationModule, final SortView view) {
        this.applicationModule = applicationModule;
        this.view = view;
    }

    public SortController getController() {
        final SortInteractor interactor = new SortInteractor(getPresenter(),
                                                             getFirstNamesRepository());
        final SortController controller = new SortControllerImpl(interactor);
        return new SortControllerDecorator(controller, applicationModule.getAsyncExecutor());
    }

    private GetFirstNamesRepository getFirstNamesRepository() {
        return new GetFirstNamesRepositoryImpl(getObjectMapper());
    }

    private SortPresenter getPresenter() {
        return new SortPresenterImpl(view, applicationModule.getContext());
    }

    private ObjectMapper getObjectMapper() {
        return applicationModule.getMapperModule().getObjectMapper();
    }
}
