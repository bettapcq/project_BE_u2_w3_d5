package bettapcq.projectu2w3d5.repositories;

import bettapcq.projectu2w3d5.entities.Reservation;
import bettapcq.projectu2w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUserAndEvent_Date(User user, LocalDate eventDate);
}
