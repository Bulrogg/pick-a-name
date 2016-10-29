package fr.fmi.pickaname.app.settings.presentation;

public interface SettingsView {

    void displayViewModel(SettingsScreenViewModel viewModel);

    void displayToast(String message);
}
