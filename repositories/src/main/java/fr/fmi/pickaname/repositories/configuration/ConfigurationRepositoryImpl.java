package fr.fmi.pickaname.repositories.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Configuration;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Settings.ResearchType;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;
import fr.fmi.pickaname.model.JsonConfiguration;
import fr.fmi.pickaname.model.JsonSettings;
import fr.fmi.pickaname.model.JsonSorting;

import static fr.fmi.pickaname.core.entities.Settings.ResearchType.BOTH;

public class ConfigurationRepositoryImpl implements ConfigurationRepository {

    // TODO ooo put in SharedPreference
    private static Configuration configuration;

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
    public Configuration saveSettings(
            final ResearchType researchType,
            final String lastName
    ) throws TechnicalException {
        configuration = JsonConfiguration.copy(getConfiguration())
                                         .setSettings(JsonSettings.builder()
                                                                  .setLastName(lastName)
                                                                  .setResearchType(researchType)
                                                                  .build())
                                         .build();
        return configuration;
    }

    private Configuration getConfiguration() {
        if (configuration == null) {
            configuration = JsonConfiguration.builder()
                                             .setSettings(JsonSettings.builder()
                                                                      .setLastName(
                                                                              "Default-lastname")
                                                                      .setResearchType(BOTH)
                                                                      .build()
                                             )
                                             .setSorting(JsonSorting.builder()
                                                                    .setAccepted(new ArrayList<String>())
                                                                    .setRejected(new ArrayList<String>())
                                                                    .build()
                                             ).build();
        }
        return configuration;
    }
}
