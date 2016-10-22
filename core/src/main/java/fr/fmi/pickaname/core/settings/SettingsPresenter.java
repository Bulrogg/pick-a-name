package fr.fmi.pickaname.core.settings;

import fr.fmi.pickaname.core.entities.Settings;

public interface SettingsPresenter {

    void presentLoading();

    void presentSettings(Settings settings);

    void presentLoadingFailure();

    void presentSaveSettingsSuccess();

    void presentSaveSettingsFailure();

}
