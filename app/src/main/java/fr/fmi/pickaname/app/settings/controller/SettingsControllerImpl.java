package fr.fmi.pickaname.app.settings.controller;

import fr.fmi.pickaname.core.entities.Settings;
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

    @Override
    public void saveSettings(final String lastName, final String researchType) {
        interactor.saveSettings(Settings.ResearchType.valueOf(researchType), lastName);
    }

}
