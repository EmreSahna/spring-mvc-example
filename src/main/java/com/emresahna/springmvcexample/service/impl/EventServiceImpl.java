package com.emresahna.springmvcexample.service.impl;

import com.emresahna.springmvcexample.dto.EventDto;
import com.emresahna.springmvcexample.mapper.EventMapper;
import com.emresahna.springmvcexample.model.Club;
import com.emresahna.springmvcexample.model.Event;
import com.emresahna.springmvcexample.repository.EventRepository;
import com.emresahna.springmvcexample.service.ClubService;
import com.emresahna.springmvcexample.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.emresahna.springmvcexample.mapper.EventMapper.mapToEvent;
import static com.emresahna.springmvcexample.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ClubService clubService;

    public EventServiceImpl(EventRepository eventRepository, ClubService clubService) {
        this.eventRepository = eventRepository;
        this.clubService = clubService;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubService.findById(clubId);
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public EventDto findEventDtoById(Long id) {
        Event event = findById(id);
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
