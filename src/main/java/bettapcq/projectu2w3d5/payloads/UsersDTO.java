package bettapcq.projectu2w3d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsersDTO(@NotBlank(message = "Username is mandatory")
                       @Size(min = 2, max = 40, message = "Username can have min 2 and max 40 characters")
                       String username,
                       @NotBlank(message = "User must have an email")
                       @Email(message = "Not valid email format")
                       String email,
                       @NotBlank(message = "A password is required")
                       @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$", message = "Insert a valid password:\n" + "Length ≥ 4\n" +
                               "Contains at least 1 digit\n" +
                               "Contains at least 1 lowercase letter\n" +
                               "Contains at least 1 uppercase letter ")
                       String password,
                       @NotBlank(message = "User must have a role")
                       @Pattern(
                               regexp = "BASE|CREATOR",
                               message = "Status must be BASE or CREATOR")
                       String role) {
}
