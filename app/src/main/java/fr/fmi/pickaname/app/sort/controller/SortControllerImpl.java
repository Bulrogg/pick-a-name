package fr.fmi.pickaname.app.sort.controller;

import fr.fmi.pickaname.core.login.LoginInteractor;
import fr.fmi.pickaname.core.login.LoginRequest;

public class SortControllerImpl implements SortController {
    private final LoginInteractor interactor;

    public SortControllerImpl(LoginInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void executeRequest(String name, String password) {
        interactor.login(new LoginRequest(name, password));
    }
}
