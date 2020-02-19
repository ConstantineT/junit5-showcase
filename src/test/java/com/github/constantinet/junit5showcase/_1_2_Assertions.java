package com.github.constantinet.junit5showcase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class _1_2_Assertions {

    public ComputationService computationService;

    @BeforeEach
    public void beforeEach() {
        computationService = new ComputationService();
    }

    @Test
    public void testFilterOutOddNumbers_shouldReturnNotNullThatIsNotTheSame_whenNotEmptyArrayPassed() {
        // given
        final long[] input = new long[]{1L, 4L, 6L, -3L};

        // when
        final long[] result = computationService.filterOutOddNumbers(input);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertNotSame(input, result)
        );
    }

    @Test
    public void testFilterOutOddNumbers_shouldReturnCorrectArray_whenNotEmptyArrayPassed() {
        // given
        final long[] input = new long[]{1L, 4L, 6L, -3L};

        // when
        final long[] result = computationService.filterOutOddNumbers(input);

        // then
        assertArrayEquals(result, new long[]{4L, 6L});
    }

    @Test
    public void testFilterOutOddNumbers_shouldNotThrowException_whenEmptyArrayPassed() {
        // given
        final long[] input = new long[]{};

        // when
        final Executable executable = () -> computationService.filterOutOddNumbers(input);

        // then
        assertDoesNotThrow(executable);
    }

    @Test
    public void testFilterOutOddNumbers_shouldThrowExceptionWithMessage_whenNullArrayPassed() {
        // given
        final long[] input = null;

        // when
        final Executable executable = () -> computationService.filterOutOddNumbers(input);

        // then
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void testDoHeavyComputation_shouldFinishInLessThan3Seconds_when2Passed() {
        // given
        final long input = 2L;

        // when
        final Executable executable = () -> computationService.doHeavyComputation(input);

        // then
        assertTimeout(Duration.ofSeconds(3), executable);
    }
}