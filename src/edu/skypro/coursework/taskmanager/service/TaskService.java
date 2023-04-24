package edu.skypro.coursework.taskmanager.service;

import edu.skypro.coursework.taskmanager.Task;
import edu.skypro.coursework.taskmanager.Type;
import edu.skypro.coursework.taskmanager.repeatability.DailyTask;
import edu.skypro.coursework.taskmanager.repeatability.OneTimeTask;

import java.time.LocalDate;
import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap;
    private final Collection<Task> removedTasks;

    public TaskService() {
        this.taskMap = new HashMap<>();
        this.removedTasks = new ArrayList<>();
    }

    public String addOneTimeTask(Type type, String title, LocalDate dateTime, String description) {
        Task task = new OneTimeTask(type, title, dateTime, description);
        taskMap.put(task.getId(), task);
        return "Задача: " + task.getTitle() + " успешно добавлена";
    }
    public String addDailyTask(Type type, String title, LocalDate dateTime, String description) {
        while (dateTime.getMonth() == LocalDate.now().getMonth()) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusDays(1);
        }
        return "Задача успешно добавлена";
    }

    public Collection<Task> printTasksForDay(LocalDate date) {
        // задачи на конкретный день
        return taskMap.values().stream()
                .filter(task -> task.getDateTime().equals(date))
                .toList();
    }

    public Collection<Task> printTasksForDay() {
        // задачи на текущий день
        return taskMap.values().stream()
                .filter(task -> task.getDateTime().equals(LocalDate.now()))
                .toList();
    }

    public String remove(int id) {
        removedTasks.add(taskMap.get(id));
        taskMap.remove(id);
        return "Задача успешно удалена";
    }
}
