package doublegis.model.route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutePoint implements Serializable {
    private RouteCharacteristic distance;
    private RouteCharacteristic duration;
    private String status;

    public RouteCharacteristic getDistance() {
        return distance;
    }

    public void setDistance(RouteCharacteristic distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RouteCharacteristic getDuration() {
        return duration;
    }

    public void setDuration(RouteCharacteristic duration) {
        this.duration = duration;
    }
}
