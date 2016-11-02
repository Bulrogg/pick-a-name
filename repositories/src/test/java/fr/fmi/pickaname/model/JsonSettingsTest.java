package fr.fmi.pickaname.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.core.entities.Settings;

import static fr.fmi.pickaname.core.entities.Settings.ResearchType.GIRL;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonSettingsTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new MapperModule().getObjectMapper();
    }

    @Test
    public void deserialization() throws Exception {
        // Given
        final String json = "{ \"lastName\": \"LAST NAME\", \"researchType\": \"GIRL\" }";

        // When
        final JsonSettings obj = mapper.readValue(json, JsonSettings.class);

        // Then
        assertThat(obj.getLastName()).isEqualTo("LAST NAME");
        assertThat(obj.getResearchType()).isEqualTo(Settings.ResearchType.GIRL);
    }

    @Test
    public void serialization() throws Exception {
        // Given
        final JsonSettings obj = JsonSettings.builder()
                                                  .setLastName("LAST NAME")
                                                  .setResearchType(GIRL)
                                                  .build();

        // When
        final String json = mapper.writeValueAsString(obj);

        // Then
        assertThat(json).isEqualTo("{\"lastName\":\"LAST NAME\",\"researchType\":\"GIRL\"}");
    }

}