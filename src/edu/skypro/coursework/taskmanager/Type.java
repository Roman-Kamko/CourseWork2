package edu.skypro.coursework.taskmanager;

public enum Type {
    PERSONAL("Личная"),
    WORK("Работа");
    private final String text;

    Type(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
