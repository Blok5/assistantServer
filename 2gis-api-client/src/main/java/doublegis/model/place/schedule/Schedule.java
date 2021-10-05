package doublegis.model.place.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import doublegis.model.place.schedule.dayofweek.DayOfWeek;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule implements Serializable {
    @JsonProperty(value = "Mon")
    private DayOfWeek mon;
    @JsonProperty(value = "Tue")
    private DayOfWeek tue;
    @JsonProperty(value = "Wed")
    private DayOfWeek wed;
    @JsonProperty(value = "Thu")
    private DayOfWeek thu;
    @JsonProperty(value = "Fri")
    private DayOfWeek fri;
    @JsonProperty(value = "Sat")
    private DayOfWeek sat;
    @JsonProperty(value = "Sun")
    private DayOfWeek sun;

    public DayOfWeek getMon() {
        return mon;
    }

    public void setMon(DayOfWeek mon) {
        this.mon = mon;
    }

    public DayOfWeek getTue() {
        return tue;
    }

    public void setTue(DayOfWeek tue) {
        this.tue = tue;
    }

    public DayOfWeek getWed() {
        return wed;
    }

    public void setWed(DayOfWeek wed) {
        this.wed = wed;
    }

    public DayOfWeek getThu() {
        return thu;
    }

    public void setThu(DayOfWeek thu) {
        this.thu = thu;
    }

    public DayOfWeek getFri() {
        return fri;
    }

    public void setFri(DayOfWeek fri) {
        this.fri = fri;
    }

    public DayOfWeek getSat() {
        return sat;
    }

    public void setSat(DayOfWeek sat) {
        this.sat = sat;
    }

    public DayOfWeek getSun() {
        return sun;
    }

    public void setSun(DayOfWeek sun) {
        this.sun = sun;
    }
}
