package doublegis.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import doublegis.model.place.PlaceResult;
import doublegis.model.route.RoutePoint;
import doublegis.model.route.RouteResult;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRouteResponse implements Serializable {

    private List <RouteResult> rows;
    private String status;

    public List<RouteResult> getRows() {
        return rows;
    }

    public void setRows(List<RouteResult> rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
