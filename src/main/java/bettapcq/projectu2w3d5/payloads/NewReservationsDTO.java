package bettapcq.projectu2w3d5.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewReservationsDTO(@NotNull(message = "Reservation must have an associated event ")
                                 @Positive(message = "Event id must be a positive number")
                                 Long eventId) {
}
