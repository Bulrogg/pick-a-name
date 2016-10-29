package fr.fmi.pickaname.app.settings.controller;

import fr.fmi.pickaname.app.settings.presentation.SettingsScreenViewModel;
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
    public void saveSettings(final SettingsScreenViewModel viewModel) {
        interactor.saveSettings(Settings.ResearchType.valueOf(viewModel.researchType),
                                viewModel.lastName);
    }

}
