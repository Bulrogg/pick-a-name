package fr.fmi.pickaname.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import fr.fmi.pickaname.core.entities.Configuration;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Sorting;

@JsonDeserialize(builder = AutoValue_JsonConfiguration.Builder.class)
@AutoValue
public abstract class JsonConfiguration implements Configuration {

    public static Builder builder() {
        return new AutoValue_JsonConfiguration.Builder();
    }

    public abstract Settings getSettings();

    public abstract Sorting getSorting();

    @SuppressWarnings("unused")
    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("settings")
        public abstract Builder setSettings(Settings settings);

        @JsonProperty("sorting")
        public abstract Builder setSorting(Sorting sorting);

        public abstract JsonConfiguration build();
    }
}