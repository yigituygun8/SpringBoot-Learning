package org.yigit;

public class ToDoListMain {
     public static void main(String[] args) {
        /*Initialize the database (create table if not exists)
        TaskRepository.initializeDatabase();*/

        // Delete all tasks for a clean start (testing purposes)
        /*TaskRepository.deleteAll();*/

        // Since I don't use Spring Boot, I need to run Flyway migrations manually at application startup
        TaskRepository.migrateDatabase();

        // Retrieve and print all tasks
        System.out.println(TaskService.displayAllTasks());

    }
}
