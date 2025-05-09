package com.yoni.hellojava;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        System.out.println("Hello! Which program would you like to run?");
        System.out.println("1. FizzBuzz");
        System.out.println("2. Mortgage Calculator");
        System.out.println("3. Bank Account Manager");
        System.out.println("4. To Do List");
        System.out.print("Please enter the number of the program you want to run: ");

        if (!scanner.hasNextInt()) {
          System.out.println("Invalid input. Please enter a number.");
          scanner.nextLine(); // clear invalid input
          continue;
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
          case 1:
            System.out.println("Running FizzBuzz...");
            FizzBuzz.run(scanner);
            return;
          case 2:
            System.out.println("Running Mortgage Calculator...");
            MortgageCalculator.run(scanner);
            return;
          case 3:
            System.out.println("Running Bank Account Manager...");
            BankAccountManager.run(scanner);
            return;
          case 4:
            System.out.println("Running To Do List...");
            ToDoListManager.run(scanner);
            break;
          default:
            System.out.println("Invalid choice. Please enter 1, 2, 3 or 4.");
        }
      }
    }
  }
}
