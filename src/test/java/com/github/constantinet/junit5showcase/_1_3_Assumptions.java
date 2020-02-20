package com.github.constantinet.junit5showcase;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class _1_3_Assumptions {

    private ComputationService computationService;

    @BeforeEach
    public void beforeEach() {
        computationService = new ComputationService();
        System.setProperty("useComputationService", "true");
    }

    @Test
    public void testMultiply_shouldReturn6_when2And3Passed() {
        // given
        Assumptions.assumeTrue("false".equals(System.getProperty("useComputationService", "false")));

        // when
        final int result = 2 * 3;

        // then
        assertEquals(6, result);
    }

    @Test
    public void testMultiply_shouldReturn6_whenNegative2AndNegative3Passed() {
        // given
        Assumptions.assumingThat(!"false".equals(System.getProperty("useComputationService", "false")),
                () -> assertNotNull(computationService));

        // when
        final long result = computationService.multiply(-2, -3);

        // then
        assertEquals(6, result);
    }
}