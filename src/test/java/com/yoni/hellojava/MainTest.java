package com.yoni.hellojava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void testMain() {
        // Set up to capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Call the main method of the Main class
        Main.main(new String[]{});

        // Check if the output matches the expected string
        assertEquals("Hello, Java!", outputStream.toString().trim());
    }
}
