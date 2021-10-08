package doublegis.model.route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteResult implements Serializable {

    private List<RoutePoint> elements;

    public List<RoutePoint> getElements() {
        return elements;
    }

    public void setElements(List<RoutePoint> elements) {
        this.elements = elements;
    }
}
