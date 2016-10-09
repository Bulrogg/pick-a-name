package fr.fmi.pickaname.repositories.login;

import org.junit.Before;
import org.junit.Test;

import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.core.entities.User;
import fr.fmi.pickaname.core.login.LoginRequest;

import static fr.fmi.pickaname.core.login.LoginRepository.InvalidPasswordException;
import static fr.fmi.pickaname.core.login.LoginRepository.UnknownUserException;
import static fr.fmi.pickaname.utils.AssertDate.assertSameHour;
import static org.assertj.core.api.Assertions.assertThat;

public class FakeSortRepositoryTest {
    private FakeLoginRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new FakeLoginRepository(new MapperModule().getObjectMapper());
    }

    @Test(expected = UnknownUserException.class)
    public void getUser_ShouldThrowUnknownUserException() throws InvalidPasswordException, UnknownUserException {
        final LoginRequest request = new LoginRequest("unknown", "pwd");
        repository.getUser(request);
    }

    @Test(expected = InvalidPasswordException.class)
    public void getUser_ShouldThrowInvalidPasswordException() throws InvalidPasswordException, UnknownUserException {
        final LoginRequest request = new LoginRequest("name", "invalid");
        repository.getUser(request);
    }

    @Test
    public void getUser() throws InvalidPasswordException, UnknownUserException {
        final LoginRequest request = new LoginRequest("name", "pwd");
        final User user = repository.getUser(request);
        assertThat(user.getFirstName()).isEqualTo("Louis");
        assertThat(user.getLastName()).isEqualTo("CK");
        assertSameHour(user.getLastLogin(), 2016, 8, 1, 12);
    }
}