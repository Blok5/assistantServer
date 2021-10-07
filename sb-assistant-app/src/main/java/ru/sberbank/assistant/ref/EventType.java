package ru.sberbank.assistant.ref;

import com.sb.api.kudago.model.ref.Category;

public enum EventType {
    CINEMA("cinema"),
    CONCERT("concert"),
    EDUCATION("education"),
    ENTERTAINMENT("entertainment"),
    EXHIBITION("exhibition"),
    FASHION("fashion"),
    FESTIVAL("festival"),
    KIDS("kids"),
    PARTY("party"),
    QUEST("quest"),
    THEATER("theater"),
    TOUR("tour"),
    YARMARKI("yarmarki-razvlecheniya-yarmarki"),
    CAFE("cafe");


    private String apiVal;

    EventType(String apiVal) {
        this.apiVal = apiVal;
    }

    public static EventType fromApiVal(String apiVal) {
        for (EventType location : values()) {
            if (location.apiVal.equals(apiVal)) {
                return location;
            }
        }
        throw new IllegalArgumentException("Unknown category "+ apiVal);
    }
}
