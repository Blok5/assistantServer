package com.sb.api.kudago.client.impl;

import com.sb.api.kudago.client.KudagoClient;
import com.sb.api.kudago.model.Event;
import com.sb.api.kudago.model.ref.Category;
import com.sb.api.kudago.model.ref.Location;
import com.sb.api.kudago.model.response.SearchResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class KudagoClientImpl implements KudagoClient {


    private final String url;

    private final RestTemplate restTemplate;

    private final HttpHeaders defaultHeaders = new HttpHeaders();

    public KudagoClientImpl(
        String url,
        RestTemplate kudagoRestTemplate
    ) {
        this.url = url;
        this.restTemplate = kudagoRestTemplate;

        defaultHeaders.setContentType(MediaType.APPLICATION_JSON);
    }



    private SearchResponse searchEvent(String keyWord, String location, String isFree){
        String searchUrl = url.concat("/search");
        UriComponentsBuilder eventUriBuilder = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("q", keyWord)
                .queryParam("location",location)
                .queryParam("is_free",isFree)
                .queryParam("page_size",100);
        return processEventRequest(eventUriBuilder.build().toUri());
    }

    @Override
    public SearchResponse getEventList(Date DateFrom, Date DateTo, String location, String isFree, String categories,int pageSize){
        //        Calendar c = Calendar.getInstance();
//        c.setTime(DateTo);
//        c.add(Calendar.DATE,1);
//        DateTo=c.getTime();
        String searchUrl = url.concat("/events");
        UriComponentsBuilder eventUriBuilder = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("actual_since", (DateFrom!=null)?DateFrom.getTime()/1000L:"")
                .queryParam("actual_until", (DateTo!=null)?DateTo.getTime()/1000L:"")
                .queryParam("location",location)
                .queryParam("is_free",isFree)
                .queryParam("fields","id,dates,title,place,description," +
                        "body_text,location,age_restriction,price,is_free,images")
                .queryParam("categories",categories)
                .queryParam("page_size",pageSize);

        return processEventRequest(eventUriBuilder.build().toUri());
    }

    @Override
    public SearchResponse getNextPage(String next) {
        return processEventRequest(URI.create(next));
    }

    private SearchResponse processEventRequest( URI url) {
        SearchResponse response=null;
        HttpEntity<? > entity = new HttpEntity<>(defaultHeaders);
        ResponseEntity<SearchResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, SearchResponse.class);
        if(responseEntity.hasBody()) {
            response = responseEntity.getBody();
        }
        return response;
    }


    @Override
    public List<Event> searchEvents(Date DateFrom, Date DateTo, Location location, String isFree, Category categories,int pageSize) {
        String locationVal=location!= null ?location.getApiVal():"";
        String categoryVal=categories!= null ?categories.getApiVal():"";
        SearchResponse response=getEventList(DateFrom,DateTo,locationVal,isFree,categoryVal,pageSize);
        // TODO: пока принимаем одну категорию (можно несколько)
        if(response!=null && response.getCount()>0){
            return response.getResults();
        }
        return null;
    }

    @Override
    public List<Event> searchEvents(Date DateFrom, Date DateTo, Location location, String isFree, String categories,int pageSize) {
        String locationVal=location!= null ?location.getApiVal():"";
        SearchResponse response=getEventList(DateFrom,DateTo,locationVal,isFree,categories,pageSize);
        // TODO: пока принимаем одну категорию (можно несколько)
        if(response!=null && response.getCount()>0){
            return response.getResults();
        }
        return null;
    }

    @Override
    public Event lucky() {
        Event result=null;
        Date today= new Date();
        SearchResponse response=getEventList(today,today,"msk","","",20);
        if(response!=null && response.getCount()>0){
            int maxSize= Math.min(response.getCount(), 20);
            double luckyPos= Math.random()*maxSize;
            result= response.getResults().get((int) luckyPos);
        }
        return result;
    }
}
