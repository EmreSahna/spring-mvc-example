package com.emresahna.springmvcexample.controller;

import com.emresahna.springmvcexample.dto.EventDto;
import com.emresahna.springmvcexample.model.Event;
import com.emresahna.springmvcexample.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}