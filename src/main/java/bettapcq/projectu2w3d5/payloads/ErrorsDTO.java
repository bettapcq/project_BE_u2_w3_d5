package bettapcq.projectu2w3d5.payloads;


import java.time.LocalDateTime;

public record ErrorsDTO(String message,
                        LocalDateTime timestamp) {
}