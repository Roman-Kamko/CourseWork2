package edu.skypro.coursework.taskmanager.service;

import edu.skypro.coursework.taskmanager.Type;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleService {
    Scanner scanner = new Scanner(System.in);
    private TaskService taskService = new TaskService();

    private int setRepeatabilityType() {

        while (true) {
            System.out.println("""
                    Укажите тип повторяемости задачи:
                    1 - однократная
                    2 - ежедневная
                    3 - еженедельная
                    4 - ежемесячная
                    5 - ежегодная""");
            int result = scanner.nextInt();
            if (result > 0 && result < 6) {
                return result;
            } else {
                System.out.println("Указанно не верное значение");
                System.out.println();
                setRepeatabilityType();
            }
        }
    }

    private Type setTaskType() {

        while (true) {
            System.out.println("""
                    Укажите тип задачи:
                    1 - Личная
                    2 - Работа""");
            int typeTask = scanner.nextInt();
            if (typeTask == 1) {
                return Type.PERSONAL;
            } else if (typeTask == 2) {
                return Type.WORK;
            } else {
                System.out.println("Указанно не верное значение");
                System.out.println();
                setTaskType();
            }
        }
    }

    private String setTitle() {
        while (true) {
            System.out.print("Укажите заголовок для задачи: ");
            String result = scanner.next();
            if (!result.isEmpty()) {
                return result;
            }
            else {
                System.out.println("Вы не указали заголовок");
                setTitle();
            }
        }
    }

    private LocalDateTime setDateTime() {          //todo переделать

        System.out.println("Введите дату и время в формате: гггг мм дд, часы(0-23), минуты(0-59)");
        return LocalDateTime.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
    }

    private String setDescription() {

        while (true) {
        System.out.print("Укажите описание задачи: ");
        String result = scanner.next();
            if (!result.isEmpty()) {
                return result;
            }
            System.out.println("Вы не указали описание");
            setDescription();
        }
    }

    private int setRepeatability() {

        while (true) {
            System.out.print("Укажите количество повторений:");
            int result = scanner.nextInt();
            if (result > 0) {
                return result;
            }
            System.out.println("Вы указали отрицательное число или 0");
            setRepeatability();
        }
    }

    public void createTask() {

        switch (setRepeatabilityType()) {
            case 1 -> taskService.addOneTimeTask(setTaskType(), setTitle(), setDateTime(), setDescription());
            case 2 -> taskService.addDailyTask(setTaskType(), setTitle(), setDateTime(), setDescription(), setRepeatability());
            case 3 -> taskService.addWeeklyTask(setTaskType(), setTitle(), setDateTime(), setDescription(), setRepeatability());
            case 4 -> taskService.addMonthlyTask(setTaskType(), setTitle(), setDateTime(), setDescription(), setRepeatability());
            case 5 -> taskService.addYearlyTask(setTaskType(), setTitle(), setDateTime(), setDescription(), setRepeatability());
            default -> System.out.println("Не верное значение");
        }
        System.out.println("Задача создана");
    }

    public void start() {
        int num = -1;
        while (num != 0) {
            System.out.println("""
                    Выберите действие:
                    1 - Создать задачу
                    2 - Удалить задачу
                    3 - Список дел на сегодня
                    4 - Список дел на указанную дату
                    0 - Выход""");
            num = scanner.nextInt();
            switch (num) {
                case 1 -> createTask();
//                case 2 -> removeTask();
//                case 3 -> printTaskForDay();
//                case 4 -> printTaskForDay(LocalDateTime dateTime);
                case 0 -> scanner.close();
            }
        }
    }
}
