package bettapcq.projectu2w3d5.controllers;

import bettapcq.projectu2w3d5.entities.Event;
import bettapcq.projectu2w3d5.entities.User;
import bettapcq.projectu2w3d5.exceptions.ValidationException;
import bettapcq.projectu2w3d5.payloads.NewEventsDTO;
import bettapcq.projectu2w3d5.services.EventsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    //POST
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CREATOR')")
    public Event addEvent(@AuthenticationPrincipal User currentAuthUser, @RequestBody @Validated NewEventsDTO payload, BindingResult valRes) {
        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }

        return this.eventsService.addEvent(currentAuthUser.getUserId(), payload);
    }
}
