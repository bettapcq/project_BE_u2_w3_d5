package bettapcq.projectu2w3d5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long eventId;

    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(name = "available_seats", nullable = false)
    private int availableSeats;
    @Column(nullable = false)
    private String location;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    public Event(LocalDate date, String title, String description, int availableSeats, String location, User creator) {
        this.date = date;
        this.title = title;
        this.description = description == null ? "description not available" : description;
        this.availableSeats = availableSeats;
        this.location = location;
        this.creator = creator;
    }
}
