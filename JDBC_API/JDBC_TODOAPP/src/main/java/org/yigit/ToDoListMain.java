package org.yigit;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ToDoListMain {
    public static void main(String[] args) {
        // Initialize the database (create table if not exists)
        TaskRepository.initializeDatabase();

        // Delete all tasks for a clean start (testing purposes)
        /*TaskRepository.deleteAll();*/

        // Add a sample task
        Task task = new Task("Sample task");
        TaskRepository.addTask(task);

        // Retrieve and print all tasks
        System.out.println(TaskService.displayAllTasks());

    }
}
