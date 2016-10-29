package fr.fmi.pickaname.repositories.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Settings.ResearchType;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;
import fr.fmi.pickaname.model.JsonConfiguration;
import fr.fmi.pickaname.model.JsonSettings;
import fr.fmi.pickaname.model.JsonSorting;

import static fr.fmi.pickaname.core.entities.Settings.ResearchType.BOTH;

// TODO tester
public class ConfigurationRepositoryImpl implements ConfigurationRepository {

    // TODO put in SharedPreference
    private static JsonConfiguration configuration;

    private final ObjectMapper mapper;

    public ConfigurationRepositoryImpl(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Settings getSettings() throws TechnicalException {
        return getConfiguration().getSettings();
    }

    @Override
    public Sorting getSorting() throws TechnicalException {
        return getConfiguration().getSorting();
    }

    @Override
    public Settings saveSettings(
            final ResearchType researchType,
            final String lastName
    ) throws TechnicalException {
        configuration = JsonConfiguration
                .copy(getConfiguration())
                .setJsonSettings(JsonSettings.builder()
                                         .setLastName(lastName)
                                         .setResearchType(researchType)
                                         .build())
                .build();
        return configuration.getSettings();
    }

    @Override
    public void saveAccept(final String firstName) throws TechnicalException {
        final JsonConfiguration oldConfiguration = getConfiguration();
        final Sorting sorting = oldConfiguration.getSorting();
        final List<String> accepted = sorting.getAccepted();
        accepted.add(firstName);
        configuration = JsonConfiguration
                .copy(oldConfiguration)
                .setJsonSorting(JsonSorting.copy(sorting).setAccepted(accepted).build())
                .build();
    }

    @Override
    public void saveRefuse(final String firstName) throws TechnicalException {
        final JsonConfiguration oldConfiguration = getConfiguration();
        final Sorting sorting = oldConfiguration.getSorting();
        final List<String> rejected = sorting.getRejected();
        rejected.add(firstName);
        configuration = JsonConfiguration
                .copy(oldConfiguration)
                .setJsonSorting(JsonSorting.copy(sorting).setRejected(rejected).build())
                .build();
    }

    JsonConfiguration getConfiguration() {
        if (configuration == null) {
            configuration = JsonConfiguration
                    .builder()
                    .setJsonSettings(JsonSettings.builder()
                                             .setLastName("Default-lastname")
                                             .setResearchType(BOTH)
                                             .build())
                    .setJsonSorting(JsonSorting.builder()
                                           .setAccepted(new ArrayList<String>())
                                           .setRejected(new ArrayList<String>())
                                           .build())
                    .build();
        }
        return configuration;
    }
}
