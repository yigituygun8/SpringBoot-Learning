package org.yigit;

import java.util.List;
import java.util.Scanner;

// For Business Logic related to tasks
public class TaskService {

    public static void addNewTask(String taskName, String status) {
        if(taskName == null || taskName.trim().isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }
        Task task = new Task(taskName.trim(), status.trim());
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
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            do {
                System.out.println("MENU:\n1. Add Task\n2. Display All Tasks\n3. Get Task By ID\n4. Update Task By ID\n5. Delete Task By ID\n6. Exit\n-------------------------------\n");
                System.out.println("Enter your choice (1-6): ");
                choice = sc.nextInt();
                if(choice < 1 || choice > 6) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.\n");
                }
                } while(choice < 1 || choice > 6);
                if(choice != 6) {
                    sc.nextLine(); // consume newline
                    switch(choice) {
                        case 1:
                            System.out.println("Enter task name: ");
                            String name = sc.nextLine();
                            System.out.println("Enter task status: ");
                            String status = sc.nextLine();
                            addNewTask(name, status);
                            System.out.println("Task added successfully.\n");
                            break;
                        case 2:
                            System.out.println("All Tasks:");
                            System.out.println(displayAllTasks());
                            break;
                        case 3:
                            System.out.println("Enter task ID to retrieve: ");
                            int getId = sc.nextInt();
                            Task task = TaskRepository.getTaskById(getId);
                            if(task != null) {
                                System.out.println("Task found: " + task + "\n");
                            } else {
                                System.out.println("Task with ID " + getId + " not found.\n");
                            }
                            break;
                        case 4:
                            System.out.println("Enter task ID to update: ");
                            int updateId = sc.nextInt();
                            sc.nextLine(); // consume newline
                            Task existingTask = TaskRepository.getTaskById(updateId);
                            if (existingTask != null) {
                                System.out.println("Enter new task name: ");
                                String newName = sc.nextLine();
                                System.out.println("Enter new task status: ");
                                String newStatus = sc.nextLine();
                                TaskRepository.updateTask(updateId, newName, newStatus);
                                System.out.println("Task updated successfully.\n");
                            } else {
                                System.out.println("Task with ID " + updateId + " not found.\n");
                            }
                            break;
                        case 5:
                            System.out.println("Enter task ID to delete: ");
                            int deleteId = sc.nextInt();
                            try {
                                TaskRepository.deleteTask(deleteId);
                                System.out.println("Task deleted successfully.\n");
                            } catch (Exception e) {
                                System.out.println("Failed to delete task. Please try again. " + e.getMessage() + "\n");
                            }
                            break;
                    }
                }
        } while(choice != 6);
        System.out.println("Exited the program.");
    }
}
