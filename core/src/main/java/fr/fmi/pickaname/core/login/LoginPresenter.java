package fr.fmi.pickaname.core.login;

import fr.fmi.pickaname.core.entities.User;

public interface LoginPresenter {
    void presentEmptyId();

    void presentEmptyPassword();

    void presentPendingRequest();

    void presentUnknownId();

    void presentInvalidPassword();

    void presentLoggedUser(User user);
}
