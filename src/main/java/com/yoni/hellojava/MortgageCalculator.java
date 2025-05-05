package com.yoni.hellojava;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MortgageCalculator {

  private static final int MONTHS_IN_YEAR = 12;
  private static final int PERCENT = 100;

  public static void run(Scanner scanner) {
    System.out.println("Welcome to the Mortgage Calculator!");
    int principal = readValidInt(scanner, "Enter principal amount: ");
    double interestRate = readValidDouble(scanner);
    int term = readValidInt(scanner, "Enter loan term (in years): ");

    double payment = calculateMonthlyPayment(principal, interestRate, term);
    System.out.println(
        "Your monthly mortgage payment is: " + NumberFormat.getCurrencyInstance().format(payment));
  }

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      run(scanner);
    }
  }

  private static int readValidInt(final Scanner scanner, final String prompt) {
    int value = 0;
    while (value <= 0) {
      System.out.print(prompt);
      try {
        value = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        if (value <= 0) {
          System.out.println("Value must be greater than zero. Please try again.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a whole number.");
        scanner.nextLine(); // clear invalid input
      }
    }
    return value;
  }

  private static double readValidDouble(Scanner scanner) {
    double value = 0;
    while (value <= 0) {
      System.out.print("Enter annual interest rate (percentage): ");
      try {
        value = scanner.nextDouble();
        scanner.nextLine();
        if (value <= 0) {
          System.out.println("Value must be greater than zero. Please try again.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a decimal number.");
        scanner.nextLine();
      }
    }
    return value;
  }

  private static double calculateMonthlyPayment(
      int principal, double annualInterestRate, int termYears) {
    double monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;
    int numberOfPayments = termYears * MONTHS_IN_YEAR;

    return principal
        * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
        / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
  }
}
