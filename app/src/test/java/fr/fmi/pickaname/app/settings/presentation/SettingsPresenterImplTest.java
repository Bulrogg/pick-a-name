package fr.fmi.pickaname.app.settings.presentation;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.core.entities.Settings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class SettingsPresenterImplTest {

    @Mock SettingsView view;
    @Mock Context context;
    @Mock private Settings settings;
    @InjectMocks SettingsPresenterImpl presenter;

    @Captor ArgumentCaptor<SettingsViewModel> viewModelCaptor;
    @Captor ArgumentCaptor<Integer> resIdCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void presentLoading_ShouldCorrectlySetupTheView() throws Exception {
        // Given

        // When
        presenter.presentLoading();

        // Then
        verify(view, only()).displayViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(SettingsViewModel.VF_LOADING);
    }

    @Test
    public void presentSettings_ShouldCorrectlySetupTheView() throws Exception {
        // Given
        given(settings.getLastName()).willReturn("LAST NAME");
        given(settings.getResearchType()).willReturn(Settings.ResearchType.GIRL);

        // When
        presenter.presentSettings(settings);

        // Then
        verify(view, only()).displayViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(SettingsViewModel.VF_SUCCESS);
        assertThat(viewModelCaptor.getValue().lastName).isEqualTo("LAST NAME");
        assertThat(viewModelCaptor.getValue().researchType).isEqualTo("GIRL");
    }

    @Test
    public void presentLoadingFailure_ShouldCorrectlySetupTheView() throws Exception {
        // Given

        // When
        presenter.presentLoadingFailure();

        // Then
        verify(view, only()).displayViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(SettingsViewModel.VF_ERROR);
    }

    @Test
    public void presentSaveSettingsSuccess_ShouldCorrectlySetupTheView() throws Exception {
        // Given

        // When
        presenter.presentSaveSettingsSuccess();

        // Then
        verify(view, only()).displayToast(resIdCaptor.capture());
        assertThat(resIdCaptor.getValue()).isEqualTo(R.string.fragment_settings_save_success);
    }

    @Test
    public void presentSaveSettingsFailure_ShouldCorrectlySetupTheView() throws Exception {
        // Given

        // When
        presenter.presentSaveSettingsFailure();

        // Then
        verify(view, only()).displayToast(resIdCaptor.capture());
        assertThat(resIdCaptor.getValue()).isEqualTo(R.string.fragment_settings_save_failure);
    }

}