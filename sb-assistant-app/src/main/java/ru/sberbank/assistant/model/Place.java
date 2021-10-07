package ru.sberbank.assistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.sberbank.assistant.ref.Source;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "place")
@Table(name = "place")
public class Place {

    @Id
    @SequenceGenerator(
            name = "place_sequence",
            sequenceName = "place_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "place_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "external_id",
            columnDefinition = "TEXT"
    )
    private Long externalId;

    @Column(
            name = "source",
            columnDefinition = "TEXT"
    )
    private Source source;

    @Column(
            name = "lat"
    )
    private double lat;

    @Column(
            name = "lon"
    )
    private double lon;

    @Column(
            name = "address"
    )
    private String address;


    public Place(Long externalId, Source source) {
        this.externalId = externalId;
        this.source = source;
    }

    public Place() {
    }

    private Place(Builder builder) {
        setId(builder.id);
        setExternalId(builder.externalId);
        setSource(builder.source);
        setLat(builder.lat);
        setLon(builder.lon);
        setAddress(builder.address);
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", source=" + source +
                '}';
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public static final class Builder {
        private Long id;
        private Long externalId;
        private Source source;
        private double lat;
        private double lon;
        private String address;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder externalId(Long val) {
            externalId = val;
            return this;
        }

        public Builder source(Source val) {
            source = val;
            return this;
        }

        public Builder lat(double val) {
            lat = val;
            return this;
        }

        public Builder lon(double val) {
            lon = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }


        public Place build() {
            return new Place(this);
        }
    }
}
