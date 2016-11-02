package fr.fmi.pickaname.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import fr.fmi.pickaname.MapperModule;

import static fr.fmi.pickaname.core.entities.Settings.ResearchType.GIRL;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonConfigurationTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new MapperModule().getObjectMapper();
    }

    @Test
    public void deserialization() throws Exception {
        // Given
        final String json = "{ \"settings\": { \"lastName\": \"LAST NAME\", \"researchType\": \"GIRL\" }, \"sorting\": { \"accepted\": [\"aA\", \"aB\"], \"rejected\": [\"rA\", \"rB\"] } }";

        // When
        final JsonConfiguration obj = mapper.readValue(json, JsonConfiguration.class);

        // Then
        assertThat(obj.getSettings()).isNotNull();
        assertThat(obj.getSorting()).isNotNull();
    }

    @Test
    public void serialization() throws Exception {
        // Given
        final JsonConfiguration obj = JsonConfiguration
                .builder()
                .setJsonSettings(JsonSettings.builder()
                                             .setLastName("LAST NAME")
                                             .setResearchType(GIRL)
                                             .build())
                .setJsonSorting(JsonSorting.builder()
                                           .setAccepted(Arrays.asList("aA", "aB"))
                                           .setRejected(Arrays.asList("rA", "ar"))
                                           .build())
                .build();

        // When
        final String json = mapper.writeValueAsString(obj);

        // Then
        assertThat(json).isEqualTo("{\"settings\":{\"lastName\":\"LAST NAME\",\"researchType\":\"GIRL\"},\"sorting\":{\"accepted\":[\"aA\",\"aB\"],\"rejected\":[\"rA\",\"ar\"]}}");
    }

}