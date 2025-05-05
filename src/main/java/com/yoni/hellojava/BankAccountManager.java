package com.yoni.hellojava;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class BankAccountManager {

  public static void run(Scanner scanner) {
    BankAccount account = new BankAccount(1000);

    System.out.println("Welcome to the Bank Account Manager!");
    while (true) {
      showMenu();
      int choice = readInt(scanner, "Please enter the number of the service you require: ");

      switch (choice) {
        case 1 -> handleDeposit(scanner, account);
        case 2 -> handleWithdrawal(scanner, account);
        case 3 -> handleBalance(account);
        default -> System.out.println("Invalid choice. Please enter 1, 2 or 3.");
      }

      System.out.print("Do you require another service? (Y/n) ");
      String another = scanner.nextLine().trim();
      if (!another.equalsIgnoreCase("Y")) {
        System.out.println("Thank you for using the Bank Account Manager!");
        break;
      }
    }
  }

  private static void showMenu() {
    System.out.println("\nThese are the services we offer:");
    System.out.println("1. Deposit");
    System.out.println("2. Withdraw");
    System.out.println("3. Check Balance");
  }

  private static void handleDeposit(Scanner scanner, BankAccount account) {
    int amount = readInt(scanner, "How much would you like to deposit? ");
    try {
      account.deposit(amount);
      System.out.println(
          "Deposit successful. Your balance is now: " + formatCurrency(account.getBalance()));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void handleWithdrawal(Scanner scanner, BankAccount account) {
    int amount = readInt(scanner, "How much would you like to withdraw? ");
    try {
      account.withdraw(amount);
      System.out.println(
          "Withdrawal successful. Your balance is now: " + formatCurrency(account.getBalance()));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void handleBalance(BankAccount account) {
    System.out.println("Your balance is: " + formatCurrency(account.getBalance()));
  }

  private static int readInt(Scanner scanner, String prompt) {
    while (true) {
      System.out.print(prompt);
      if (scanner.hasNextInt()) {
        int val = scanner.nextInt();
        scanner.nextLine(); // clear newline
        return val;
      } else {
        System.out.println("Invalid input. Please enter a whole number.");
        scanner.nextLine(); // discard invalid input
      }
    }
  }

  private static String formatCurrency(int amount) {
    return NumberFormat.getCurrencyInstance(Locale.UK).format(amount);
  }

  public static void main(String[] args) {
    run(new Scanner(System.in));
  }
}
