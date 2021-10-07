package ru.sberbank.assistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.assistant.model.Event;
import ru.sberbank.assistant.model.Route;
import ru.sberbank.assistant.repository.RouteRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    /**
     *
     * @return
     */
    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    /**
     *
     * @return
     */
    public Route createRoute() {
        return routeRepository.save(new Route());
    }

    public Route getRoutById(Long id) {
        if(!routeRepository.existsById(id)){
            return null;
        }
        return routeRepository.getById(id);
    }

    /**
     *
     * @param routeId
     */
    public void deleteRoute(Long routeId) {
        boolean exist = routeRepository.existsById(routeId);
        if (!exist) {
            throw new IllegalStateException("route with id " + routeId + " doesnt exist");
        }
        routeRepository.deleteById(routeId);
    }

    /**
     *
     * @param event
     * @param routeId
     */
    @Transactional
    public void addEvent(Event event, Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalStateException(
                        "route with id " + routeId + " doesnt exist"));

        route.addEvent(event);
    }

    /**
     *
     * @param event
     * @param routeId
     */
    @Transactional
    public Route deleteEvent(Event event, Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalStateException(
                        "route with id " + routeId + " doesnt exist"));

        route.removeEvent(event);

        return route;
    }


}
