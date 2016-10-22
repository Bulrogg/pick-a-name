package fr.fmi.pickaname.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import fr.fmi.pickaname.MapperModule;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonSortingTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new MapperModule().getObjectMapper();
    }

    @Test
    public void deserialization() throws IOException {
        // Given
        final String json = "{ \"accepted\": [\"aA\", \"aB\"], \"rejected\": [\"rA\", \"rB\"] }";

        // When
        final JsonSorting obj = mapper.readValue(json, JsonSorting.class);

        // Then
        assertThat(obj.getAccepted()).containsExactly("aA", "aB");
        assertThat(obj.getRejected()).containsExactly("rA", "rB");
    }

}