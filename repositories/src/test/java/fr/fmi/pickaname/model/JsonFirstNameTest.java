package fr.fmi.pickaname.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

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
    public void deserialization() throws Exception {
        // Given
        final String json = "{ \"first-name\" : \"Frédéric\", \"gender\" : \"MALE\" }";

        // When
        final JsonFirstName obj = mapper.readValue(json, JsonFirstName.class);

        // Then
        assertThat(obj.getFirstName()).isEqualTo("Frédéric");
        assertThat(obj.getGender()).isEqualTo(FirstName.Gender.MALE);
    }

    @Test
    public void serialization() throws Exception {
        // Given
        final JsonFirstName obj = JsonFirstName.builder()
                                               .setFirstName("Frédéric")
                                               .setGender(FirstName.Gender.MALE)
                                               .build();

        // When
        final String json = mapper.writeValueAsString(obj);

        // Then
        assertThat(json).isEqualTo("{\"firstName\":\"Frédéric\",\"gender\":\"MALE\"}");
    }

}