package fr.fmi.pickaname.repositories.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Configuration;
import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.exception.TechnicalException;

// TODO ooo gérer un cache
public class ConfigurationRepositoryImpl implements ConfigurationRepository {

    private final ObjectMapper mapper;

    public ConfigurationRepositoryImpl(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override public Configuration getUserConfiguration() throws TechnicalException {
        // TODO rechercher depuis les preferences storages

        return new Configuration() {
            @Override public String getLastName() {
                return "Last name";
            }

            @Override public ResearchType getResearchType() {
                return ResearchType.BOY;
            }

            @Override public List<FirstName> getFirstNameAccepted() {
                return new ArrayList<>();
            }

            @Override public List<FirstName> getFirstNameRejected() {
                return new ArrayList<>();
            }
        };
    }
}
