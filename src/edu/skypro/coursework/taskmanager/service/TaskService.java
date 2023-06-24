package edu.skypro.coursework.taskmanager.service;

import edu.skypro.coursework.taskmanager.Task;
import edu.skypro.coursework.taskmanager.Type;
import edu.skypro.coursework.taskmanager.repeatability.DailyTask;
import edu.skypro.coursework.taskmanager.repeatability.OneTimeTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/* Я не понял как изящно реализовать повторяемость задачи,
   поэтому использую дибильный способ и прошу пользователя указать число повторений */

public class TaskService {
    private final Map<Integer, Task> taskMap;
    private final Collection<Task> removedTasks;

    public TaskService() {
        this.taskMap = new HashMap<>();
        this.removedTasks = new ArrayList<>();
    }

    public void addOneTimeTask(Type type, String title, LocalDateTime dateTime, String description) {
        OneTimeTask task = new OneTimeTask(type, title, dateTime, description);
        taskMap.put(task.getId(), task);
    }

    public void addDailyTask(Type type, String title, LocalDateTime dateTime, String description, int repeatability) {
        for (int i = 0; i <= repeatability; i++) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusDays(1);
        }
    }

    public void addWeeklyTask(Type type, String title, LocalDateTime dateTime, String description, int repeatability) {
        for (int i = 0; i <= repeatability; i++) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusWeeks(1);
        }
    }

    public void addMonthlyTask(Type type, String title, LocalDateTime dateTime, String description, int repeatability) {
        for (int i = 0; i < repeatability; i++) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusMonths(1);
        }
    }

    public void addYearlyTask(Type type, String title, LocalDateTime dateTime, String description, int repeatability) {
        for (int i = 0; i < repeatability; i++) {
            Task task = new DailyTask(type, title, dateTime, description);
            taskMap.put(task.getId(), task);
            dateTime = dateTime.plusYears(1);
        }
    }

    public Collection<Task> printTasksForDay(LocalDate date) {
        // задачи на конкретный день
        return taskMap.values()
                .stream()
                .filter(task -> task.getDateTime().getDayOfMonth() == (date.getDayOfMonth()))
                .sorted(Comparator.comparing(Task::getDateTime))
                .toList();
    }

    public Collection<Task> printTasksForDay() {
        // задачи на текущий день
        return taskMap.values()
                .stream()
                .filter(task -> task.getDateTime().getDayOfMonth() == (LocalDateTime.now().getDayOfMonth()))
                .sorted(Comparator.comparing(Task::getDateTime))
                .toList();
    }

    public void remove(int id) {
        removedTasks.add(taskMap.get(id));
        taskMap.remove(id);
    }

//    public static void main(String[] args) {
//        TaskService taskService = new TaskService();
//        taskService.addOneTimeTask(Type.PERSONAL, "qq", LocalDateTime.of(2023, 4, 26, 9, 30), "qqqq");
//        taskService.addOneTimeTask(Type.PERSONAL, "ww", LocalDateTime.of(2023, 4, 26, 9, 0), "wwww");
//        taskService.addOneTimeTask(Type.PERSONAL, "ee", LocalDateTime.of(2023, 4, 27, 9, 30), "eeee");
//        taskService.addOneTimeTask(Type.PERSONAL, "rr", LocalDateTime.of(2023, 4, 27, 9, 0), "rrrr");
//        System.out.println(taskService.printTasksForDay());
//        System.out.println(taskService.printTasksForDay(LocalDate.of(2023, 4, 27)));
//    }
}
