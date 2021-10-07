package ru.sberbank.assistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.assistant.model.Event;
import ru.sberbank.assistant.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEventForRoute(Event event, Long routeId) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        boolean exist = eventRepository.existsById(eventId);

        if (!exist) {
            throw new IllegalStateException("event with id " + eventId + " doesnt exist");
        }

        eventRepository.deleteById(eventId);
    }
}

