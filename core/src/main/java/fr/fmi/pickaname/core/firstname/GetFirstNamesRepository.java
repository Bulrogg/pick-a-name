package fr.fmi.pickaname.core.firstname;

import java.util.List;

import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.exception.TechnicalException;

public interface GetFirstNamesRepository {

    List<FirstName> getFirstNames() throws TechnicalException;

}
