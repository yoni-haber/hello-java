package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class BankAccountManagerTest {

  private String runWithInput(String input) {
    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;

    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    System.setIn(in);
    System.setOut(new PrintStream(out));

    try {
      BankAccountManager.main(new String[] {});
      return out.toString();
    } finally {
      System.setIn(originalIn);
      System.setOut(originalOut);
    }
  }

  /** Ensure we can deposit successfully */
  @Test
  public void testBankAccountManager1() {
    String input = "1\n500\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Deposit successful"));
    assertTrue(output.contains("£1,500.00"));
  }

  /** Ensure we can withdraw successfully */
  @Test
  public void testBankAccountManager2() {
    String input = "2\n200\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Withdrawal successful"));
    assertTrue(output.contains("£800.00"));
  }

  /** Ensure we can check the balance */
  @Test
  public void testBankAccountManager3() {
    String input = "3\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Your balance is: £1,000.00"));
  }

  /** Ensure we handle invalid service choices */
  @Test
  public void testBankAccountManager4() {
    String input = "9\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Invalid choice. Please enter 1, 2 or 3."));
  }

  /** Ensure we handle invalid input for deposits */
  @Test
  public void testBankAccountManager5() {
    String input = "1\nabc\n500\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Invalid input. Please enter a whole number."));
    assertTrue(output.contains("Deposit successful"));
  }

  /** Ensure the user cannot go into overdraft */
  @Test
  public void testBankAccountManager6() {
    String input = "2\n1500\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Insufficient funds"));
  }

  /** Ensure the user cannot deposit a negative amount */
  @Test
  public void testBankAccountManager7() {
    String input = "1\n-200\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Deposit must be positive"));
  }

  /** Ensure we can handle multiple transactions */
  @Test
  public void testBankAccountManager8() {
    String input = "1\n500\ny\n2\n200\ny\n3\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Deposit successful"));
    assertTrue(output.contains("Do you require another service? (Y/n)"));
    assertTrue(output.contains("Withdrawal successful"));
    assertTrue(output.contains("Your balance is: £1,300.00"));
  }

  /** Ensure we get the "thank you" message */
  @Test
  public void testBankAccountManager9() {
    String input = "1\n500\nn\n";
    String output = runWithInput(input);
    assertTrue(output.contains("Thank you for using the Bank Account Manager!"));
  }
}
