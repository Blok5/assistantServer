package com.sb.api.kudago.client;


import com.sb.api.kudago.model.event.Event;
import com.sb.api.kudago.model.place.Place;
import com.sb.api.kudago.model.ref.Category;
import com.sb.api.kudago.model.ref.Location;
import com.sb.api.kudago.model.response.SearchEventResponse;

import java.util.Date;
import java.util.List;

public interface KudagoClient {
    //    SearchResponse searchEvent(String keyWord, String location, String isFree);
//
    SearchEventResponse getEventList(Date DateFrom, Date DateTo, String location, String isFree, String category, int pageSize);

    SearchEventResponse getNextPage(String next);

    List<Event> searchEvents(Date DateFrom, Date DateTo, Location location, String isFree, Category categories, int pageSize);


    List<Event> searchEvents(Date DateFrom, Date DateTo, Location location, String isFree, String categories, int pageSize);

    Place getPlace(String id);


    Event lucky();

}
