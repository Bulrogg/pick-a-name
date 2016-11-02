package fr.fmi.pickaname.repositories.firstname;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import fr.fmi.pickaname.MapperModule;
import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class GetFirstNamesRepositoryImplTest {

    private ObjectMapper mapper;
    private GetFirstNamesRepository repository;

    @Before
    public void setup() {
        mapper = new MapperModule().getObjectMapper();
        repository = new GetFirstNamesRepositoryImpl(mapper);
    }

    @Test
    public void getFirstNames_ShouldExtractAllTheFirstNames() throws Exception {
        // Given

        // When
        final List<FirstName> firstNames = repository.getFirstNames();

        // Then
        assertThat(firstNames).hasSize(1246);
    }

}