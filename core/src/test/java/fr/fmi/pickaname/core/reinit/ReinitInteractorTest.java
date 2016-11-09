package fr.fmi.pickaname.core.reinit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.exception.TechnicalException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class ReinitInteractorTest {

    @Mock ReinitPresenter presenter;
    @Mock ConfigurationRepository configurationRepository;

    @InjectMocks ReinitInteractor interactor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void reinitializeConfiguration_WhenRepositoryFailed_ShouldPresentAFail() throws Exception {
        // Given
        doThrow(TechnicalException.class).when(configurationRepository).reinitializeConfiguration();

        // When
        interactor.reinitializeConfiguration();

        // Then
        verify(presenter).presentReinitializationFailure();
    }

    @Test
    public void reinitializeConfiguration_WhenRepositorySucceed_ShouldPresentASuccess() throws Exception {
        // Given

        // When
        interactor.reinitializeConfiguration();

        // Then
        verify(presenter).presentReinitializationSuccess();
    }

}