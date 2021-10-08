package doublegis.client.impl;

import doublegis.client.DoubleGisClient;
import doublegis.model.place.Place;
import doublegis.model.place.Point;
import doublegis.model.response.GetRouteResponse;
import doublegis.model.response.SearchPlaceResponse;
import doublegis.model.route.RouteCharacteristicResp;
import doublegis.model.route.RoutePoint;
import doublegis.model.route.RouteResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoubleGisClientImpl implements DoubleGisClient {


    private final String url;
    private final String key;

    private final RestTemplate restTemplate;


    public DoubleGisClientImpl(
            String url, String key,
            RestTemplate doubleGisRestTemplate
    ) {
        this.url = url;
        this.key = key;
        this.restTemplate = doubleGisRestTemplate;

    }


    @Override
    public List<Place> searchPlace(String query, Double lon, Double lat, Integer radius) {
        String coordString = "";
        String radiusString = "";
        if (lon != null && lat != null) {
            coordString = lon + "," + lat;
        }
        if (radius != null) {
            radiusString = radius.toString();
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/3.0/items")
                .queryParam("q", query)
                .queryParam("point", coordString)
                .queryParam("radius", radiusString)
                .queryParam("region_id", 32)
                .queryParam("has_photos", true)
                .queryParam("has_rating", true)
                .queryParam("sort", "flamp_rating")
                .queryParam("fields", "items.point,items.delivery,items.reviews,items.schedule,items.external_content")
                .queryParam("key", key);
        List<Place> result = null;
        SearchPlaceResponse searchResponse = restTemplate.getForObject(
                builder.build().encode().toUri(), SearchPlaceResponse.class);

        if (searchResponse != null && searchResponse.getResult() != null) {
            result = searchResponse.getResult().getItems();
        }

        return result;
    }

    @Override
    public List<Map<String, RouteCharacteristicResp>> getDistance(List<Point> coords) {
        List<Map<String, RouteCharacteristicResp>> result = new ArrayList<>();
        String[] types = {"DRIVING", "WALKING", "TRANSIT"};
        StringBuilder distances = new StringBuilder();
        StringBuilder origins = new StringBuilder();
        for (int i = 0; i < coords.size() - 1; i++) {
            Point p1 = coords.get(i);
            Point p2 = coords.get(i + 1);
            origins.append("|").append(p1.getTextView());
            distances.append("|").append(p2.getTextView());
            // создаем элемент на каждую пару по порядку
            result.add(new HashMap<>());
        }
        for (String type : types) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/distancematrix/json")
                    .queryParam("destinations", distances.substring(1))
                    .queryParam("origins", origins.substring(1))
                    .queryParam("mode", type.toLowerCase())
                    .queryParam("key", "AIzaSyDWjiZVEzF-1-uS2NZEbldH8zRWKCP2htU");

            GetRouteResponse searchResponse = restTemplate.getForObject(
                    builder.build().encode().toUri(), GetRouteResponse.class);

            if (searchResponse != null) {
                if (searchResponse.getStatus().equals("OK")) {
                    //для каждой пары ищем соответствие в результате
                    for (int i = 0; i < result.size(); i++) {
                        if (searchResponse.getRows() != null) {
                            try {
                                RouteResult row = searchResponse.getRows().get(i);
                                if (row.getElements() != null) {

                                    RouteCharacteristicResp dist = new RouteCharacteristicResp();
                                    RoutePoint currentElem = row.getElements().get(i);
                                    if (currentElem.getStatus().equals("OK")) {
                                        dist.setDistance(currentElem.getDistance().getText());
                                        dist.setDistanceVal(currentElem.getDistance().getValue());
                                        dist.setDuration(currentElem.getDuration().getText());
                                        dist.setDurationVal(currentElem.getDuration().getValue());
                                        if (type.equals("DRIVING")) {
                                            dist.setCost((int) (currentElem.getDuration().getValue() / 60 * 17));
                                        }
                                        result.get(i).put(type, dist);
                                    }

                                }
                            } catch (Exception e) { // ловим все!

                            }
                        }

                    }
                }
            }
        }
        return result;
    }


}
