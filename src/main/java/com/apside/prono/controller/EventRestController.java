package com.apside.prono.controller;

import com.apside.prono.errors.InvalidEventDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apside.prono.model.Event;
import com.apside.prono.service.EventService;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URI;

@CrossOrigin
@RestController
public class EventRestController {

    @Autowired
    private EventService eventService;

    @GetMapping(produces = "application/json", path = "/event")
    public Iterable<Event> getAllEvents() {

        return eventService.getAllEvents();
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event, UriComponentsBuilder uriBuilder) {

        if (event == null) {
            throw new InvalidEventDataException();
        } else {
            eventService.createEvent(event);
            URI location = uriBuilder.path("/event/{id}").buildAndExpand(event.getId()).toUri();
            return ResponseEntity.created(location).body(event);
        }
    }

    @GetMapping(produces = "application/json", path = "/event/{id}")
    public Event getEventById(@PathVariable Long id) {

        return eventService.getEventById(id);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/event")
    public Event modifyEvent(@RequestBody Event event) {
        if (event == null) {
            throw new InvalidEventDataException();
        } else {
            return eventService.modifyEvent(event);
        }
    }

    @DeleteMapping(path = "/event/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
