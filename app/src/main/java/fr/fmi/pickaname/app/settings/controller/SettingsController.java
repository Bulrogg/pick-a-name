package fr.fmi.pickaname.app.settings.controller;

public interface SettingsController {

    void loadSettings();

    void saveSettings(String lastName, String researchType);
}
