package fr.fmi.pickaname.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.core.entities.Settings;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonSettingsTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new MapperModule().getObjectMapper();
    }

    @Test
    public void deserialization() throws IOException {
        // Given
        final String json = "{ \"last-name\": \"LAST NAME\", \"research-type\": \"GIRL\" }";

        // When
        final JsonSettings obj = mapper.readValue(json, JsonSettings.class);

        // Then
        assertThat(obj.getLastName()).isEqualTo("LAST NAME");
        assertThat(obj.getResearchType()).isEqualTo(Settings.ResearchType.GIRL);
    }

}