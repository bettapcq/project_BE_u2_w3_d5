package bettapcq.projectu2w3d5.controllers;

import bettapcq.projectu2w3d5.entities.User;
import bettapcq.projectu2w3d5.exceptions.ValidationException;
import bettapcq.projectu2w3d5.payloads.LoginDTO;
import bettapcq.projectu2w3d5.payloads.LoginResponseDTO;
import bettapcq.projectu2w3d5.payloads.UsersDTO;
import bettapcq.projectu2w3d5.services.AuthService;
import bettapcq.projectu2w3d5.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UsersService usersService;

    public AuthController(AuthService authService, UsersService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }

    //Post user:
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Validated UsersDTO payload, BindingResult valRes) {

        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }

        return this.usersService.addUser(payload);
    }

    //login:
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO payload) {
        return new LoginResponseDTO(this.authService.checkLoginAndCreateToken(payload));
    }


}
