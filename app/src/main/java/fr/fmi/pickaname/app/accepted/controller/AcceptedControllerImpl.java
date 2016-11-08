package fr.fmi.pickaname.app.accepted.controller;

import fr.fmi.pickaname.core.accepted.AcceptedInteractor;

public class AcceptedControllerImpl implements AcceptedController {

    private final AcceptedInteractor interactor;

    public AcceptedControllerImpl(final AcceptedInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void load() {
        interactor.loadAcceptedFirstNames();
    }

}
