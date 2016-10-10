package fr.fmi.pickaname.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.core.entities.FirstName;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonFirstNameTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new MapperModule().getObjectMapper();
    }

    @Test
    public void deserialization() throws IOException {
        // Given
        final String json = "{ \"first-name\" : \"Frédéric\", \"gender\" : \"MALE\" }";

        // When
        final JsonFirstName firstName = mapper.readValue(json, JsonFirstName.class);

        // Then
        assertThat(firstName.getFirstName()).isEqualTo("Frédéric");
        assertThat(firstName.getGender()).isEqualTo(FirstName.Gender.MALE);
    }

}