package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class MainTest {

  private String runWithInput(String input) {
    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;

    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    System.setIn(in);
    System.setOut(new PrintStream(out));

    try {
      Main.main(new String[] {});
      return out.toString();
    } finally {
      System.setIn(originalIn);
      System.setOut(originalOut);
    }
  }

  /** Ensure the user is presented with all the Program options */
  @Test
  public void testMain1() {
    String output = runWithInput("1\n5\n"); // choose FizzBuzz to terminate the loop
    assertTrue(output.contains("Hello! Which program would you like to run?"));
    assertTrue(output.contains("1. FizzBuzz"));
    assertTrue(output.contains("2. Mortgage Calculator"));
    assertTrue(output.contains("3. Bank Account Manager"));
    assertTrue(output.contains("4. To Do List"));
    assertTrue(output.contains("Please enter the number of the program you want to run:"));
  }

  /** Ensure we can select the FizzBuzz program */
  @Test
  public void testMain2() {
    String output = runWithInput("1\n5\n");
    assertTrue(output.contains("Running FizzBuzz..."));
    assertTrue(output.contains("Welcome to FizzBuzz!"));
  }

  /** Ensure we can select the mortgage calculator program */
  @Test
  public void testMain3() {
    String output = runWithInput("2\n100000\n5.5\n30\n");
    assertTrue(output.contains("Running Mortgage Calculator..."));
    assertTrue(output.contains("Welcome to the Mortgage Calculator!"));
  }

  /** Ensure we can select the bank account manager program */
  @Test
  public void testMain4() {
    String output = runWithInput("3\n1\n300\nn\n");
    assertTrue(output.contains("Running Bank Account Manager..."));
    assertTrue(output.contains("Welcome to the Bank Account Manager!"));
  }

  /** Ensure we notify the user on an invalid option and give them another chance */
  @Test
  public void testMain5() {
    String output = runWithInput("9\n1\n5\n");
    assertTrue(output.contains("Invalid choice. Please enter 1, 2, 3 or 4."));
    assertTrue(output.contains("Running FizzBuzz..."));
    assertTrue(output.contains("Welcome to FizzBuzz!"));
  }

  /** Ensure we handle invalid input for the program choice */
  @Test
  public void testMain6() {
    String output = runWithInput("abc\n1\n5\n");
    assertTrue(output.contains("Invalid input. Please enter a number."));
    assertTrue(output.contains("Running FizzBuzz..."));
    assertTrue(output.contains("Welcome to FizzBuzz!"));
  }

  /** Ensure we can select the to do list manager program */
  @Test
  public void testMain7() {
    String output = runWithInput("4\n5\n1\n5\n");
    assertTrue(output.contains("Running To Do List..."));
    assertTrue(output.contains("Welcome to the To Do list program!"));
  }
}
