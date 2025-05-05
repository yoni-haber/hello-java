package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

  private String runWithInput(String input) {
    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;

    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    System.setIn(in);
    System.setOut(new PrintStream(out));

    try {
      FizzBuzz.main(new String[] {});
      return out.toString();
    } finally {
      System.setIn(originalIn);
      System.setOut(originalOut);
    }
  }

  /** Ensure we get "FizzBuzz" if the value is divisible by 3 and 5 */
  @Test
  public void testFizzBuzz1() {
    String output = runWithInput("15\n");
    assertTrue(output.contains("FizzBuzz"));
  }

  /** Ensure we get "Fizz" if the number is divisible by 3 */
  @Test
  public void testFizzBuzz2() {
    String output = runWithInput("27\n");
    assertTrue(output.contains("Fizz"));
  }

  /** Ensure we get "Buzz" if the number is divisible by 5 */
  @Test
  public void testFizzBuzz3() {
    String output = runWithInput("20\n");
    assertTrue(output.contains("Buzz"));
  }

  /** Ensure the number is returned if it is not divisible by 3 or 5 */
  @Test
  public void testFizzBuzz4() {
    String output = runWithInput("14\n");
    assertTrue(output.contains("14"));
  }

  /** Ensure the user is prompted as expected */
  @Test
  public void testFizzBuzz5() {
    String output = runWithInput("0\n");
    assertTrue(output.contains("Welcome to FizzBuzz!"));
    assertTrue(output.contains("Please provide a number:"));
  }
}
