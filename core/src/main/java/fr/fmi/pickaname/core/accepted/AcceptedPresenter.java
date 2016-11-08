package fr.fmi.pickaname.core.accepted;

import java.util.List;

public interface AcceptedPresenter {

    void presentLoading();

    void presentFirstNames(List<String> firstNames, String lastName);

    void presentNoFirstNameAccepted();

    void presentLoadingFailure();

}
