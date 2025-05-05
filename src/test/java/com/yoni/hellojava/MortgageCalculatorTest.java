package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class MortgageCalculatorTest {

  private String runWithInput(String input) {
    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;

    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    System.setIn(in);
    System.setOut(new PrintStream(out));

    try {
      MortgageCalculator.main(new String[] {});
      return out.toString();
    } finally {
      System.setIn(originalIn);
      System.setOut(originalOut);
    }
  }

  /** Ensure we can calculate the monthly payment correctly */
  @Test
  public void testMortgageCalculator1() {
    String output = runWithInput("100000\n5.0\n15\n");
    assertTrue(output.contains("Welcome to the Mortgage Calculator!"));
    assertTrue(output.contains("Enter principal amount:"));
    assertTrue(output.contains("Enter annual interest rate (percentage):"));
    assertTrue(output.contains("Enter loan term (in years):"));
    assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
  }

  /** Ensure we can handle principal input of less than 0 */
  @Test
  public void testMortgageCalculator2() {
    String output = runWithInput("-120000\n100000\n5.0\n15\n");
    assertTrue(output.contains("Value must be greater than zero. Please try again."));
  }

  /** Ensure we can handle non-integer principal input */
  @Test
  public void testMortgageCalculator3() {
    String output = runWithInput("abc\n100000\n5.0\n15\n");
    assertTrue(output.contains("Invalid input. Please enter a whole number."));
  }

  /** Ensure we can handle interest rate input of less than 0 */
  @Test
  public void testMortgageCalculator4() {
    String output = runWithInput("100000\n-5.0\n5.0\n15\n");
    assertTrue(output.contains("Value must be greater than zero. Please try again."));
  }

  /** Ensure we can handle non-float interest rate input */
  @Test
  public void testMortgageCalculator5() {
    String output = runWithInput("100000\nabc\n5.0\n15\n");
    assertTrue(output.contains("Invalid input. Please enter a decimal number."));
  }

  /** Ensure we can handle loan term input of less than 0 */
  @Test
  public void testMortgageCalculator6() {
    String output = runWithInput("100000\n5.0\n-5\n15\n");
    assertTrue(output.contains("Value must be greater than zero. Please try again."));
  }

  /** Ensure we can handle non-integer loan term input */
  @Test
  public void testMortgageCalculator7() {
    String output = runWithInput("100000\n5.0\nabc\n15\n");
    assertTrue(output.contains("Invalid input. Please enter a whole number."));
  }
}
