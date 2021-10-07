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
            nullable = false,
            columnDefinition = "TEXT"
    )
    private EventType type;

    @Column(
            name = "date_start",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime dateStart;

    @Column(
            name = "date_end",
            nullable = false,
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

    public Long getId() {
        return id;
    }

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
}
