package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class MortgageCalculatorTest {

  private static String runMainWithInput(String input) {
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    MortgageCalculator.main(new String[] {});
    return out.toString();
  }

  /** Ensure we can calculate the monthly payment correctly */
  @Test
  public void testMortgageCalculator1() {
    String output = runMainWithInput("100000\n5.0\n15\n");
    assertTrue(output.contains("Welcome to the Mortgage Calculator!"));
    assertTrue(output.contains("Enter principal amount:"));
    assertTrue(output.contains("Enter annual interest rate (percentage):"));
    assertTrue(output.contains("Enter loan term (in years):"));
    assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
  }

  /** Ensure we can handle principal input of less than 0 */
  @Test
  public void testMortgageCalculator2() {
    String output = runMainWithInput("-120000\n100000\n5.0\n15\n");
    assertTrue(output.contains("Value must be greater than zero. Please try again."));
  }

  /** Ensure we can handle non-integer principal input */
  @Test
  public void testMortgageCalculator3() {
    String output = runMainWithInput("abc\n100000\n5.0\n15\n");
    assertTrue(output.contains("Invalid input. Please enter a whole number."));
  }

  /** Ensure we can handle interest rate input of less than 0 */
  @Test
  public void testMortgageCalculator4() {
    String output = runMainWithInput("100000\n-5.0\n5.0\n15\n");
    assertTrue(output.contains("Value must be greater than zero. Please try again."));
  }

  /** Ensure we can handle non-float interest rate input */
  @Test
  public void testMortgageCalculator5() {
    String output = runMainWithInput("100000\nabc\n5.0\n15\n");
    assertTrue(output.contains("Invalid input. Please enter a decimal number."));
  }

  /** Ensure we can handle loan term input of less than 0 */
  @Test
  public void testMortgageCalculator6() {
    String output = runMainWithInput("100000\n5.0\n-5\n15\n");
    assertTrue(output.contains("Value must be greater than zero. Please try again."));
  }

  /** Ensure we can handle non-integer loan term input */
  @Test
  public void testMortgageCalculator7() {
    String output = runMainWithInput("100000\n5.0\nabc\n15\n");
    assertTrue(output.contains("Invalid input. Please enter a whole number."));
  }

  /** Ensure we can calculate the monthly payment correctly */
  @Test
  public void testCalculateMonthlyPayment1() {
    double result = MortgageCalculator.calculateMonthlyPayment(500000, 4.8, 26);
    assertEquals(2808.18, result, 0.01); // delta = 1p
  }

  /** Ensure we can calculate the monthly payment correctly */
  @Test
  public void testCalculateMonthlyPayment2() {
    double result = MortgageCalculator.calculateMonthlyPayment(0, 5.0, 15);
    assertEquals(0.0, result, 0.01);
  }
}
