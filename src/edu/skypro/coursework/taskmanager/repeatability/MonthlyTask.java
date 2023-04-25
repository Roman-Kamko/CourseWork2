package edu.skypro.coursework.taskmanager.repeatability;

import edu.skypro.coursework.taskmanager.Task;
import edu.skypro.coursework.taskmanager.Type;

import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(Type type, String title, LocalDateTime dateTime, String description) {
        super(type, title, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDateTime dateTime) {
        return false;
    }
}
