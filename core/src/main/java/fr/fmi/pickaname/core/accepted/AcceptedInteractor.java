package fr.fmi.pickaname.core.accepted;

import java.util.List;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;

public class AcceptedInteractor {

    private final AcceptedPresenter presenter;

    private final ConfigurationRepository configurationRepository;

    public AcceptedInteractor(
            final AcceptedPresenter presenter,
            final ConfigurationRepository configurationRepository
    ) {
        this.presenter = presenter;
        this.configurationRepository = configurationRepository;
    }

    public void loadAcceptedFirstNames() {
        presenter.presentLoading();
        try {
            // TODO retourner directement toute la configuration
            final Settings settings = configurationRepository.getSettings();
            final Sorting sorting = configurationRepository.getSorting();
            final List<String> accepted = sorting.getAccepted();
            if (accepted.isEmpty()) {
                presenter.presentNoFirstNameAccepted();
            } else {
                presenter.presentFirstNames(accepted, settings.getLastName());
            }
        } catch (TechnicalException e) {
            presenter.presentLoadingFailure();
        }
    }

}
