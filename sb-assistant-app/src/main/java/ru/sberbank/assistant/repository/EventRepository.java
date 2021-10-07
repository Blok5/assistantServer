package ru.sberbank.assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.assistant.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
