package org.yigit;

public class ToDoListMain {
     public static void main(String[] args) {
        // Since I don't use Spring Boot, I need to run Flyway migrations manually at application startup
        TaskRepository.migrateDatabase();

        // User Interface Related Via Console
        TaskService.getInput();
    }
}
