package ru.sberbank.assistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.assistant.model.Event;
import ru.sberbank.assistant.model.Route;
import ru.sberbank.assistant.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final RouteService routeService;

    @Autowired
    public EventService(EventRepository eventRepository, RouteService routeService) {
        this.eventRepository = eventRepository;
        this.routeService = routeService;
    }

    /**
     * @param event
     * @param routeId
     * @return
     */
    public Route createEventForRoute(Event event, Long routeId) {
        return routeService.addEvent(event, routeId);
    }

    /**
     * @param eventId
     * @param routeId
     */
    public Route deleteEventFromRoute(Long eventId, Long routeId) {
        Event event = getEventById(eventId);
        return routeService.deleteEvent(event, routeId);
    }

    public Event getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalStateException(
                        "route with id " + eventId + " doesnt exist"));

        return event;
    }
}

