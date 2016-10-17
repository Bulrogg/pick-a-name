package fr.fmi.pickaname.core.entities;

public interface Settings {

    String getLastName();

    ResearchType getResearchType();

    enum ResearchType {
        GIRL, BOY, BOTH
    }

}
