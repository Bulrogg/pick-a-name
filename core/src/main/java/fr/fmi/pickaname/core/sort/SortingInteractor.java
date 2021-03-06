package fr.fmi.pickaname.core.sort;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.exception.TechnicalException;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;

public class SortingInteractor {

    private final SortingPresenter presenter;

    private final GetFirstNamesRepository getFirstNamesRepository;

    private final ConfigurationRepository configurationRepository;

    private List<FirstName> firstNamesToPropose;

    public SortingInteractor(final SortingPresenter presenter,
                             final GetFirstNamesRepository getFirstNamesRepository,
                             final ConfigurationRepository configurationRepository) {
        this.presenter = presenter;
        this.getFirstNamesRepository = getFirstNamesRepository;
        this.configurationRepository = configurationRepository;
    }

    public void load() {
        presenter.presentLoading();
        try {
            final Settings settings = configurationRepository.getSettings();
            presenter.presentSortingScreen(settings.getLastName());
        } catch (TechnicalException e) {
            presenter.presentTechnicalError();
        }
        presentNextFirstNameToPropose();
    }

    public void accept(final String firstName) {
        try {
            configurationRepository.saveAccept(firstName);
        } catch (TechnicalException e) {
            presenter.presentAcceptSavingError();
        }
        presentNextFirstNameToPropose();
    }

    public void refuse(final String firstName) {
        try {
            configurationRepository.saveRefuse(firstName);
        } catch (TechnicalException e) {
            presenter.presentRefuseSavingError();
        }
        presentNextFirstNameToPropose();
    }

    void presentNextFirstNameToPropose() {
        try {
            final List<FirstName> firstNamesToPropose = getFirstNamesToPropose();
            if (firstNamesToPropose.isEmpty()) {
                presenter.presentNoMoreFirstName();
            } else {
                presenter.presentProposedFirstName(firstNamesToPropose.get(0));
                firstNamesToPropose.remove(0);
            }
        } catch (TechnicalException e) {
            presenter.presentTechnicalError();
        }
    }

    // TODO Code à améliorer
    List<FirstName> getFirstNamesToPropose() throws TechnicalException {
        if (firstNamesToPropose == null) {
            firstNamesToPropose = new ArrayList<>();
            final List<FirstName> allFirstName = getFirstNamesRepository.getFirstNames();

            final List<String> accepted = configurationRepository.getSorting().getAccepted();
            final List<String> rejected = configurationRepository.getSorting().getRejected();

            for (final FirstName firstName : allFirstName) {
                if (!accepted.contains(firstName.getFirstName()) && !rejected
                        .contains(firstName.getFirstName())) {
                    firstNamesToPropose.add(firstName);
                }
            }

            // TODO supprimer les mauvais types
            // TODO mélanger les prénoms
        }
        return firstNamesToPropose;
    }
}
