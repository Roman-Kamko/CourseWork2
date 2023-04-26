package edu.skypro.coursework.taskmanager.service;

import edu.skypro.coursework.taskmanager.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*      Поизучав класс Scanner, я пришел к выводы, что нужно использовать везде метод .nextLine()
   (не получится комбинировать с .nextInt()), связанно это с тем что Сканер делит ввод на токены
   и делимитеры, и при использовании nextInt() после парсинга сканер найдет и заберет int,
   а делимитер(все делимитеры которые будут в потоке) в виде переноса строки оставит и делимитер
   автоматически считается методом .nextLine() так же как и методом .next(), которые будут идти после .nextInt().
   Т.к. приложение получается интерактивным, лучше всего будет работать со строками, в противном случае общение
   с консолью будет багнутым с повторяющимися сообщениями(из-за рекурсии в методах) в тех случаях когда после
   .nextInt() следующим будет метод .nextLine().
        Пока что все реализовано через Integer.parseInt(), возможно лучше все переделать на строки*/

public class ConsoleService {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService = new TaskService();

    private int setRepeatabilityType() {

        while (true) {
            System.out.println("""
                    Укажите тип повторяемости задачи:
                    1 - однократная
                    2 - ежедневная
                    3 - еженедельная
                    4 - ежемесячная
                    5 - ежегодная""");

            int result = Integer.parseInt(scanner.nextLine());

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

            int typeTask = Integer.parseInt(scanner.nextLine());

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
            String result = scanner.nextLine();

            if (!result.isEmpty()) {
                return result;
            } else {
                System.out.println("Вы не указали заголовок");
                setTitle();
            }
        }
    }

    private LocalDateTime setDateTime() {

        LocalDate date = setDate();

        System.out.print("Введите часы: ");
        int setHours = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите минуты: ");
        int setMinutes = Integer.parseInt(scanner.nextLine());

        LocalTime time = LocalTime.of(setHours, setMinutes);

        return LocalDateTime.of(date, time);
    }

    private LocalDate setDate() {

        System.out.print("Введите год: ");
        int setYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите месяц: ");
        int setMonth = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите день: ");
        int setDate = Integer.parseInt(scanner.nextLine());

        return LocalDate.of(setYear, setMonth, setDate);
    }

    private String setDescription() {

        while (true) {
            System.out.print("Укажите описание задачи: ");
            String result = scanner.nextLine();

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
            int result = Integer.parseInt(scanner.nextLine());

            if (result > 0) {
                return result;
            }
            System.out.println("Вы указали отрицательное число или 0");
            setRepeatability();
        }
    }

    private void createTask() {

        switch (setRepeatabilityType()) {

            case 1 -> taskService.addOneTimeTask(
                    setTaskType(),
                    setTitle(),
                    setDateTime(),
                    setDescription());

            case 2 -> taskService.addDailyTask(
                    setTaskType(),
                    setTitle(),
                    setDateTime(),
                    setDescription(),
                    setRepeatability());

            case 3 -> taskService.addWeeklyTask(
                    setTaskType(),
                    setTitle(),
                    setDateTime(),
                    setDescription(),
                    setRepeatability());

            case 4 -> taskService.addMonthlyTask(
                    setTaskType(),
                    setTitle(),
                    setDateTime(),
                    setDescription(),
                    setRepeatability());

            case 5 -> taskService.addYearlyTask(
                    setTaskType(),
                    setTitle(),
                    setDateTime(),
                    setDescription(),
                    setRepeatability());

            default -> createTask();
        }
        System.out.println("Задача создана");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printTaskForDay() {
        System.out.println(taskService.printTasksForDay());
    }

    private void printTaskForAnotherDay() {
        System.out.println(taskService.printTasksForDay(setDate()));
    }

    private void removeTask() {

        System.out.print("Введите ID задачи, которую хотите удалить: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (id == 0) {
            start();
        }

        taskService.remove(id);
    }

    private void exit() {
        System.out.println("Завершение программы");
        System.exit(0);
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

            num = Integer.parseInt(scanner.nextLine());

            switch (num) {
                case 1 -> createTask();
                case 2 -> removeTask();
                case 3 -> printTaskForDay();
                case 4 -> printTaskForAnotherDay();
                case 0 -> exit();
            }
        }
    }
}
