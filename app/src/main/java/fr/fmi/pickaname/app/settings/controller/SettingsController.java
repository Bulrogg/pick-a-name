package fr.fmi.pickaname.app.settings.controller;

import fr.fmi.pickaname.app.settings.presentation.SettingsViewModel;

public interface SettingsController {

    void loadSettings();

    void saveSettings(SettingsViewModel viewModel);
}
