package doublegis.client.impl;

import doublegis.client.DoubleGisClient;
import doublegis.model.response.SearchPlaceResponse;
import doublegis.model.place.Place;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
        String coordString="";
        String radiusString="";
        if(lon!=null && lat!=null){
            coordString= lon +","+ lat;
        }
        if(radius!=null){
            radiusString=radius.toString();
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/items")
                .queryParam("q",query)
                .queryParam("point",coordString)
                .queryParam("radius",radiusString)
                .queryParam("region_id",32)
                .queryParam("has_photos",true)
                .queryParam("has_rating",true)
                .queryParam("sort","rating")
                .queryParam("fields","items.point,items.delivery,items.reviews,items.schedule,items.external_content")
                .queryParam("key",key);
        List<Place> result=null;
        SearchPlaceResponse searchResponse=restTemplate.getForObject(
                builder.build().encode().toUri(), SearchPlaceResponse.class);

        if(searchResponse!=null&& searchResponse.getResult()!=null){
            result=searchResponse.getResult().getItems();
        }

        return result;
    }


}
