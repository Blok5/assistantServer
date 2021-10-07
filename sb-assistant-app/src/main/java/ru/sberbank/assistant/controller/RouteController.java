package ru.sberbank.assistant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.assistant.model.Route;
import ru.sberbank.assistant.service.RouteService;

import java.util.List;

@RestController
@Api(value = "Operations with routes")
@RequestMapping(path = "api/v1/route")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ApiOperation(value = "Get all routes")
    @GetMapping
    public List<Route> getRoutes() {
        return routeService.getRoutes();
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
}