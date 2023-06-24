package edu.skypro.coursework;

import edu.skypro.coursework.taskmanager.service.ConsoleService;

public class App {
    public static void main(String[] args) {
        ConsoleService consoleService = new ConsoleService();
        consoleService.start();
    }
}
