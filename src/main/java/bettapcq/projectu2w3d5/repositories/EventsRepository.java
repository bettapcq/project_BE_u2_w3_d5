package bettapcq.projectu2w3d5.repositories;

import bettapcq.projectu2w3d5.entities.Event;
import bettapcq.projectu2w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventsRepository extends JpaRepository<Event, Long> {
    public boolean existsByLocationAndDateAndCreator(String location, LocalDate date, User creator);

}
