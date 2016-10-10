package fr.fmi.pickaname.app.sort.controller;

import fr.fmi.pickaname.core.sort.SortInteractor;

public class SortControllerImpl implements SortController {

    private final SortInteractor interactor;

    public SortControllerImpl(final SortInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void loadFirstNames() {
        interactor.loadFirstNames();
    }

}
