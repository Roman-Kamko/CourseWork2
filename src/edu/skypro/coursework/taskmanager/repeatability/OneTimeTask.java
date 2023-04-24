package edu.skypro.coursework.taskmanager.repeatability;

import edu.skypro.coursework.taskmanager.Task;
import edu.skypro.coursework.taskmanager.Type;

import java.time.LocalDate;

public class OneTimeTask extends Task {
    public OneTimeTask(Type type, String title, LocalDate dateTime, String description) {
        super(type, title, dateTime, description);
    }
}
