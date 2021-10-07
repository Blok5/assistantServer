package ru.sberbank.assistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.assistant.model.Route;
import ru.sberbank.assistant.repository.RouteRepository;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    public Route createRoute() {
        return routeRepository.save(new Route());
    }

    public void deleteRoute(Long routeId) {
        boolean exist = routeRepository.existsById(routeId);

        if (!exist) {
            throw new IllegalStateException("route with id " + routeId + "doesnt exist");
        }

        routeRepository.deleteById(routeId);
    }

    public void updateRoute(Long routeId) {
        boolean exist = routeRepository.existsById(routeId);

        if (!exist) {
            throw new IllegalStateException("route with id " + routeId + " doesnt exist");
        }

        routeRepository.deleteById(routeId);
    }
}
