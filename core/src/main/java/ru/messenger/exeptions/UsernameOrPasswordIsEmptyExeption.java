package ru.messenger.exeptions;

public class UsernameOrPasswordIsEmptyExeption extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameOrPasswordIsEmptyExeption(String message) {
        super(message);
    }
}