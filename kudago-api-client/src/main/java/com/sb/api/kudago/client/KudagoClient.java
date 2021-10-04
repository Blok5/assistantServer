package com.sb.api.kudago.client;


import com.sb.api.kudago.model.Event;
import com.sb.api.kudago.model.ref.Category;
import com.sb.api.kudago.model.ref.Location;
import com.sb.api.kudago.model.response.SearchResponse;

import java.util.Date;
import java.util.List;

public interface KudagoClient {
//    SearchResponse searchEvent(String keyWord, String location, String isFree);
//
    SearchResponse getEventList(Date DateFrom, Date DateTo, String location, String isFree,String category,int pageSize);

    SearchResponse getNextPage(String next);

    List<Event> searchEvents(Date DateFrom, Date DateTo, Location location, String isFree, Category categories,int pageSize);


    List<Event> searchEvents(Date DateFrom, Date DateTo, Location location, String isFree, String categories,int pageSize);


    Event lucky() ;

}
