package doublegis.model.route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteCharacteristicResp implements Serializable {
    private String distance;
    private long distanceVal;
    private String duration;
    private long durationVal;
    private int cost;


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public long getDistanceVal() {
        return distanceVal;
    }

    public void setDistanceVal(long distanceVal) {
        this.distanceVal = distanceVal;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getDurationVal() {
        return durationVal;
    }

    public void setDurationVal(long durationVal) {
        this.durationVal = durationVal;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
