package ru.messenger.domain;

public enum Role  {
    ROLE_ANONYMOUS,
    ROLE_USER,
    ROLE_ADMIN;

    public String getRole(){
        return name();
    }
}
