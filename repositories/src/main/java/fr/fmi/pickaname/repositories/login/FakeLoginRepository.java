package fr.fmi.pickaname.repositories.login;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import fr.fmi.pickaname.core.entities.User;
import fr.fmi.pickaname.core.login.LoginRepository;
import fr.fmi.pickaname.core.login.LoginRequest;
import fr.fmi.pickaname.model.JsonUser;

public class FakeLoginRepository implements LoginRepository {
    private static final String UNKNOWN = "unknown";
    private static final String INVALID = "invalid";

    private final ObjectMapper mapper;

    public FakeLoginRepository(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User getUser(final LoginRequest request) throws UnknownUserException, InvalidPasswordException {
        assertKnownUser(request);
        assertValidPassword(request);
        return loadUserFromResource();
    }

    private User loadUserFromResource() {
        User user = null;
        try {
            user = mapper.readValue(getClass().getResource("/user.json"), JsonUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    private void assertValidPassword(LoginRequest request) throws InvalidPasswordException {
        if (INVALID.equalsIgnoreCase(request.getPassword())) {
            throw new InvalidPasswordException();
        }
    }

    private void assertKnownUser(LoginRequest request) throws UnknownUserException {
        if (UNKNOWN.equalsIgnoreCase(request.getId())) {
            throw new UnknownUserException();
        }
    }
}
