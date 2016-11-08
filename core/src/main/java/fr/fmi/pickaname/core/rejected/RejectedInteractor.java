package fr.fmi.pickaname.core.rejected;

import java.util.List;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;

public class RejectedInteractor {

    private final RejectedPresenter presenter;

    private final ConfigurationRepository configurationRepository;

    public RejectedInteractor(
            final RejectedPresenter presenter,
            final ConfigurationRepository configurationRepository
    ) {
        this.presenter = presenter;
        this.configurationRepository = configurationRepository;
    }

    public void loadRejectedFirstNames() {
        presenter.presentLoading();
        try {
            // TODO retourner directement toute la configuration
            final Settings settings = configurationRepository.getSettings();
            final Sorting sorting = configurationRepository.getSorting();
            final List<String> rejected = sorting.getRejected();
            if (rejected.isEmpty()) {
                presenter.presentNoFirstNameRejected();
            } else {
                presenter.presentFirstNames(rejected, settings.getLastName());
            }
        } catch (TechnicalException e) {
            presenter.presentLoadingFailure();
        }
    }

}
