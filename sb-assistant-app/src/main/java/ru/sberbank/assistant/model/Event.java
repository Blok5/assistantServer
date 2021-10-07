package ru.sberbank.assistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.sberbank.assistant.ref.EventType;
import ru.sberbank.assistant.ref.Source;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Event")
@Table(name = "event")
public class Event {

    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "event_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "ageRestriction"
    )
    private String ageRestriction;

    @Column(
            name = "price"
    )
    private String price;

    @Column(
            name = "rating"
    )
    private double rating;

    @Column(
            name = "image_url"
    )
    private String imageUrl;


    @Column(
            name = "external_id",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private Long externalId;

    @Column(
            name = "source",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private Source source;

    @Column(
            name = "type",
            columnDefinition = "TEXT"
    )
    private EventType type;

    @Column(
            name = "date_start",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime dateStart;

    @Column(
            name = "date_end",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime dateEnd;

    @ManyToOne
    @JoinColumn(
            name = "route_id",
            foreignKey = @ForeignKey(name = "FK_event_route")
    )
    private Route route;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "place_id"
    )
    private Place place;

    public Event() {
    }

    public Event(Long externalId,
                 Source source,
                 EventType type,
                 LocalDateTime dateStart,
                 LocalDateTime dateEnd,
                 Route route,
                 Place place) {
        this.externalId = externalId;
        this.source = source;
        this.type = type;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.route = route;
        this.place = place;
    }

    private Event(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setAgeRestriction(builder.ageRestriction);
        setPrice(builder.price);
        setRating(builder.rating);
        setImageUrl(builder.imageUrl);
        setExternalId(builder.externalId);
        setSource(builder.source);
        setType(builder.type);
        setDateStart(builder.dateStart);
        setDateEnd(builder.dateEnd);
        setRoute(builder.route);
        setPlace(builder.place);
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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setRoute(Route route) {
        setRoute(route, true);
    }

    public void setRoute(Route route, boolean add) {
        this.route = route;
        if (route != null && add)  {
            route.addEvent(this, false);
        }
    }

    @JsonIgnore
    public Route getRoute() {
        return route;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", source=" + source +
                ", type=" + type +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", route=" + route +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String description;
        private String ageRestriction;
        private String price;
        private double rating;
        private String imageUrl;
        private Long externalId;
        private Source source;
        private EventType type;
        private LocalDateTime dateStart;
        private LocalDateTime dateEnd;
        private Route route;
        private Place place;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder ageRestriction(String val) {
            ageRestriction = val;
            return this;
        }

        public Builder price(String val) {
            price = val;
            return this;
        }

        public Builder rating(double val) {
            rating = val;
            return this;
        }

        public Builder imageUrl(String val) {
            imageUrl = val;
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

        public Builder type(EventType val) {
            type = val;
            return this;
        }

        public Builder dateStart(LocalDateTime val) {
            dateStart = val;
            return this;
        }

        public Builder dateEnd(LocalDateTime val) {
            dateEnd = val;
            return this;
        }

        public Builder route(Route val) {
            route = val;
            return this;
        }

        public Builder place(Place val) {
            place = val;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
