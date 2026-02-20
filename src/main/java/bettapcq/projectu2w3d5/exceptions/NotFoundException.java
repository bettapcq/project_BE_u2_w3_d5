package bettapcq.projectu2w3d5.exceptions;


public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Record con id " + id + " non trovato");
    }

    public NotFoundException(String message) {
        super(message);
    }
}