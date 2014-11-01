package no.borber.monsterShop.application;

public class CommandValidationException extends RuntimeException {
    public CommandValidationException(String message) {
        super(message);
    }
}
