package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

  /** Ensure if we instantiate with a negative balance, we throw an exception */
  @Test
  public void testBankAccount1() {
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1000));
    assertEquals("Initial balance cannot be negative.", exception.getMessage());
  }

  /** Ensure a balance can undergo multiple transactions */
  @Test
  public void testBankAccount2() {
    BankAccount account = new BankAccount(1000);
    account.deposit(500);
    account.withdraw(200);
    account.deposit(950);
    assertEquals(2250, account.getBalance());
  }

  /** Ensure we can get the balance */
  @Test
  public void testGetBalance1() {
    BankAccount account = new BankAccount(1000);
    assertEquals(1000, account.getBalance());
  }

  /** Ensure we can deposit as expected */
  @Test
  public void testDeposit1() {
    BankAccount account = new BankAccount(1000);
    account.deposit(500);
    assertEquals(1500, account.getBalance());
  }

  /** Ensure if we deposit a negative amount, we throw an exception */
  @Test
  public void testDeposit2() {
    BankAccount account = new BankAccount(1000);
    Exception exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(-500));
    assertEquals("Deposit must be positive.", exception.getMessage());
  }

  /** Ensure we can withdraw as expected */
  @Test
  public void testWithdraw1() {
    BankAccount account = new BankAccount(1000);
    account.withdraw(500);
    assertEquals(500, account.getBalance());
  }

  /** Ensure if we withdraw a negative amount, we throw an exception */
  @Test
  public void testWithdraw2() {
    BankAccount account = new BankAccount(1000);
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-500));
    assertEquals("Withdrawal must be positive.", exception.getMessage());
  }

  /** Ensure if we withdraw more than the balance, we throw an exception */
  @Test
  public void testWithdraw3() {
    BankAccount account = new BankAccount(1000);
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1500));
    assertEquals("Insufficient funds.", exception.getMessage());
  }
}
