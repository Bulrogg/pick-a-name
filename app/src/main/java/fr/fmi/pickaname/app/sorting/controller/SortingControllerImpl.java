package fr.fmi.pickaname.app.sorting.controller;

import fr.fmi.pickaname.core.sort.SortingInteractor;

public class SortingControllerImpl implements SortingController {

    private final SortingInteractor interactor;

    public SortingControllerImpl(final SortingInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void load() {
        interactor.load();
    }

    @Override
    public void accept(final String firstName) {
        interactor.accept(firstName);
    }

    @Override
    public void refuse(final String firstName) {
        interactor.refuse(firstName);
    }
}
