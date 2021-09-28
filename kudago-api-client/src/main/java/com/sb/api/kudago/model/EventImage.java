package com.sb.api.kudago.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class EventImage implements Serializable {
    @JsonProperty(value = "image")
    private String imageUrl;
    
}
