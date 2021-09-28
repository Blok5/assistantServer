package com.sb.api.kudago.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements Serializable {
    private String title;
    @JsonProperty(value = "favorites_count")
    private int favoritesCount;
    private String description;
    private String id;
    private EventDate [] dates;
    @JsonProperty(value = "age_restriction")
    private String ageRestriction;
    private String price;
    @JsonProperty(value = "is_free")
    private boolean isFree;
    private EventImage [] images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventDate[] getDates() {
        return dates;
    }

    public void setDates(EventDate[] dates) {
        this.dates = dates;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public EventImage[] getImages() {
        return images;
    }

    public void setImages(EventImage[] images) {
        this.images = images;
    }
}
