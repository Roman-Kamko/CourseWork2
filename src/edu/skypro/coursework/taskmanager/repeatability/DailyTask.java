package edu.skypro.coursework.taskmanager.repeatability;

import edu.skypro.coursework.taskmanager.Task;
import edu.skypro.coursework.taskmanager.Type;

import java.time.LocalDate;

public class DailyTask extends Task{
    public DailyTask(Type type, String title, LocalDate dateTime, String description) {
        super(type, title, dateTime, description);
    }
}
