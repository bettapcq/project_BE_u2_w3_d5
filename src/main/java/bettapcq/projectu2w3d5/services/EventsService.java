package bettapcq.projectu2w3d5.services;

import bettapcq.projectu2w3d5.entities.Event;
import bettapcq.projectu2w3d5.entities.User;
import bettapcq.projectu2w3d5.exceptions.BadRequestException;
import bettapcq.projectu2w3d5.payloads.NewEventsDTO;
import bettapcq.projectu2w3d5.repositories.EventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventsService {

    private final UsersService usersService;
    private final EventsRepository eventsRepository;

    public EventsService(UsersService usersService, EventsRepository eventsRepository) {
        this.usersService = usersService;
        this.eventsRepository = eventsRepository;
    }

    public Event addEvent(Long pathUserId, NewEventsDTO payload) {
        //recupero creator:
        User userFound = this.usersService.findById(pathUserId);


        //controllo se il creator ha già creato un evento nella stessa data e luogo:

        if (eventsRepository.existsByLocationAndDateAndCreator(payload.location(), payload.date(), userFound))
            throw new BadRequestException("You already created an event on this date in this location!");

        Event newEvent = new Event(payload.date(), payload.title(), payload.description(), payload.availableSeats(), payload.location(), userFound);
        Event newEventSaved = this.eventsRepository.save(newEvent);

        log.info("New event with id " + newEventSaved.getEventId() + " has been created");

        return newEventSaved;
    }

}
