package ru.sberbank.assistant.model;

import javax.persistence.*;

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
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "event_id",
            foreignKey = @ForeignKey(
                    name = "FK_event_route"
            )

    )
    private List<Event> eventList;

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

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", eventList=" + eventList +
                '}';
    }
}
