package fr.fmi.pickaname.core.login;

import fr.fmi.pickaname.core.entities.User;

public interface LoginRepository {
    User getUser(LoginRequest request) throws UnknownUserException, InvalidPasswordException;

    class UnknownUserException extends Exception {

    }

    class InvalidPasswordException extends Exception {

    }
}
