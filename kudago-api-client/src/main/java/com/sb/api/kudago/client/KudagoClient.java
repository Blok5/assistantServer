package com.sb.api.kudago.client;


import com.sb.api.kudago.model.Event;
import com.sb.api.kudago.model.response.SearchResponse;

import java.util.Date;

public interface KudagoClient {
    SearchResponse searchEvent(String keyWord, String location, String isFree);

    SearchResponse getEventList(Date DateFrom, Date DateTo, String location, String isFree);

    Event lucky() ;

}
