package fr.fmi.pickaname.core.entities;

import java.util.List;

public interface Configuration {

    String getLastName();

    ResearchType getResearchType();

    List<FirstName> getFirstNameAccepted();

    List<FirstName> getFirstNameRejected();

    enum ResearchType {
        GIRL, BOY, BOTH
    }

}
