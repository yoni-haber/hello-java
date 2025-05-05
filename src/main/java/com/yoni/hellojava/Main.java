package com.yoni.hellojava;

import java.util.Scanner;

public class Main {

  /**
   * Main method to run the program.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Hello! Which program would you like to run?");
    System.out.println("1. FizzBuzz");
    System.out.println("2. Mortgage Calculator");
    System.out.print("Please enter the number of the program you want to run: ");
    int choice = scanner.nextInt();
    switch (choice) {
      case 1:
        System.out.println("Running FizzBuzz...");
        FizzBuzz.run(scanner);
        break;
      case 2:
        System.out.println("Running Mortgage Calculator...");
        MortgageCalculator.run(scanner);
        break;
      default:
        System.out.println("Invalid choice. Please enter 1 or 2.");
    }
  }
}
