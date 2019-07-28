package com.apside.prono.service;

import javax.transaction.Transactional;

import com.apside.prono.errors.InvalidEventDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apside.prono.model.Event;
import com.apside.prono.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public void createEvent(Event event) {

        if (event.getLabel() == null || event.getEventDate() == null || event.getOpenDate() == null
                || event.getCloseDate() == null || event.getCoeff() == 0 || event.getContest() == null) {
            throw new InvalidEventDataException();
        } else {
            eventRepository.save(event);
        }
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Transactional
    public Event modifyEvent(Event event) {
        Event eventModif = new Event();
        if (eventRepository.existsById(event.getId())) {
            eventModif = getEventById(event.getId());
        } else {
            throw new InvalidEventDataException();
        }
        if (event.getLabel() == null || event.getEventDate() == null || event.getCloseDate() == null
                || event.getCloseDate() == null || event.getCoeff() == 0 || event.getContest() == null) {
            throw new InvalidEventDataException();
        } else {
            eventModif.setId(event.getId());
            eventModif.setLabel(event.getLabel());
            eventModif.setEventDate(event.getEventDate());
            eventModif.setOpenDate(event.getOpenDate());
            eventModif.setCloseDate(event.getCloseDate());
            eventModif.setCoeff(event.getCoeff());
            eventModif.setContest(event.getContest());
        }

        return eventModif;
    }

    @Transactional
    public void deleteEvent(Long id) {

        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new InvalidEventDataException();
        }
    }

}
