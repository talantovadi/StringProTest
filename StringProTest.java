package com.example.stringprocessing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

   public class StringProTest {

    StringProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new StringProcessor();
    }

    @Test
    void isStrongPassword() {
        StringProcessor processor = new StringProcessor();

        assertTrue(processor.isStrongPassword("StrongP@ssword1"));
        assertTrue(processor.isStrongPassword("AnotherStrong1@"));
        assertFalse(processor.isStrongPassword("weakpassword"));
        assertFalse(processor.isStrongPassword("NoSpecialChar1"));
        assertFalse(processor.isStrongPassword("NoUppercase&Digit"));
    }

    @Test
    void calculateDigits(){
        StringProcessor processor = new StringProcessor();

        assertEquals(3, processor.calculateDigits("abc123xyz"));
        assertEquals(4, processor.calculateDigits("Digit1234"));
        assertEquals(5, processor.calculateDigits("qwerty12345"));
        assertEquals(0, processor.calculateDigits("NoDigitsHere"));
        assertEquals(0, processor.calculateDigits(""));
    }

    @Test
    void calculateWords(){
        StringProcessor processor = new StringProcessor();

        assertEquals(4, processor.calculateWords("This is a test"));
        assertEquals(1, processor.calculateWords("SingleWord"));
        assertEquals(5, processor.calculateWords("A sentence with multiple words"));
        assertEquals(6, processor.calculateWords("Hello World hello world hello world"));
        assertEquals(0, processor.calculateWords(""));
    }

    @Test
    void calculateExpression(){
        StringProcessor StringProcessor = new StringProcessor();
        assertEquals(-12.0, com.example.stringprocessing.StringProcessor.calculateExpression("3 + 5 * ( 2 - 8 ) / 2"));
        assertEquals(10, com.example.stringprocessing.StringProcessor.calculateExpression("5 + 5"));
        assertEquals(25, com.example.stringprocessing.StringProcessor.calculateExpression("5 * 5"));
        assertEquals(232, com.example.stringprocessing.StringProcessor.calculateExpression("150 + 150 - 68"));
        assertEquals(0, com.example.stringprocessing.StringProcessor.calculateExpression("0 / 10"));

    }

}
