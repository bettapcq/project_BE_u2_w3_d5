package bettapcq.projectu2w3d5.services;

import bettapcq.projectu2w3d5.security.JWTTools;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersService usersService;
    private final JWTTools jwtTools;

    public AuthService(UsersService usersService, JWTTools jwtTools) {
        this.usersService = usersService;
        this.jwtTools = jwtTools;
    }


}
