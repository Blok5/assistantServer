package ru.sberbank.assistant.controller;

import doublegis.client.DoubleGisClient;
import doublegis.model.place.Point;
import doublegis.model.route.RouteCharacteristicResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.assistant.model.Event;
import ru.sberbank.assistant.model.Route;
import ru.sberbank.assistant.service.RouteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "Operations with routes")
@RequestMapping(path = "api/v1/route")
public class RouteController {

    private final RouteService routeService;
    private final DoubleGisClient doubleGisClient;

    @Autowired
    public RouteController(RouteService routeService, DoubleGisClient doubleGisClient) {
        this.routeService = routeService;
        this.doubleGisClient = doubleGisClient;
    }

    @ApiOperation(value = "Get all routes")
    @GetMapping
    public List<Route> getRoutes() {
        return routeService.getRoutes();
    }

    @ApiOperation(value = "Get route by Id")
    @GetMapping(path = "{routeId}")
    public Route getRouteById(
            @PathVariable("routeId") Long routeId
    ) {
        return routeService.getRouteById(routeId);
    }

    @ApiOperation(value = "Create new empty route")
    @PostMapping
    public Route createRoute() {
        return routeService.createRoute();
    }

    @ApiOperation(value = "Delete route by Id")
    @DeleteMapping(path = "{routeId}")
    public void deleteRoute(
            @PathVariable("routeId") Long routeId
    ) {
        routeService.deleteRoute(routeId);
    }

    @ApiOperation(value = "Get distances for route events")
    @GetMapping(value = "/distance", produces = "application/json;charset=UTF-8")
    public List<Map<String, RouteCharacteristicResp>> getRouteDistances(
            @RequestParam(name = "routeId") Long routeId,
            @RequestParam(name = "currentLat") double currentLat,
            @RequestParam(name = "currentLon") double currentLon) {

        Route route = routeService.getRoutById(routeId);
        if (route == null) {
            return null;
        }

        List<Point> coords = new ArrayList<>();
        coords.add(new Point(currentLat, currentLon));
        for (Event event : route.getEventList()) {
            if (event.getPlace() != null) {
                coords.add(new Point(event.getPlace().getLat(), event.getPlace().getLon()));
            }
        }

        return doubleGisClient.getDistance(coords);
    }


}