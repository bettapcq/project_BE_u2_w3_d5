package bettapcq.projectu2w3d5.repositories;

import bettapcq.projectu2w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public boolean existsByUsername(String username);
}
