package fr.fmi.pickaname.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import fr.fmi.pickaname.MapperModule;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonConfigurationTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new MapperModule().getObjectMapper();
    }

    @Test
    public void deserialization() throws IOException {
        // Given
        final String json = "{ \"settings\": { \"last-name\": \"LAST NAME\", \"research-type\": \"GIRL\" }, \"sorting\": { \"accepted\": [\"aA\", \"aB\"], \"rejected\": [\"rA\", \"rB\"] } }";

        // When
        final JsonConfiguration obj = mapper.readValue(json, JsonConfiguration.class);

        // Then
        assertThat(obj.getSettings()).isNotNull();
        assertThat(obj.getSorting()).isNotNull();
    }

}