package fr.fmi.pickaname.app.reinit.controller;

import fr.fmi.pickaname.core.reinit.ReinitInteractor;

public class ReinitControllerImpl implements ReinitController {

    private final ReinitInteractor interactor;

    public ReinitControllerImpl(final ReinitInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void reinitialize() {
        interactor.reinitializeConfiguration();
    }
}
