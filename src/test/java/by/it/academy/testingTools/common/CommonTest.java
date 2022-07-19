package by.it.academy.testingTools.common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for demonstration string-boot-starter tools")
public class CommonTest {

    private static Object testData;

    @Test
    @DisplayName("Check JUnit 5 tools")
    void availableUserTest() {
        assertEquals("Max", "Max");
        assertNotEquals("max", "dima");
        assertNotNull(new Object());
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("max"));
    }

    @Test
    void checkOccurrenceCharacterToString() {
        IntStream chars = "Hello world, Alex!".chars();
        assertEquals(4, chars.filter(value -> value == 'l').count(), "Must be 4");
    }

    @Test
    void checkSymbolsOrder() {
        char[] chars = "Hello world, Alex!".toCharArray();
        assertAll("Block head",
                () -> assertEquals('H', chars[0]),
                () -> assertEquals('d', chars[2]),
                () -> assertEquals('!', chars[chars.length - 1]));
    }

    @BeforeAll
    static void setUp() {
        testData = new Object();
    }

    @AfterAll
    static void tearDown() {

    }

}
