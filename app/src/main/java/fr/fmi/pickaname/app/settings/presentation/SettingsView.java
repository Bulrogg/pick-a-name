package fr.fmi.pickaname.app.settings.presentation;

import android.support.annotation.StringRes;

public interface SettingsView {

    void displayViewModel(SettingsViewModel viewModel);

    void displayToast(@StringRes int fragment_settings_save_success);
}
