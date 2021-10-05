package doublegis.model.place;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceResult implements Serializable {

    private List<Place> items;

    public List<Place> getItems() {
        return items;
    }

    public void setItems(List<Place> items) {
        this.items = items;
    }
}
