package bettapcq.projectu2w3d5.controllers;


import bettapcq.projectu2w3d5.entities.Reservation;
import bettapcq.projectu2w3d5.entities.User;
import bettapcq.projectu2w3d5.exceptions.ValidationException;
import bettapcq.projectu2w3d5.payloads.NewReservationsDTO;
import bettapcq.projectu2w3d5.services.ReservationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private final ReservationService reservationService;

    public ReservationsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    //post:
    @PostMapping
    public Reservation addReservation(@AuthenticationPrincipal User currentAuthUser, @RequestBody @Validated NewReservationsDTO payload, BindingResult valRes) {
        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }
        return this.reservationService.addReservation(currentAuthUser.getUserId(), payload);
    }


}
