package fr.fmi.pickaname.repositories.firstname;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.exception.TechnicalException;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;
import fr.fmi.pickaname.model.JsonFirstName;

public class GetFirstNamesRepositoryImpl implements GetFirstNamesRepository {

    private final ObjectMapper mapper;

    private List<FirstName> firstNames;

    public GetFirstNamesRepositoryImpl(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<FirstName> getFirstNames() throws TechnicalException {
        if (firstNames == null) {
            firstNames = fetchFirstNames();
        }
        return firstNames;
    }

    private List<FirstName> fetchFirstNames() throws TechnicalException {
        try {
            final URL resource = getClass().getResource("/first-names.json");
            final ArrayList<FirstName> firstNames = new ArrayList<>();
            firstNames.addAll(Arrays.asList(mapper.readValue(resource, JsonFirstName[].class)));
            return firstNames;
        } catch (RuntimeException | IOException e) {
            throw new TechnicalException("Error during first-names.json fetching", e);
        }
    }

}
