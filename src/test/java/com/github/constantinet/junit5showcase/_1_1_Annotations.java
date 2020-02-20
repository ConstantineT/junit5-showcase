package com.github.constantinet.junit5showcase;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _1_1_Annotations {

    private ComputationService computationService;

    @BeforeAll // 1
    public static void beforeAll() {
        System.out.println("Executing once at the very beginning...");
    }

    @BeforeEach // 2
    public void beforeEach() {
        System.out.println("Executing before each test method...");
        computationService = new ComputationService();
    }

    @Nested // 8
    public class AdditionTests {

        @Test // 5
        @DisplayName("test 5 + 2 = 7") // 6
        @Tag("positiveNumbers") // 9
        public void testAdd_shouldReturn7_when5And2Passed() {
            // when
            final long result = computationService.add(2, 5);

            // then
            assertEquals(7, result);
        }

        @Test
        @Disabled("not supposed to be executed") // 7
        @Tag("negativeNumbers") // 9
        public void testAdd_shouldReturnNegative7_whenNegative5AndNegative2Passed() {
            // when
            final long result = computationService.add(-5, -2);

            // then
            assertEquals(-7, result);
        }
    }

    @Nested // 8
    public class SubtractionTests {

        @Test
        @Tag("positiveNumbers") // 9
        @RepeatedTest(3) // 10
        public void testSubtract_shouldReturn3_when5And2Passed() {
            // when
            final long result = computationService.subtract(5, 2);

            // then
            assertEquals(3, result);
        }

        @Test
        @Tag("negativeNumbers") // 9
        @RepeatedTest(3) // 10
        public void testSubtract_shouldReturnNegative3_whenNegative5AndNegative2Passed() {
            // when
            final long result = computationService.subtract(-5, -2);

            // then
            assertEquals(-3, result);
        }
    }

    @AfterEach // 3
    public void afterEach() {
        System.out.println("Executing after each test method...");
    }

    @AfterAll // 4
    public static void afterAll() {
        System.out.println("Executing once at the very end...");
    }
}