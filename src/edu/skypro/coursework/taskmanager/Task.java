package edu.skypro.coursework.taskmanager;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator = 1;
    private final int id;
    private Type type;
    private String title;
    private LocalDateTime dateTime;
    private String description;
//    Date dateOfCreation;

    public Task(Type type,
                String title,
                LocalDateTime dateTime,
                String description) {
        this.type = type;
        this.title = title;
        this.dateTime = dateTime;
        this.description = description;
//        Date dateOfCreation = new Date();
        id = idGenerator++;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract boolean appearsIn(LocalDateTime dateTime); // пока не понял что с этим делать

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                type == task.type &&
                Objects.equals(title, task.title) &&
                Objects.equals(dateTime, task.dateTime) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, title, dateTime, description);
    }

    @Override
    public String toString() {
        return String.format("ID %d, " +
                        "Тип %s, " +
                        "Заголовок: %s, " +
                        "Дата выполнения: %te %<tB %<tY, " +
                        "Описание: %s%n",
                id, type.getText(), title, dateTime, description);
    }
}
