package fr.fmi.pickaname.repositories.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Settings.ResearchType;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;
import fr.fmi.pickaname.core.storage.DeviceStorage;
import fr.fmi.pickaname.model.JsonConfiguration;
import fr.fmi.pickaname.model.JsonSettings;
import fr.fmi.pickaname.model.JsonSorting;

import static fr.fmi.pickaname.core.entities.Settings.ResearchType.BOTH;

public class ConfigurationRepositoryImpl implements ConfigurationRepository {

    private static final String USER_CONFIGURATION_KEY = "USER_CONFIGURATION_KEY";

    private final DeviceStorage deviceStorage;
    private final ObjectMapper mapper;

    public ConfigurationRepositoryImpl(
            final DeviceStorage deviceStorage,
            final ObjectMapper mapper
    ) {
        this.deviceStorage = deviceStorage;
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
        final JsonConfiguration oldConfiguration = getConfiguration();
        final JsonSettings newSettings = JsonSettings.builder()
                                                     .setLastName(lastName)
                                                     .setResearchType(researchType)
                                                     .build();
        final JsonConfiguration newConfiguration = JsonConfiguration.copy(oldConfiguration)
                                                                    .setJsonSettings(newSettings)
                                                                    .build();
        saveConfiguration(newConfiguration);
        return newSettings;
    }

    @Override
    public void saveAccept(final String firstName) throws TechnicalException {
        final JsonConfiguration oldConfiguration = getConfiguration();
        final JsonSorting sorting = oldConfiguration.getJsonSorting();
        final List<String> accepted = sorting.getAccepted();
        accepted.add(firstName);
        final JsonSorting newSorting = JsonSorting.copy(sorting).setAccepted(accepted).build();
        final JsonConfiguration newConfiguration = JsonConfiguration.copy(oldConfiguration)
                                                                    .setJsonSorting(newSorting)
                                                                    .build();
        saveConfiguration(newConfiguration);
    }

    @Override
    public void saveRefuse(final String firstName) throws TechnicalException {
        final JsonConfiguration oldConfiguration = getConfiguration();
        final JsonSorting sorting = oldConfiguration.getJsonSorting();
        final List<String> rejected = sorting.getRejected();
        rejected.add(firstName);
        final JsonSorting newSorting = JsonSorting.copy(sorting).setRejected(rejected).build();
        final JsonConfiguration newConfiguration = JsonConfiguration.copy(oldConfiguration)
                                                                    .setJsonSorting(newSorting)
                                                                    .build();
        saveConfiguration(newConfiguration);
    }

    void saveConfiguration(final JsonConfiguration configuration) throws TechnicalException {
        try {
            final String confStr = mapper.writeValueAsString(configuration);
            deviceStorage.put(USER_CONFIGURATION_KEY, confStr);
        } catch (JsonProcessingException e) {
            throw new TechnicalException("Error during configuration serialization", e);
        }
    }

    JsonConfiguration getConfiguration() throws TechnicalException {
        final String confStr = deviceStorage.get(USER_CONFIGURATION_KEY, null);
        JsonConfiguration configuration;
        if (confStr == null) {
            configuration = initConfiguration();
            saveConfiguration(configuration);
        } else {
            try {
                configuration = mapper.readValue(confStr, JsonConfiguration.class);
            } catch (IOException e) {
                throw new TechnicalException("Error during configuration deserialization", e);
            }
        }
        return configuration;
    }

    JsonConfiguration initConfiguration() {
        return JsonConfiguration
                .builder()
                .setJsonSettings(JsonSettings.builder()
                                             .setLastName("")
                                             .setResearchType(BOTH)
                                             .build())
                .setJsonSorting(JsonSorting.builder()
                                           .setAccepted(new ArrayList<String>())
                                           .setRejected(new ArrayList<String>())
                                           .build())
                .build();
    }
}
