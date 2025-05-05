package com.yoni.hellojava;

public class BankAccount {
  private int balance;

  public BankAccount(int initialBalance) {
    if (initialBalance < 0)
      throw new IllegalArgumentException("Initial balance cannot be negative.");
    this.balance = initialBalance;
  }

  public void deposit(int amount) {
    if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive.");
    balance += amount;
  }

  public void withdraw(int amount) {
    if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive.");
    if (amount > balance) throw new IllegalArgumentException("Insufficient funds.");
    balance -= amount;
  }

  public int getBalance() {
    return balance;
  }
}
