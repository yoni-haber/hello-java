package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class MainTest {

  private String runMainWithInput(String input) {
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

  /** Ensure we can start the FizzBuzz program */
  @Test
  public void testMain1() {
    String output = runMainWithInput("1\n5\n");
    assertTrue(output.contains("Running FizzBuzz..."));
    assertTrue(output.contains("Welcome to FizzBuzz!"));
  }

  /** Ensure we can start the mortgage calculator program */
  @Test
  public void testMortgageCalculatorOption() {
    String input = "2\n100000\n5.5\n30\n";
    String output = runMainWithInput(input);

    assertTrue(output.contains("Running Mortgage Calculator..."));
    assertTrue(output.contains("Welcome to the Mortgage Calculator!"));
  }

  /** Ensure we notify the user on an invalid option */
  @Test
  public void testInvalidOption() {
    String output = runMainWithInput("3\n");
    assertTrue(output.contains("Invalid choice. Please enter 1 or 2."));
  }
}
