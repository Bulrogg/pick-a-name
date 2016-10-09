package fr.fmi.pickaname.app.sort;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.fmi.pickaname.app.ApplicationModule;
import fr.fmi.pickaname.app.sort.controller.SortController;
import fr.fmi.pickaname.app.sort.controller.SortControllerDecorator;
import fr.fmi.pickaname.app.sort.controller.SortControllerImpl;
import fr.fmi.pickaname.app.sort.presentation.SortPresenterImpl;
import fr.fmi.pickaname.app.sort.presentation.SortView;
import fr.fmi.pickaname.core.login.LoginInteractor;
import fr.fmi.pickaname.core.login.LoginPresenter;
import fr.fmi.pickaname.core.login.LoginRepository;
import fr.fmi.pickaname.repositories.login.FakeLoginRepository;
import fr.fmi.pickaname.repositories.login.SimulateDelayLoginRepository;

public class SortModule {

    private final ApplicationModule applicationModule;
    private final SortView view;

    public SortModule(final ApplicationModule applicationModule, final SortView view) {
        this.applicationModule = applicationModule;
        this.view = view;
    }

    public SortController getController() {
        final LoginInteractor interactor = new LoginInteractor(getRepository(), getPresenter());
        final SortControllerImpl controller = new SortControllerImpl(interactor);
        return new SortControllerDecorator(controller, applicationModule.getAsyncExecutor());
    }

    private LoginRepository getRepository() {
        final ObjectMapper objectMapper = applicationModule.getMapperModule().getObjectMapper();
        final FakeLoginRepository repository = new FakeLoginRepository(objectMapper);
        return new SimulateDelayLoginRepository(repository);
    }

    private LoginPresenter getPresenter(){
        return new SortPresenterImpl(view, applicationModule.getContext());
    }
}
