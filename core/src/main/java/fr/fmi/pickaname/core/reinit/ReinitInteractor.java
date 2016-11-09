package fr.fmi.pickaname.core.reinit;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.exception.TechnicalException;

public class ReinitInteractor {

    private final ReinitPresenter presenter;
    private final ConfigurationRepository configurationRepository;

    public ReinitInteractor(
            final ReinitPresenter presenter,
            final ConfigurationRepository configurationRepository
    ) {
        this.presenter = presenter;
        this.configurationRepository = configurationRepository;
    }

    public void reinitializeConfiguration() {
        try {
            configurationRepository.reinitializeConfiguration();
            presenter.presentReinitializationSuccess();
        } catch (TechnicalException e) {
            presenter.presentReinitializationFailure();
        }
    }

}
