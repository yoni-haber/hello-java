package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

  private static String runMainWithInput(String input) {
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    FizzBuzz.main(new String[] {});
    return out.toString();
  }

  /** Ensure we get "FizzBuzz" if the value is divisible by 3 and 5 */
  @Test
  public void testFizzBuzz1() {
    String output = runMainWithInput("15\n");
    assertTrue(output.contains("FizzBuzz"));
  }

  /** Ensure we get "Fizz" if the number is divisible by 3 */
  @Test
  public void testFizzBuzz2() {
    String output = runMainWithInput("27\n");
    assertTrue(output.contains("Fizz"));
  }

  /** Ensure we get "Buzz" if the number is divisible by 5 */
  @Test
  public void testFizzBuzz3() {
    String output = runMainWithInput("20\n");
    assertTrue(output.contains("Buzz"));
  }

  /** Ensure the number is returned if it is not divisible by 3 or 5 */
  @Test
  public void testFizzBuzz4() {
    String output = runMainWithInput("14\n");
    assertTrue(output.contains("14"));
  }
}
