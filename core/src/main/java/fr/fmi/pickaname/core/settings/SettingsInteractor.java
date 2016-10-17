package fr.fmi.pickaname.core.settings;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.exception.TechnicalException;

public class SettingsInteractor {

    private final SettingsPresenter presenter;

    private final ConfigurationRepository configurationRepository;

    public SettingsInteractor(
            final SettingsPresenter presenter,
            final ConfigurationRepository configurationRepository
    ) {
        this.presenter = presenter;
        this.configurationRepository = configurationRepository;
    }

    public void loadSettings() {
        presenter.presentLoading();
        try {
            final Settings settings = configurationRepository.getSettings();
            presenter.presentSettings(settings);
        } catch (TechnicalException e) {
            presenter.presentLoadingFailure();
        }
    }

}
