package fr.fmi.pickaname.repositories.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;
import fr.fmi.pickaname.core.storage.DeviceStorage;
import fr.fmi.pickaname.model.JsonConfiguration;
import fr.fmi.pickaname.model.JsonSettings;
import fr.fmi.pickaname.model.JsonSorting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ConfigurationRepositoryImplTest {

    @Mock JsonSorting sorting;
    @Mock JsonSettings settings;
    @Mock JsonConfiguration configuration;
    @Mock ObjectMapper mapper;
    @Mock DeviceStorage deviceStorage;
    @InjectMocks ConfigurationRepositoryImpl repository;

    @Captor ArgumentCaptor<JsonConfiguration> configurationCaptor;
    @Captor ArgumentCaptor<String> jsonCaptor;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = spy(repository);
        given(configuration.getJsonSettings()).willReturn(settings);
        given(configuration.getSettings()).willReturn(settings);
        given(configuration.getJsonSorting()).willReturn(sorting);
        given(configuration.getSorting()).willReturn(sorting);
    }

    @Test
    public void getSettings_ShouldReturnTheSaveSettings() throws Exception {
        // Given
        doReturn(configuration).when(repository).getConfiguration();

        // When
        final Settings actualSettings = repository.getSettings();

        // Then
        verify(repository).getConfiguration();
        assertThat(actualSettings).isEqualTo(settings);
    }

    @Test
    public void getSorting_ShouldReturnTheSaveSorting() throws Exception {
        // Given

        // When
        final Sorting actualSorting = repository.getSorting();

        // Then
        verify(repository).getConfiguration();
        assertThat(actualSorting).isEqualTo(sorting);
    }

    @Test
    public void saveSettings_WhenSaveConfigurationSuccess_ShouldReturnNewSettings() throws Exception {
        // Given
        doNothing().when(repository).saveConfiguration(any(JsonConfiguration.class));
        doReturn(configuration).when(repository).getConfiguration();

        // When
        final Settings newSettings = repository.saveSettings(Settings.ResearchType.GIRL,
                                                             "NEW LAST NAME");

        // Then
        verify(repository).saveConfiguration(configurationCaptor.capture());
        assertThat(configurationCaptor.getValue().getSettings().getLastName()).isEqualTo(
                "NEW LAST NAME");
        assertThat(configurationCaptor.getValue().getSettings().getResearchType()).isEqualTo(
                Settings.ResearchType.GIRL);
        assertThat(newSettings.getLastName()).isEqualTo("NEW LAST NAME");
        assertThat(newSettings.getResearchType()).isEqualTo(Settings.ResearchType.GIRL);
    }

    @Test(expected = TechnicalException.class)
    public void saveSettings_WhenSaveConfigurationFailed_ShouldThrowATechnicalException() throws Exception {
        // Given
        doThrow(TechnicalException.class).when(repository)
                                         .saveConfiguration(any(JsonConfiguration.class));

        // When
        repository.saveSettings(Settings.ResearchType.GIRL, "NEW LAST NAME");
    }

    @Test
    public void saveAccept_WhenSaveConfigurationSuccess_ShouldReturnVoid() throws Exception {
        // Given
        final String firstName = "FIRST NAME";
        doNothing().when(repository).saveConfiguration(any(JsonConfiguration.class));
        doReturn(configuration).when(repository).getConfiguration();

        // When
        repository.saveAccept(firstName);

        // Then
        verify(repository).saveConfiguration(configurationCaptor.capture());
        assertThat(configurationCaptor.getValue()
                                      .getSorting()
                                      .getAccepted()).contains("FIRST NAME");
    }

    @Test(expected = TechnicalException.class)
    public void saveAccept_WhenSaveConfigurationFailed_ShouldThrowATechnicalException() throws Exception {
        // Given
        final String firstName = "FIRST NAME";
        doThrow(TechnicalException.class).when(repository)
                                         .saveConfiguration(any(JsonConfiguration.class));

        // When
        repository.saveAccept(firstName);
    }

    @Test
    public void saveRefuse_WhenSaveConfigurationSuccess_ShouldReturnVoid() throws Exception {
        // Given
        final String firstName = "FIRST NAME";
        doNothing().when(repository).saveConfiguration(any(JsonConfiguration.class));
        doReturn(configuration).when(repository).getConfiguration();

        // When
        repository.saveRefuse(firstName);

        // Then
        verify(repository).saveConfiguration(configurationCaptor.capture());
        assertThat(configurationCaptor.getValue()
                                      .getSorting()
                                      .getRejected()).contains("FIRST NAME");
    }

    @Test(expected = TechnicalException.class)
    public void saveRefuse_WhenSaveConfigurationFailed_ShouldThrowATechnicalException() throws Exception {
        // Given
        final String firstName = "FIRST NAME";
        doThrow(TechnicalException.class).when(repository)
                                         .saveConfiguration(any(JsonConfiguration.class));

        // When
        repository.saveRefuse(firstName);
    }

    @Test(expected = TechnicalException.class)
    public void saveConfiguration_WhenErrorDuringJsonProcessing_ShouldThrowATechnicalException() throws Exception {
        // Given
        given(mapper.writeValueAsString(configuration)).willThrow(JsonProcessingException.class);

        // When
        repository.saveConfiguration(configuration);
    }

    @Test
    public void saveConfiguration_WhenSerialisationSucceed_ShouldSaveTheConfiguration() throws Exception {
        // Given
        given(mapper.writeValueAsString(configuration)).willReturn("JSON");

        // When
        repository.saveConfiguration(configuration);

        // Then
        final InOrder inOrder = inOrder(mapper, deviceStorage);
        inOrder.verify(mapper).writeValueAsString(configuration);
        inOrder.verify(deviceStorage).put("USER_CONFIGURATION_KEY", "JSON");
    }

    @Test
    public void getConfiguration_WhenNoStoredConf_ShouldInitAndReturnTheConf() throws Exception {
        // Given
        given(deviceStorage.get("USER_CONFIGURATION_KEY", null)).willReturn(null);
        doReturn(configuration).when(repository).initConfiguration();

        // When
        final JsonConfiguration returnConfiguration = repository.getConfiguration();

        // Then
        final InOrder inOrder = inOrder(deviceStorage, repository);
        inOrder.verify(deviceStorage).get("USER_CONFIGURATION_KEY", null);
        inOrder.verify(repository).initConfiguration();
        inOrder.verify(repository).saveConfiguration(configuration);
        assertThat(returnConfiguration).isEqualTo(configuration);
    }

    @Test
    public void getConfiguration_WhenStoredConf_ShouldReturnTheConf() throws Exception {
        // Given
        given(deviceStorage.get("USER_CONFIGURATION_KEY", null)).willReturn("JSON");
        given(mapper.readValue("JSON", JsonConfiguration.class)).willReturn(configuration);

        // When
        final JsonConfiguration returnConfiguration = repository.getConfiguration();

        // Then
        final InOrder inOrder = inOrder(deviceStorage, mapper);
        inOrder.verify(deviceStorage).get("USER_CONFIGURATION_KEY", null);
        inOrder.verify(mapper).readValue("JSON", JsonConfiguration.class);
        assertThat(returnConfiguration).isEqualTo(configuration);
    }

    @Test(expected = TechnicalException.class)
    public void getConfiguration_WhenDeserializationFailed_ShouldThrowAnError() throws Exception {
        // Given
        given(deviceStorage.get("USER_CONFIGURATION_KEY", null)).willReturn("JSON");
        given(mapper.readValue("JSON", JsonConfiguration.class)).willThrow(TechnicalException.class);

        // When
        repository.getConfiguration();
    }
}