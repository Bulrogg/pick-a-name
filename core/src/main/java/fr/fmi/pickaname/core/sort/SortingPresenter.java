package fr.fmi.pickaname.core.sort;

import fr.fmi.pickaname.core.entities.FirstName;

public interface SortingPresenter {

    void presentSortingScreen(String lastName);

    void presentProposedFirstName(FirstName firstName);

    void presentTechnicalError();

    void presentLoading();

    void presentNoMoreFirstName();

    void presentAcceptSavingError();

    void presentRefuseSavingError();
}
