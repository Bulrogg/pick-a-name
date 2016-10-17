package fr.fmi.pickaname.app.settings.controller;

import fr.fmi.pickaname.core.settings.SettingsInteractor;

public class SettingsControllerImpl implements SettingsController {

    private final SettingsInteractor interactor;

    public SettingsControllerImpl(final SettingsInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void loadSettings() {
        interactor.loadSettings();
    }

}
