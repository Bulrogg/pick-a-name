package fr.fmi.pickaname.core.sort;

import fr.fmi.pickaname.core.entities.FirstName;

public interface SortPresenter {

    void presentAFirstName(FirstName firstName);

    void presentLoadingFailure();

    void presentLoading();
}
