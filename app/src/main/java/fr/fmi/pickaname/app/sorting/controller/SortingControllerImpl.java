package fr.fmi.pickaname.app.sorting.controller;

import fr.fmi.pickaname.core.sort.SortInteractor;

public class SortingControllerImpl implements SortingController {

    private final SortInteractor interactor;

    public SortingControllerImpl(final SortInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void loadFirstNames() {
        interactor.loadFirstNames();
    }

}
