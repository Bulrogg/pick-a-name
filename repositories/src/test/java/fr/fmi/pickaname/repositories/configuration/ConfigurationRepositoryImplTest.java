package fr.fmi.pickaname.repositories.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.core.entities.Configuration;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Sorting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

public class ConfigurationRepositoryImplTest {

    @Mock Sorting sorting;
    @Mock Settings settings;
    @Mock Configuration configuration;
    @Mock ObjectMapper mapper;
    @InjectMocks ConfigurationRepositoryImpl repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repository = spy(repository);
        given(configuration.getSettings()).willReturn(settings);
        given(configuration.getSorting()).willReturn(sorting);
        given(repository.getConfiguration()).willReturn(configuration);
    }


    @Test
    public void getSettings_ShouldReturnTheSaveSettings() throws Exception {
        // Given

        // When
        final Settings actualSettings = repository.getSettings();

        // Then
        assertThat(actualSettings).isEqualTo(settings);
    }

    @Test
    public void getSorting_ShouldReturnTheSaveSorting() throws Exception {
        // Given

        // When
        final Sorting actualSorting = repository.getSorting();

        // Then
        assertThat(actualSorting).isEqualTo(sorting);
    }

    // TODO ooo test saveSettings

}