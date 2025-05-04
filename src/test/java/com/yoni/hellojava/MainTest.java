package com.yoni.hellojava;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testMain1() {
        String input = "100000\n5.0\n15\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Your monthly mortgage payment is: £790.79"));
    }

    @Test
    public void testMain2() {
        String input = "0\n5.0\n15\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Your monthly mortgage payment is: £0.00"));
    }

    @Test
    public void testMain3() {
        String input = "100000\n5.0\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Your monthly mortgage payment is: £8,560.64"));
    }

    @Test
    public void testMain4() {
        String input = "100000\n5.0\n30\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Your monthly mortgage payment is: £536.82"));
    }

    @Test
    public void testCalculateMonthlyPayment1() {
        String result = Main.calculateMonthlyPayment(100000, 5.0f, (byte) 15);
        assertEquals("£790.79", result);
    }

    @Test
    public void testCalculateMonthlyPayment2() {
        String result = Main.calculateMonthlyPayment(0, 5.0f, (byte) 15);
        assertEquals("£0.00", result);
    }

    @Test
    public void testCalculateMonthlyPayment3() {
        String result = Main.calculateMonthlyPayment(100000, 5.0f, (byte) 1);
        assertEquals("£8,560.64", result);
    }

    @Test
    public void testCalculateMonthlyPayment4() {
        String result = Main.calculateMonthlyPayment(2000000, 50.0f, (byte) 50);
        assertEquals("£83,333.34", result);
    }
}
