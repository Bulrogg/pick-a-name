package fr.fmi.pickaname.app.settings.controller;

import fr.fmi.pickaname.app.settings.presentation.SettingsScreenViewModel;

public interface SettingsController {

    void loadSettings();

    void saveSettings(SettingsScreenViewModel viewModel);
}
