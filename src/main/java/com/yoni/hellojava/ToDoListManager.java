package com.yoni.hellojava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListManager {

  static List<Task> tasks = new ArrayList<>();

  public static void run(Scanner scanner) {
    System.out.println("Welcome to the To Do list program!");
    boolean running = true;

    while (running) {
      showMenu();
      int option = getValidOption(scanner);

      switch (option) {
        case 1 -> displayTasks();
        case 2 -> addTask(scanner);
        case 3 -> completeTask(scanner);
        case 4 -> deleteTask(scanner);
        case 5 -> running = false;
        default -> System.out.println("Invalid choice. Please choose a number between 1 and 5.");
      }
    }

    System.out.println("Thank you for using the To Do list program!");
  }

  private static void showMenu() {
    System.out.println("\n1. View tasks");
    System.out.println("2. Add task");
    System.out.println("3. Mark task as done");
    System.out.println("4. Delete task");
    System.out.println("5. Exit");
    System.out.print("Choose an option: ");
  }

  private static int getValidOption(Scanner scanner) {
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid input. Please enter a number between 1 and 5.");
      scanner.nextLine();
      showMenu();
    }
    int option = scanner.nextInt();
    scanner.nextLine();
    return option;
  }

  private static void addTask(Scanner scanner) {
    String desc;
    do {
      System.out.print("Enter a task description: ");
      desc = scanner.nextLine().trim();
      if (desc.isEmpty()) {
        System.out.println("Task description cannot be empty. Please try again.\n");
      }
    } while (desc.isEmpty());

    tasks.add(new Task(desc));
    System.out.println("'" + desc + "' added successfully to the list.\n");
  }

  private static void displayTasks() {
    if (tasks.isEmpty()) {
      System.out.println("Your to-do list is empty.\n");
      return;
    }
    System.out.println("Here are the items on your list: ");
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
  }

  private static void completeTask(Scanner scanner) {
    if (tasks.isEmpty()) {
      System.out.println("No tasks to complete.\n");
      return;
    }
    System.out.print("Enter task number to mark as done: ");
    int index = getValidIndex(scanner);
    if (index != -1) {
      tasks.get(index).markCompleted();
      System.out.println("Marked as done.\n");
    }
  }

  private static void deleteTask(Scanner scanner) {
    if (tasks.isEmpty()) {
      System.out.println("No tasks to delete.\n");
      return;
    }
    System.out.print("Enter task number to delete: ");
    int index = getValidIndex(scanner);
    if (index != -1) {
      Task removed = tasks.remove(index);
      System.out.println("Deleted: " + removed.getDescription() + "\n");
    }
  }

  private static int getValidIndex(Scanner scanner) {
    if (!scanner.hasNextInt()) {
      System.out.println("Invalid input. Must be a number.\n");
      scanner.nextLine();
      return -1;
    }
    int index = scanner.nextInt();
    scanner.nextLine();
    if (index < 1 || index > tasks.size()) {
      System.out.println("Invalid task number.\n");
      return -1;
    }
    return index - 1;
  }

  public static void main(String[] args) {
    run(new Scanner(System.in));
  }
}
