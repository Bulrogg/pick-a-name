package fr.fmi.pickaname.core.configuration;

import fr.fmi.pickaname.core.entities.Configuration;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Settings.ResearchType;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;

public interface ConfigurationRepository {

    Settings getSettings() throws TechnicalException;

    Sorting getSorting() throws TechnicalException;

    Configuration saveSettings(ResearchType researchType, String lastName) throws TechnicalException;
}
