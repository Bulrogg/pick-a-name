package fr.fmi.pickaname.app.settings.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.app.settings.presentation.SettingsViewModel;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.settings.SettingsInteractor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class SettingsControllerImplTest {

    @Mock SettingsInteractor interactor;
    @Mock SettingsViewModel viewModel;
    @InjectMocks SettingsControllerImpl controller;

    @Captor ArgumentCaptor<Settings.ResearchType> researchTypeCaptor;
    @Captor ArgumentCaptor<String> lastNameCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadSettings_ShouldCallLoadOnTheInteractor() throws Exception {
        // Given

        // When
        controller.loadSettings();

        // Then
        verify(interactor, only()).loadSettings();
    }

    @Test
    public void saveSettings_WhenGIRL_ShouldTellTheInteractorToSaveSettings() throws Exception {
        // Given
        viewModel.lastName = "LAST NAME";
        viewModel.researchType = "GIRL";

        // When
        controller.saveSettings(viewModel);

        // Then
        verify(interactor).saveSettings(researchTypeCaptor.capture(), lastNameCaptor.capture());
        assertThat(researchTypeCaptor.getValue()).isEqualTo(Settings.ResearchType.GIRL);
        assertThat(lastNameCaptor.getValue()).isEqualTo("LAST NAME");
    }

    @Test
    public void saveSettings_WhenBOY_ShouldTellTheInteractorToSaveSettings() throws Exception {
        // Given
        viewModel.lastName = "LAST NAME";
        viewModel.researchType = "BOY";

        // When
        controller.saveSettings(viewModel);

        // Then
        verify(interactor).saveSettings(researchTypeCaptor.capture(), lastNameCaptor.capture());
        assertThat(researchTypeCaptor.getValue()).isEqualTo(Settings.ResearchType.BOY);
        assertThat(lastNameCaptor.getValue()).isEqualTo("LAST NAME");
    }

    @Test
    public void saveSettings_WhenBOTH_ShouldTellTheInteractorToSaveSettings() throws Exception {
        // Given
        viewModel.lastName = "LAST NAME";
        viewModel.researchType = "BOTH";

        // When
        controller.saveSettings(viewModel);

        // Then
        verify(interactor).saveSettings(researchTypeCaptor.capture(), lastNameCaptor.capture());
        assertThat(researchTypeCaptor.getValue()).isEqualTo(Settings.ResearchType.BOTH);
        assertThat(lastNameCaptor.getValue()).isEqualTo("LAST NAME");
    }
}