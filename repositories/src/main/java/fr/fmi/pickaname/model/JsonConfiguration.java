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

    public static Builder copy(final JsonConfiguration source) {
        return builder()
                .setJsonSettings(source.getJsonSettings())
                .setJsonSorting(source.getJsonSorting());
    }

    public abstract JsonSettings getJsonSettings();

    public abstract JsonSorting getJsonSorting();

    public Settings getSettings() {
        return getJsonSettings();
    }

    public Sorting getSorting() {
        return getJsonSorting();
    }

    @SuppressWarnings("unused")
    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("settings")
        public abstract Builder setJsonSettings(JsonSettings settings);

        @JsonProperty("sorting")
        public abstract Builder setJsonSorting(JsonSorting sorting);

        public abstract JsonConfiguration build();
    }
}