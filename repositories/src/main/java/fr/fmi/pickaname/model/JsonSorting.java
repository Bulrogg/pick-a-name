package fr.fmi.pickaname.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import java.util.List;

import fr.fmi.pickaname.core.entities.Sorting;

@JsonDeserialize(builder = AutoValue_JsonSorting.Builder.class)
@AutoValue
public abstract class JsonSorting implements Sorting {

    public static Builder builder() {
        return new AutoValue_JsonSorting.Builder();
    }

    public static JsonSorting.Builder copy(final Sorting source) {
        return builder()
                .setAccepted(source.getAccepted())
                .setRejected(source.getRejected());
    }

    public abstract List<String> getAccepted();

    public abstract List<String> getRejected();

    @SuppressWarnings("unused")
    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("accepted")
        public abstract Builder setAccepted(List<String> accepted);

        @JsonProperty("rejected")
        public abstract Builder setRejected(List<String> rejected);

        public abstract JsonSorting build();
    }
}