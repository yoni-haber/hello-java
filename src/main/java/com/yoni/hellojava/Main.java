package com.yoni.hellojava;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    private static final int MONTHS_IN_YEAR = 12;
    private static final int PERCENT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please provide your principal amount: ");
        int principal = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please provide your interest rate (percentage): ");
        float interestRate = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Please provide your term (in years): ");
        byte term = scanner.nextByte();

        System.out.println("Your monthly mortgage payment is: " + calculateMonthlyPayment(principal, interestRate, term));
    }

    public static String calculateMonthlyPayment(int principal, float interestRate, byte term) {
        float monthlyInterestRate = interestRate / PERCENT / MONTHS_IN_YEAR;
        int numberOfPayments = term * MONTHS_IN_YEAR;

        double monthlyPayment = principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return NumberFormat.getCurrencyInstance().format(monthlyPayment);
    }
}