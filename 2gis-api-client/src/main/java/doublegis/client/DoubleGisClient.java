package doublegis.client;


import doublegis.model.place.Place;

import java.util.List;

public interface DoubleGisClient {

    List<Place> searchPlace(String query,Double lon, Double lat,Integer radius);
}
