package bettapcq.projectu2w3d5.services;

import bettapcq.projectu2w3d5.entities.Role;
import bettapcq.projectu2w3d5.entities.User;
import bettapcq.projectu2w3d5.exceptions.BadRequestException;
import bettapcq.projectu2w3d5.exceptions.NotFoundException;
import bettapcq.projectu2w3d5.payloads.UsersDTO;
import bettapcq.projectu2w3d5.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(UsersDTO payload) {

        //verifico che email non esista già:
        User found = this.usersRepository.findByEmail(payload.email());
        if (found != null) throw new BadRequestException("Questa mail è già registrata");

        //verifico che username sia unico:
        boolean usernameExists = this.usersRepository.existsByUsername(payload.username());
        if (usernameExists) throw new BadRequestException("L'username " + payload.username() + " è già in uso");

        //creo
        User newUser = new User(payload.username(), payload.email(), Role.valueOf(payload.role()), passwordEncoder.encode(payload.password()));

        //salvo
        return this.usersRepository.save(newUser);
    }


    public User findById(Long userId) {
        return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return this.usersRepository.findByEmail(email);
    }
}
