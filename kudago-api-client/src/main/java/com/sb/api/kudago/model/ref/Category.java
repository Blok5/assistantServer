package com.sb.api.kudago.model.ref;

public enum Category {
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

    private Category(String apiVal) {
        this.apiVal = apiVal;
    }

    public String getApiVal() {
        return apiVal;
    }

    public static Category fromApiVal(String apiVal) {
        for (Category location : values()) {
            if (location.apiVal.equals(apiVal)) {
                return location;
            }
        }
        throw new IllegalArgumentException("Unknown category " + apiVal);
    }

    //для генерации аннотации allowedValues
    public static String validValues() {
        StringBuilder res = new StringBuilder();
        for (Category location : values()) {
            res.append(location.apiVal).append(",");
        }
        return res.substring(0, res.length() - 1);
    }
}
