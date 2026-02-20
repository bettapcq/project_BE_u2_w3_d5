package bettapcq.projectu2w3d5.services;

import bettapcq.projectu2w3d5.entities.User;
import bettapcq.projectu2w3d5.exceptions.UnauthorizedException;
import bettapcq.projectu2w3d5.payloads.LoginDTO;
import bettapcq.projectu2w3d5.security.JWTTools;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersService usersService;
    private final JWTTools jwtTools;
    private PasswordEncoder passwordEncoder;

    public AuthService(UsersService usersService, JWTTools jwtTools, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.jwtTools = jwtTools;
        this.passwordEncoder = passwordEncoder;
    }

    public String checkLoginAndCreateToken(LoginDTO payload) {
        //check credenziali:

        User found = this.usersService.findByEmail(payload.email());
        if (passwordEncoder.matches(payload.password(), found.getPassword())) {

            //genero e ritorno token:
            String accessToken = jwtTools.createToken(found);
            return accessToken;

        } else {

            //errore se fallisce check credenziali:
            throw new UnauthorizedException("Invalid mail or password!");

        }
    }
}
