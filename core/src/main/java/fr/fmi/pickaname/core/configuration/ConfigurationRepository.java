package fr.fmi.pickaname.core.configuration;

import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Settings.ResearchType;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;

public interface ConfigurationRepository {

    Settings getSettings() throws TechnicalException;

    Sorting getSorting() throws TechnicalException;

    Settings saveSettings(ResearchType researchType, String lastName) throws TechnicalException;

    void saveAccept(String firstName) throws TechnicalException;

    void saveRefuse(String firstName) throws TechnicalException;

    void reinitializeConfiguration() throws TechnicalException;
}
