package edu.skypro.coursework.taskmanager.service;

import edu.skypro.coursework.taskmanager.Task;
import edu.skypro.coursework.taskmanager.Type;
import edu.skypro.coursework.taskmanager.repeatability.DailyTask;
import edu.skypro.coursework.taskmanager.repeatability.OneTimeTask;

import java.time.LocalDateTime;
import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap;
    private final Collection<Task> removedTasks;

    public TaskService() {
        this.taskMap = new TreeMap<>();
        this.removedTasks = new ArrayList<>();
    }

    public String addOneTimeTask(Type type, String title, LocalDateTime dateTime, String description) {
        Task task = new OneTimeTask(type, title, dateTime, description);
        taskMap.put(task.getId(), task);
        return "Задача успешно добавлена";
    }
    public String addDailyTask(Type type, String title, LocalDateTime dateTime, String description, int repeatability) {
        for (int i = 0; i <= repeatability; i++) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusDays(1);
        }
        return "Задача успешно добавлена";
    }
    public String addWeeklyTask(Type type, String title, LocalDateTime dateTime, String description, int repeatability) {
        for (int i = 0; i <= repeatability; i++) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusWeeks(1);
        }
        return "Задача успешно добавлена";
    }

    public String addMonthlyTask(Type type, String title, LocalDateTime dateTime, String description, int repeatability) {
        for (int i = 0; i < repeatability; i++) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusMonths(1);
        }
        return "Задача успешно добавлена";
    }
    public String addYearlyTask(Type type, String title, LocalDateTime dateTime, String description, int repeatability) {
        for (int i = 0; i < repeatability; i++) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusYears(1);
        }
        return "Задача успешно добавлена";
    }

    public Collection<Task> printTasksForDay(LocalDateTime date) {
        // задачи на конкретный день
        return taskMap.values().stream()
                .filter(task -> task.getDateTime().equals(date))
                .sorted(Comparator.comparing(Task::getDateTime))
                .toList();
    }

    public Collection<Task> printTasksForDay() {
        // задачи на текущий день
        return taskMap.values().stream()
                .filter(task -> task.getDateTime().equals(LocalDateTime.now()))
                .sorted(Comparator.comparing(Task::getDateTime))
                .toList();
    }

    public String remove(int id) {
        removedTasks.add(taskMap.get(id));
        taskMap.remove(id);
        return "Задача успешно удалена";
    }
}
