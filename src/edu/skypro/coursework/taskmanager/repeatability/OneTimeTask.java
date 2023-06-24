package edu.skypro.coursework.taskmanager.repeatability;

import edu.skypro.coursework.taskmanager.Task;
import edu.skypro.coursework.taskmanager.Type;

import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask(Type type, String title, LocalDateTime dateTime, String description) {
        super(type, title, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDateTime dateTime) {
        return false;
    }
}
