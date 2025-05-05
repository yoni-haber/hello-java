package com.yoni.hellojava;

import java.util.Scanner;

public class FizzBuzz {

  private static final int FIZZ = 3;
  private static final int BUZZ = 5;

  /**
   * Main method to run the FizzBuzz program.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
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
}
