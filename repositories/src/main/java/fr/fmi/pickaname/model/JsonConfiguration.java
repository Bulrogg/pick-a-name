package fr.fmi.pickaname.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import java.util.List;

import fr.fmi.pickaname.core.entities.Configuration;
import fr.fmi.pickaname.core.entities.FirstName;

@JsonDeserialize(builder = AutoValue_JsonConfiguration.Builder.class)
@AutoValue
public abstract class JsonConfiguration implements Configuration {

    public abstract String getLastName();

    public abstract ResearchType getResearchType();

    public abstract List<FirstName> getFirstNameAccepted();

    public abstract List<FirstName> getFirstNameRejected();


    @SuppressWarnings("unused")
    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("last-name")
        public abstract Builder setLastName(String lastName);

        @JsonProperty("research-type")
        public abstract Builder setResearchType(ResearchType researchType);

        @JsonProperty("first-name-accepted")
        public abstract Builder setFirstNameAccepted(List<FirstName> firstNameAccepted);

        @JsonProperty("first-name-rejected")
        public abstract Builder setFirstNameRejected(List<FirstName> firstNameRejected);

        public abstract JsonConfiguration build();
    }
}