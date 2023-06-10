package com.emresahna.springmvcexample.repository;

import com.emresahna.springmvcexample.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
