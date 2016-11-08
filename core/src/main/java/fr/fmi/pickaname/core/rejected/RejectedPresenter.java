package fr.fmi.pickaname.core.rejected;

import java.util.List;

public interface RejectedPresenter {

    void presentLoading();

    void presentFirstNames(List<String> firstNames, String lastName);

    void presentNoFirstNameRejected();

    void presentLoadingFailure();

}
