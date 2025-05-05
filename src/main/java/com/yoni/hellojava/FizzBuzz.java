package com.yoni.hellojava;

import java.util.Scanner;

public class FizzBuzz {
  private static final int FIZZ = 3;
  private static final int BUZZ = 5;

  public static void run(Scanner scanner) {
    System.out.println("Welcome to FizzBuzz!");
    System.out.print("Please provide a number: ");
    int number = scanner.nextInt();

    if (number % FIZZ == 0 && number % BUZZ == 0) {
      System.out.println("FizzBuzz");
    } else if (number % FIZZ == 0) {
      System.out.println("Fizz");
    } else if (number % BUZZ == 0) {
      System.out.println("Buzz");
    } else {
      System.out.println(number);
    }
  }

  public static void main(String[] args) {
    run(new Scanner(System.in));
  }
}
