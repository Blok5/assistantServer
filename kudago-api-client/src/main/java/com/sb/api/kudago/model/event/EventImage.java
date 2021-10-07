package com.sb.api.kudago.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class EventImage implements Serializable {
    @JsonProperty(value = "image")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
