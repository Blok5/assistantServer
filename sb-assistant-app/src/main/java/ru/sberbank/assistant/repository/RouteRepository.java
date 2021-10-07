package ru.sberbank.assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.assistant.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

}
