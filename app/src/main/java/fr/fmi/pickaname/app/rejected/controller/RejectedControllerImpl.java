package fr.fmi.pickaname.app.rejected.controller;

import fr.fmi.pickaname.core.rejected.RejectedInteractor;

public class RejectedControllerImpl implements RejectedController {

    private final RejectedInteractor interactor;

    public RejectedControllerImpl(final RejectedInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void load() {
        interactor.loadRejectedFirstNames();
    }

}
