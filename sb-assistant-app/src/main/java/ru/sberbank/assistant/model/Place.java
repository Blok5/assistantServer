package ru.sberbank.assistant.model;

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

    public Place(Long externalId, Source source) {
        this.externalId = externalId;
        this.source = source;
    }

    public Place() {
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

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", source=" + source +
                '}';
    }
}
