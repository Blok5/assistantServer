package ru.sberbank.assistant.ref;

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
    YARMARKI("yarmarki-razvlecheniya-yarmarki");

    private String apiVal;

    EventType(String apiVal) {
        this.apiVal = apiVal;
    }

}
