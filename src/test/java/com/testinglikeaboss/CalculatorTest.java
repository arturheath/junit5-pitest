package com.testinglikeaboss;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    // Method to capture the console output
    private String getConsoleOutput(Runnable task) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            task.run();
        } finally {
            System.setOut(originalOut);
        }
        return outContent.toString().trim();
    }

    @ParameterizedTest
    @CsvSource({
            "'quit', false, ''",
            "'QUIT', false, ''",
            "'QuIt', false, ''",
            "'1', true, 'Invalid Input'",
            "'hello', true, 'Invalid Input'",
            "'cos 45', true, '0,71'",
            "'cos abc', true, 'Invalid Input'",
            "'sin 30', true, '0,50'",
            "'sin xyz', true, 'Invalid Input'",
            "'tan 60', true, '1,73'",
            "'tan 123a', true, 'Invalid Input'",
            "'cot 45', true, 'Invalid Input'",
            "'2 + 3', true, '5,00'",
            "'2 + abc', true, 'Invalid Input'",
            "'5 - 2', true, '3,00'",
            "'5 - xyz', true, 'Invalid Input'",
            "'3 * 4', true, '12,00'",
            "'3 * 7a', true, 'Invalid Input'",
            "'10 / 2', true, '5,00'",
            "'10 / bcd', true, 'Invalid Input'",
            "'2 ^ 3', true, '8,00'",
            "'2 ^ x1', true, 'Invalid Input'",
            "'2 % 3', true, 'Invalid Input'",
            "'1 + 2 + 3', true, ''"
    })
    void testParseExpression(String input, boolean expectedReturn, String expectedOutput) {
        // Capture the output
        String output = getConsoleOutput(() -> assertEquals(expectedReturn, calculator.parseExpression(input)));
        // Compare the captured output
        assertEquals(expectedOutput, output);
    }
}