package ru.messenger.database.model;

public enum UserField {
    USER_NAME("username"),
    USER_PASSWORD("username");


    UserField(String field) {
        this.field = field;
    }

    private final String field;

    public String getField() {
        return field;
    }

}
