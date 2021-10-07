package ru.sberbank.assistant.controller;

import doublegis.client.DoubleGisClient;
import doublegis.model.place.Place;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.assistant.converter.PlaceToRouteEventConverter;
import ru.sberbank.assistant.model.Event;
import ru.sberbank.assistant.model.Route;
import ru.sberbank.assistant.ref.PlaceType;
import ru.sberbank.assistant.service.EventService;

import java.util.List;

@RestController
@Api(value = "Operations with events")
@RequestMapping(path = "api/v1/route-event")
public class RouteEventController {

    private final EventService eventService;
    private final DoubleGisClient doubleGisClient;
    private final PlaceToRouteEventConverter placeToRouteEventConverter;

    @Autowired
    public RouteEventController(EventService eventService, DoubleGisClient doubleGisClient, PlaceToRouteEventConverter placeToRouteEventConverter) {
        this.eventService = eventService;
        this.doubleGisClient = doubleGisClient;
        this.placeToRouteEventConverter = placeToRouteEventConverter;
    }

    @ApiOperation(value = "Create new event for route and return updated route")
    @PostMapping
    public Route createEvent(
            @RequestBody Event event,
            @RequestParam Long routeId
    ) {
        Route route= eventService.createEventForRoute(event, routeId);
        List<Place> places=doubleGisClient.searchPlace(PlaceType.CAFE.getApiVal(),event.getPlace().getLon(),
                event.getPlace().getLat(),2000);
        if(places!=null && places.size()>0) {
            Event eventRoute =placeToRouteEventConverter.convert(places.get(0));
            if(eventRoute!=null) {
                eventRoute.setDateStart(event.getDateEnd().plusHours(1));
                eventRoute.setDateEnd(eventRoute.getDateStart().plusHours(2));
                eventService.createEventForRoute(eventRoute, routeId);
            }
        }
         return route;
    }

    @ApiOperation(value = "Delete event by Id from route")
    @DeleteMapping(path = "{eventId}")
    public Route deleteEvent(
            @PathVariable("eventId") Long eventId,
            @RequestParam Long routeId
    ) {
        return eventService.deleteEventFromRoute(eventId, routeId);
    }
}
