package fr.fmi.pickaname.core.entities;

public interface FirstName {

    String getFirstName();

    Gender getGender();

    enum Gender {
        MALE, FEMALE
    }

}
