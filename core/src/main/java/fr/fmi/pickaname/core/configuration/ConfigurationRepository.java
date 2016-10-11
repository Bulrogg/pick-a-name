package fr.fmi.pickaname.core.configuration;

import fr.fmi.pickaname.core.entities.Configuration;
import fr.fmi.pickaname.core.exception.TechnicalException;

public interface ConfigurationRepository {

    Configuration getUserConfiguration() throws TechnicalException;

}
