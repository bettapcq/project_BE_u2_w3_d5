package bettapcq.projectu2w3d5.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewEventsDTO(@NotBlank(message = "Title is mandatory")
                           @Size(min = 2, max = 40, message = "Title can have min 2 and max 100 characters")
                           String title,
                           @NotNull(message = "Event must have a date")
                           @Future(message = "Trip date must be in the future")
                           LocalDate date,
                           @Size(max = 150, message = "Description can have max 150 characters")
                           String description,
                           @NotNull(message = "Event must have available seats number")
                           @Positive(message = "Number of available seats must be positive")
                           int availableSeats,
                           @NotBlank(message = "Location is mandatory")
                           @Size(min = 2, max = 40, message = "Location can have min 2 and max 50 characters")
                           String location) {
}
