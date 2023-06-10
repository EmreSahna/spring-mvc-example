package com.emresahna.springmvcexample.service;

import com.emresahna.springmvcexample.dto.ClubDto;
import com.emresahna.springmvcexample.dto.EventDto;
import com.emresahna.springmvcexample.model.Event;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    List<EventDto> findAllEvents();
    Event findById(Long id);
    void updateEvent(EventDto event);
    void deleteEvent(Long id);
    EventDto findEventDtoById(Long id);
}
