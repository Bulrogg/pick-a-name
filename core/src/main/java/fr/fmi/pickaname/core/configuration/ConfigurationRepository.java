package fr.fmi.pickaname.core.configuration;

import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;

public interface ConfigurationRepository {

    Settings getSettings() throws TechnicalException;

    Sorting getSorting() throws TechnicalException;

}
