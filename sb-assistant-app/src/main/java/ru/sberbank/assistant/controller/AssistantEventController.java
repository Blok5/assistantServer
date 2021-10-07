package ru.sberbank.assistant.controller;

import com.sb.api.kudago.model.event.Event;
import com.sb.api.kudago.model.ref.Category;
import com.sb.api.kudago.model.ref.Location;
import doublegis.client.DoubleGisClient;
import doublegis.model.place.Point;
import doublegis.model.route.RouteCharacteristicResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.assistant.component.KudagoCache;
import ru.sberbank.assistant.converter.EventToRouteEventConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "Operations with events")
@RequestMapping(path = "api/event/v1")
public class AssistantEventController {

    private final KudagoCache kudagoCache;
    private final DoubleGisClient doubleGisClient;
    private final EventToRouteEventConverter eventToRouteEventConverter;

    @Autowired
    public AssistantEventController(KudagoCache kudagoCache, DoubleGisClient doubleGisClient, EventToRouteEventConverter eventToRouteEventConverter) {
        this.kudagoCache = kudagoCache;
        this.doubleGisClient = doubleGisClient;
        this.eventToRouteEventConverter = eventToRouteEventConverter;
    }

    @ApiOperation(value = "Test the api application is working correctly")
    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello World";
    }

    @ApiOperation(value = "Test the api application is working correctly")
    @GetMapping(value = "/testGoogle")
    public List<Map<String, RouteCharacteristicResp>> testGoogle() {
        List <Point> points=new ArrayList<>();
        points.add(new Point(55.546975,37.4730373));
        points.add(new Point(55.78139299999999,37.598528));
        points.add(new Point(55.83273199999982,37.61831599999973));
        return doubleGisClient.getDistance(points);
    }

    @ApiOperation(value = "Get allowed values for enums")
    @GetMapping(value = "/allowedValues")
    public String getAllowedValues() {
        return "Location: "+ Location.validValues()+"\n"+
                "Category: "+ Category.validValues();
    }

    @ApiOperation(value = "Get random today event if you are lucky")
    @GetMapping(value = "/lucky", produces = "application/json;charset=UTF-8")
    public ru.sberbank.assistant.model.Event getLuckyEvent() {
        Event event=kudagoCache.lucky();
        if(event!=null){
            return eventToRouteEventConverter.convert(event);
        }
            return null;

    }

    @ApiOperation(value = "Search events by different filters")
    @GetMapping(value = "/search-events", produces = "application/json;charset=UTF-8")
    public List<Event> searchEvents( @RequestParam(name="dateTo",required=false) Date dateTo,
                                     @RequestParam(name="dateFrom",required=false) Date dateFrom,
                                     @ApiParam(allowableValues="spb,msk,nsk,ekb,nnv,kzn,vbg,smr,krd,sochi,ufa,krasnoyarsk,kev") @RequestParam(name="location",required=false) Location location,
                                     @RequestParam(name="isFree",required=false) String isFree,
                                     @ApiParam(allowableValues="cinema,concert,education,entertainment,exhibition,fashion,festival,kids,party,quest,theater,tour,yarmarki-razvlecheniya-yarmarki") @RequestParam(name="categories",required=false) Category categories){


                                     return kudagoCache.searchEvents(dateTo,dateFrom,location,isFree,categories);
    }

}
