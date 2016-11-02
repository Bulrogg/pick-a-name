package fr.fmi.pickaname.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import fr.fmi.pickaname.core.entities.FirstName;

@JsonDeserialize(builder = AutoValue_JsonFirstName.Builder.class)
@AutoValue
public abstract class JsonFirstName implements FirstName {

    public static Builder builder() {
        return new AutoValue_JsonFirstName.Builder();
    }

    public abstract String getFirstName();

    public abstract Gender getGender();

    @SuppressWarnings("unused")
    @AutoValue.Builder
    abstract static class Builder {

        @JsonProperty("first-name")
        public abstract Builder setFirstName(String firstName);

        @JsonProperty("gender")
        public abstract Builder setGender(Gender gender);

        public abstract JsonFirstName build();
    }
}