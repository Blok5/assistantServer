package doublegis.client;


import doublegis.model.place.Place;
import doublegis.model.place.Point;
import doublegis.model.route.RouteCharacteristicResp;

import java.util.List;
import java.util.Map;

public interface DoubleGisClient {

    List<Place> searchPlace(String query, Double lon, Double lat, Integer radius);

    List<Map<String, RouteCharacteristicResp>> getDistance(List<Point> coords);
}
