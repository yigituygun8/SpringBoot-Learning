package org.yigit;

import java.util.List;

// For Business Logic related to tasks
public class TaskService {

    public static void addNewTask(String taskName) {
        if(taskName == null || taskName.trim().isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }
        Task task = new Task(taskName.trim());
        TaskRepository.addTask(task); // call from DB layer
    }

    public static String displayAllTasks() {
        StringBuilder sb = new StringBuilder();
        List<Task> tasks = TaskRepository.getAllTasks();
        for(Task task : tasks) {
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }

    // User Interface Related Via Console
    public static void getInput() {

    }


}
