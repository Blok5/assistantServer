package ru.sberbank.assistant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.assistant.model.Event;
import ru.sberbank.assistant.model.Route;
import ru.sberbank.assistant.service.EventService;

@RestController
@Api(value = "Operations with events")
@RequestMapping(path = "api/v1/route-event")
public class RouteEventController {

    private final EventService eventService;

    @Autowired
    public RouteEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ApiOperation(value = "Create new event for route and return updated route")
    @PostMapping
    public void createEvent(
            @RequestBody Event event,
            @RequestParam Long routeId
    ) {
         eventService.createEventForRoute(event, routeId);
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
