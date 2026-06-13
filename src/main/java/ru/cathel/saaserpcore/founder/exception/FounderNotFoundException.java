package ru.cathel.saaserpcore.founder.exception;

public class FounderNotFoundException extends RuntimeException {
    public FounderNotFoundException() {
        super("Founder not found");
    }
}
