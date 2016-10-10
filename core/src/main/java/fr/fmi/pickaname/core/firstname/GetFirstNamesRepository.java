package fr.fmi.pickaname.core.firstname;

import java.util.List;

import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.exception.TechnicalException;

// TODO ooo gérer un cache en RAM dans le repository pour ne pas avoir à faire les appels json
public interface GetFirstNamesRepository {

    List<FirstName> getFirstNames() throws TechnicalException;

}
