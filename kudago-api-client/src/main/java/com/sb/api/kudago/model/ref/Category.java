package com.sb.api.kudago.model.ref;

public enum Category {
    CINEMA("cinema","Кино"),
    CONCERT("concert","Концерты"),
    EDUCATION("education","Образование"),
    ENTERTAINMENT("entertainment","Развлечения"),
    EXHIBITION("exhibition","Выставки"),
    FASHION("fashion","Мода"),
    FESTIVAL("festival","Фестивали"),
    KIDS("kids","Дети"),
    PARTY("party","Вечеринки"),
    QUEST("quest","Квесты"),
    THEATER("theater","Театры"),
    TOUR("tour","Туры"),
    YARMARKI("yarmarki-razvlecheniya-yarmarki","Ярмарки");

    private String apiVal;
    private String localizedVal;

    private Category(String apiVal, String localizedVal) {
        this.apiVal = apiVal;
        this.localizedVal=localizedVal;
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

    public String getLocalizedVal() {
        return localizedVal;
    }

    public void setLocalizedVal(String localizedVal) {
        this.localizedVal = localizedVal;
    }
}
