package edu.skypro.coursework.taskmanager.repeatability;

import edu.skypro.coursework.taskmanager.Task;
import edu.skypro.coursework.taskmanager.Type;

import java.time.LocalDateTime;
import java.util.List;

public class DailyTask extends Task {
    public DailyTask(Type type, String title, LocalDateTime dateTime, String description) {
        super(type, title, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDateTime dateTime) {
        return false;
    }
}
