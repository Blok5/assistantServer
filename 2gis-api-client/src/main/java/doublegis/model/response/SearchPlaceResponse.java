package doublegis.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import doublegis.model.place.PlaceResult;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchPlaceResponse implements Serializable {

    private PlaceResult result;


    public PlaceResult getResult() {
        return result;
    }

    public void setResult(PlaceResult result) {
        this.result = result;
    }
}
