package fr.fmi.pickaname.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import fr.fmi.pickaname.MapperModule;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonSortingTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new MapperModule().getObjectMapper();
    }

    @Test
    public void deserialization() throws Exception {
        // Given
        final String json = "{ \"accepted\": [\"aA\", \"aB\"], \"rejected\": [\"rA\", \"rB\"] }";

        // When
        final JsonSorting obj = mapper.readValue(json, JsonSorting.class);

        // Then
        assertThat(obj.getAccepted()).containsExactly("aA", "aB");
        assertThat(obj.getRejected()).containsExactly("rA", "rB");
    }

    @Test
    public void serialization() throws Exception {
        // Given
        final JsonSorting obj = JsonSorting.builder()
                                           .setAccepted(Arrays.asList("aA", "aB"))
                                           .setRejected(Arrays.asList("rA", "ar"))
                                           .build();

        // When
        final String json = mapper.writeValueAsString(obj);

        // Then
        assertThat(json).isEqualTo("{\"accepted\":[\"aA\",\"aB\"],\"rejected\":[\"rA\",\"ar\"]}");
    }

}