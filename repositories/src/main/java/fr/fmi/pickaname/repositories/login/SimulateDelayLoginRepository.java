package fr.fmi.pickaname.repositories.login;

import fr.fmi.pickaname.core.entities.User;
import fr.fmi.pickaname.core.login.LoginRepository;
import fr.fmi.pickaname.core.login.LoginRequest;

public class SimulateDelayLoginRepository implements LoginRepository {
    private static final int DELAY = 4000;
    private final LoginRepository repository;

    public SimulateDelayLoginRepository(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUser(LoginRequest request) throws UnknownUserException, InvalidPasswordException {
        simulateDelay();
        return repository.getUser(request);
    }

    protected void simulateDelay() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
