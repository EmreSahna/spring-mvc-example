package com.emresahna.springmvcexample.controller;

import com.emresahna.springmvcexample.dto.ClubDto;
import com.emresahna.springmvcexample.dto.EventDto;
import com.emresahna.springmvcexample.model.Event;
import com.emresahna.springmvcexample.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String events(Model model) {
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{eventId}")
    public String eventsByClub(@PathVariable("eventId") Long eventId, Model model) {
        EventDto event = eventService.findEventDtoById(eventId);
        model.addAttribute("event", event);
        return "events-view";
    }

    @GetMapping("/events/{clubId}/new")
    public String newEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping("/events/{clubId}")
    public String newEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto, Model model) {
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events/edit/{id}")
    public String editEditForm(@PathVariable("id") Long id, Model model){
        EventDto event = eventService.findEventDtoById(id);
        model.addAttribute("event", event);
        return "events-edit";
    }

    @PostMapping("/events/edit/{id}")
    public String editEvent(@PathVariable("id") Long id,
                            @Valid @ModelAttribute("event") EventDto event,
                            BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "events-edit";
        }
        EventDto eventDto = eventService.findEventDtoById(id);
        event.setId(id);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/events/delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
}