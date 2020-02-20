package com.github.constantinet.junit5showcase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _1_4_Parameterized {

    private ComputationService computationService;

    @BeforeEach
    public void beforeEach() {
        computationService = new ComputationService();
    }

    @ParameterizedTest(name = "Test if {0} is odd")
    @ValueSource(longs = {1, 7, 135})
    public void testIsOdd_shouldReturnTrue_whenOddInputsPassed(final long number) {
        // when
        final boolean result = computationService.isOdd(number);

        // then
        assertEquals(true, result);
    }

    @ParameterizedTest
    @CsvSource({"5,2,7", "-5,-2,-7"})
    public void testAdd_shouldReturnExpectedValues_whenDifferentInputsPassed(final long addend0,
                                                                            final long addend1,
                                                                            final long expected) {
        // when
        final long result = computationService.add(addend0, addend1);

        // then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/subtract-test-input.csv", numLinesToSkip = 1)
    public void testSubtract_shouldReturnExpectedValues_whenDifferentInputsPassed(final long minuend,
                                                                                 final long subtrahend,
                                                                                 final long expected) {
        // when
        final long result = computationService.subtract(minuend, subtrahend);

        // then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideInputForTestMultiply")
    public void testMultiply_shouldReturnExpectedValues_whenDifferentInputsPassed(final long multiplicand,
                                                                                 final long multiplier,
                                                                                 final long expected) {
        // when
        final long result = computationService.multiply(multiplicand, multiplier);

        // then
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideInputForTestMultiply() {
        return Stream.of(
                Arguments.of(2, 3, 6),
                Arguments.of(-2, -3, 6)
        );
    }
}
