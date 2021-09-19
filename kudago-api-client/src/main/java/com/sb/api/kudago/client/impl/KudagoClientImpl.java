package com.sb.api.kudago.client.impl;

import com.sb.api.kudago.client.KudagoClient;
import com.sb.api.kudago.model.Event;
import com.sb.api.kudago.model.response.SearchResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

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


    @Override
    public SearchResponse searchEvent(String keyWord, String location, String isFree){
        String searchUrl = url.concat("/search");
        UriComponentsBuilder eventUriBuilder = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("q", keyWord)
                .queryParam("location",location)
                .queryParam("is_free",isFree)
                .queryParam("page_size",100);
        return processEventRequest(eventUriBuilder);
    }


    @Override
    public SearchResponse getEventList(Date DateFrom, Date DateTo, String location, String isFree){
        //        Calendar c = Calendar.getInstance();
//        c.setTime(DateTo);
//        c.add(Calendar.DATE,1);
//        DateTo=c.getTime();
        String searchUrl = url.concat("/events");
        UriComponentsBuilder eventUriBuilder = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("actual_since", DateFrom.getTime()/1000L)
                .queryParam("actual_until", DateTo.getTime()/1000L)
                .queryParam("location",location)
                .queryParam("is_free",isFree)
                .queryParam("page_size",100);

        return processEventRequest(eventUriBuilder);
    }

    private SearchResponse processEventRequest( UriComponentsBuilder loginUriBuilder) {
        SearchResponse response=null;
        HttpEntity<? > entity = new HttpEntity<>(defaultHeaders);
        ResponseEntity<SearchResponse> responseEntity = restTemplate.exchange(loginUriBuilder.toUriString(), HttpMethod.GET, entity, SearchResponse.class);
        if(responseEntity.hasBody()) {
            response = responseEntity.getBody();
        }
        return response;
    }


    @Override
    public Event lucky() {
        Event result=null;
        Date today= new Date();
        SearchResponse response=getEventList(today,today,"msk","");
        if(response!=null && response.getCount()>0){
            int maxSize= Math.min(response.getCount(), 100);
            double luckyPos= Math.random()*maxSize;
            result= response.getResults().get((int) luckyPos);
        }
        return result;
    }
}
