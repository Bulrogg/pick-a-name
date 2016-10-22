package fr.fmi.pickaname.core.settings;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.exception.TechnicalException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class SettingsInteractorTest {

    @Mock Settings settings;
    @Mock SettingsPresenter presenter;
    @Mock ConfigurationRepository repository;

    @InjectMocks SettingsInteractor interactor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadSettings_WhenRepositorySucceed_ShouldLoadThenPresentSettings() throws Exception {
        // Given
        given(repository.getSettings()).willReturn(settings);

        // When
        interactor.loadSettings();

        // Then
        final InOrder inOrder = inOrder(presenter);
        inOrder.verify(presenter).presentLoading();
        inOrder.verify(presenter).presentSettings(settings);
        verify(presenter, never()).presentLoadingFailure();
    }

    @Test
    public void loadSettings_WhenRepositoryFailed_ShouldLoadThenPresentLoadingFailure() throws Exception {
        // Given
        given(repository.getSettings()).willThrow(TechnicalException.class);

        // When
        interactor.loadSettings();

        // Then
        final InOrder inOrder = inOrder(presenter);
        inOrder.verify(presenter).presentLoading();
        inOrder.verify(presenter).presentLoadingFailure();
        verify(presenter, never()).presentSettings(settings);
    }

    @Test
    public void saveSettings_WhenRepositorySucceed_ShouldPresentSaveSuccess() throws Exception {
        // Given
        final String lastName = "LAST NAME";
        final Settings.ResearchType researchType = Settings.ResearchType.GIRL;
        given(repository.saveSettings(researchType, lastName)).willReturn(settings);

        // When
        interactor.saveSettings(researchType, lastName);

        // Then
        verify(presenter).presentSaveSettingsSuccess();
        verify(presenter, never()).presentSaveSettingsFailure();
    }

    @Test
    public void saveSettings_WhenRepositoryFailed_ShouldPresentSaveFailure() throws Exception {
        // Given
        final String lastName = "LAST NAME";
        final Settings.ResearchType researchType = Settings.ResearchType.GIRL;
        given(repository.saveSettings(researchType, lastName)).willThrow(TechnicalException.class);

        // When
        interactor.saveSettings(researchType, lastName);

        // Then
        verify(presenter).presentSaveSettingsFailure();
        verify(presenter, never()).presentSaveSettingsSuccess();
    }

}