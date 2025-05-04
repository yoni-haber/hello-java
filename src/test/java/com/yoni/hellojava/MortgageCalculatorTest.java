package com.yoni.hellojava;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MortgageCalculatorTest {

    private static String runMainWithInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        MortgageCalculator.main(new String[]{});
        return out.toString();
    }

    @Test
    public void testValidInput() {
        String output = runMainWithInput("100000\n5.0\n15\n");
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testZeroPrincipal() {
        String output = runMainWithInput("0\n100000\n5.0\n15\n");
        assertTrue(output.contains("Enter principal amount:"));
        assertTrue(output.contains("Value must be greater than zero. Please try again."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testNegativePrincipal() {
        String output = runMainWithInput("-120\n100000\n5.0\n15\n");
        assertTrue(output.contains("Enter principal amount:"));
        assertTrue(output.contains("Value must be greater than zero. Please try again."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testNonIntegerPrincipal() {
        String output = runMainWithInput("abc\n100000\n5.0\n15\n");
        assertTrue(output.contains("Enter principal amount:"));
        assertTrue(output.contains("Invalid input. Please enter a whole number."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testZeroInterest() {
        String output = runMainWithInput("100000\n0\n5.0\n15\n");
        assertTrue(output.contains("Enter annual interest rate (percentage):"));
        assertTrue(output.contains("Value must be greater than zero. Please try again."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testNegativeInterest() {
        String output = runMainWithInput("100000\n-5.0\n5.0\n15\n");
        assertTrue(output.contains("Enter annual interest rate (percentage):"));
        assertTrue(output.contains("Value must be greater than zero. Please try again."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testNonFloatInterest() {
        String output = runMainWithInput("100000\nabc\n5.0\n15\n");
        assertTrue(output.contains("Enter annual interest rate (percentage):"));
        assertTrue(output.contains("Invalid input. Please enter a decimal number."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testZeroTerm() {
        String output = runMainWithInput("100000\n5.0\n0\n15\n");
        assertTrue(output.contains("Enter loan term (in years):"));
        assertTrue(output.contains("Value must be greater than zero. Please try again."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testNegativeTerm() {
        String output = runMainWithInput("100000\n5.0\n-5\n15\n");
        assertTrue(output.contains("Enter loan term (in years):"));
        assertTrue(output.contains("Value must be greater than zero. Please try again."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testNonIntegerTerm() {
        String output = runMainWithInput("100000\n5.0\nabc\n15\n");
        assertTrue(output.contains("Enter loan term (in years):"));
        assertTrue(output.contains("Invalid input. Please enter a whole number."));
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testMonthlyPaymentCalculation() {
        double result = MortgageCalculator.calculateMonthlyPayment(500000, 4.8, 26);
        assertEquals(2808.18, result, 0.01); // delta = 1p
    }

    @Test
    public void testZeroPrincipalCalculation() {
        double result = MortgageCalculator.calculateMonthlyPayment(0, 5.0, 15);
        assertEquals(0.0, result, 0.01);
    }
}
