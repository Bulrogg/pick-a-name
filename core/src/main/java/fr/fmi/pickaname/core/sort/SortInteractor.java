package fr.fmi.pickaname.core.sort;

import java.util.List;

import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.exception.TechnicalException;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;

public class SortInteractor {

    private final SortPresenter presenter;

    private final GetFirstNamesRepository getFirstNamesRepository;

    public SortInteractor(
            final SortPresenter presenter,
            final GetFirstNamesRepository getFirstNamesRepository
    ) {
        this.presenter = presenter;
        this.getFirstNamesRepository = getFirstNamesRepository;
    }

    public void loadFirstNames() {
        // TODO ooo avoir de l'aléatoire dans la présentation des prénoms
        presenter.presentLoading();
        try {
            final List<FirstName> firstNames = getFirstNamesRepository.getFirstNames();
            // TODO ooo attention au tableau vide
            presenter.presentAFirstName(firstNames.get(0));
        } catch (TechnicalException e) {
            presenter.presentLoadingFailure();
        }
    }

}
