package com.sb.api.kudago.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sb.api.kudago.model.Event;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse implements Serializable {

    private int count;
    private List<Event> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Event> getResults() {
        return results;
    }

    public void setResults(List<Event> results) {
        this.results = results;
    }
}
