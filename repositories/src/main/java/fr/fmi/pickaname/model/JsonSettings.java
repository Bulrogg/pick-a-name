package fr.fmi.pickaname.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import fr.fmi.pickaname.core.entities.Settings;

@JsonDeserialize(builder = AutoValue_JsonSettings.Builder.class)
@AutoValue
public abstract class JsonSettings implements Settings {

    public static Builder builder() {
        return new AutoValue_JsonSettings.Builder();
    }

    public abstract String getLastName();

    public abstract Settings.ResearchType getResearchType();

    @SuppressWarnings("unused")
    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("last-name")
        public abstract Builder setLastName(String lastName);

        @JsonProperty("research-type")
        public abstract Builder setResearchType(Settings.ResearchType researchType);

        public abstract JsonSettings build();
    }
}