package ru.sberbank.assistant.ref;

public enum PlaceType {
    CINEMA("cinema", "Кинотеатр"),
    CAFE("cafe-restaurant", "Кафе рестораны"),
    THEATER("theater", "Театр"),
    PUB("pub", "Бар");
    //    ,
//    EDUCATION("education"),
//    ENTERTAINMENT("entertainment"),
//    EXHIBITION("exhibition"),
//    FASHION("fashion"),
//    FESTIVAL("festival"),
//    KIDS("kids"),
//    PARTY("party"),
//    QUEST("quest"),
//    THEATER("theater"),
//    TOUR("tour"),
//    YARMARKI("yarmarki-razvlecheniya-yarmarki");
    private String frontVal;
    private String apiVal;

    private PlaceType(String frontVal, String apiVal) {
        this.frontVal = frontVal;
        this.apiVal = apiVal;
    }

    public String getApiVal() {
        return apiVal;
    }

    public static PlaceType fromFrontVal(String frontVal) {
        for (PlaceType location : values()) {
            if (location.frontVal.equals(frontVal)) {
                return location;
            }
        }
        throw new IllegalArgumentException("Unknown category " + frontVal);
    }

    //для генерации аннотации allowedValues
    public static String validValues() {
        StringBuilder res = new StringBuilder();
        for (PlaceType location : values()) {
            res.append(location.frontVal).append(",");
        }
        return res.substring(0, res.length() - 1);
    }
}
