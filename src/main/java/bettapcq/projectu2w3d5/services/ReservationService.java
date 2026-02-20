package bettapcq.projectu2w3d5.services;

import bettapcq.projectu2w3d5.entities.Event;
import bettapcq.projectu2w3d5.entities.Reservation;
import bettapcq.projectu2w3d5.entities.User;
import bettapcq.projectu2w3d5.exceptions.BadRequestException;
import bettapcq.projectu2w3d5.payloads.NewReservationsDTO;
import bettapcq.projectu2w3d5.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationService {

    private final UsersService usersService;
    private final EventsService eventsService;
    private final ReservationRepository reservationRepository;


    public ReservationService(UsersService usersService, EventsService eventsService, ReservationRepository reservationRepository) {
        this.usersService = usersService;
        this.eventsService = eventsService;
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Long currentUserId, NewReservationsDTO payload) {
        //recupero user e event
        User userFound = this.usersService.findById(currentUserId);
        Event eventFound = this.eventsService.findById(payload.eventId());

        //check non altro evento nella stessa data:
        if (reservationRepository.existsByUserAndEvent_Date(userFound, eventFound.getDate()))
            throw new BadRequestException("You already have a reservation on this date");

        Reservation newReservation = new Reservation(userFound, eventFound);

        log.info("New reservation with id " + newReservation.getReservationId() + " has been saved");

        return reservationRepository.save(newReservation);
    }

}
