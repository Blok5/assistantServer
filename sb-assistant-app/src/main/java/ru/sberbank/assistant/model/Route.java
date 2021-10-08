package ru.sberbank.assistant.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Route")
@Table(name = "route")
public class Route {

    @Id
    @SequenceGenerator(
            name = "route_sequence",
            sequenceName = "route_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "route_sequence"
    )
    @Column(
            name = "route_id",
            updatable = false
    )
    private Long id;

    @OneToMany(
            mappedBy = "route",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Event> eventList = new ArrayList<>();

    public void addEvent(Event event) {
        addEvent(event, true);
    }

    public void addEvent(Event event, boolean set) {
        if (getEventList().contains(event)) {
            getEventList().set(getEventList().indexOf(event), event);
        } else {
            getEventList().add(event);
        }
        if (set) {
            event.setRoute(this, false);
        }

    }

    public void removeEvent(Event event) {
        getEventList().remove(event);
        event.setRoute(null, false);
    }

    public Route() {
    }

    public Route(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }


    public void removeEventById(Long eventId) {
        this.eventList.removeIf(
                event -> event.getId().equals(eventId));
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", eventList=" + eventList +
                '}';
    }
}
