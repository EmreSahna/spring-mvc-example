package com.emresahna.springmvcexample.service;

import com.emresahna.springmvcexample.dto.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    List<EventDto> findAllEvents();
}
