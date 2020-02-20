package com.github.constantinet.junit5showcase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.constantinet.junit5showcase._1_5_Extensions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;

@ExtendWith({TestInstanceExtension.class, ConditionExtension.class, CallbackExtension.class,
        IgnoreArithmeticExceptionExtension.class})
public class _1_5_Extensions {

    public ComputationService computationService;

    @BeforeEach
    public void beforeEach() {
        computationService = new ComputationService();
    }

    @ParameterizedTest(name = "Test {0} / {1} == {2}")
    @MethodSource("provideInputForTestDivide")
    public void testDivide_shouldReturnExpectedValues_whenDifferentInputsPassed(final long dividend,
                                                                                final long divisor,
                                                                                final long expected) {
        // when
        final long result = computationService.divide(dividend, divisor);

        // then
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideInputForTestDivide() {
        return Stream.of(
                Arguments.of(6, 3, 2),
                Arguments.of(0, 0, 0)
        );
    }

    public static class TestInstanceExtension implements TestInstancePostProcessor {

        @Override
        public void postProcessTestInstance(final Object testInstance, final ExtensionContext context) {
            System.out.println("Created a test instance of " + testInstance.getClass().getName());
        }
    }

    public static class ConditionExtension implements ExecutionCondition {

        @Override
        public ConditionEvaluationResult evaluateExecutionCondition(final ExtensionContext context) {
            if ("false".equals(System.getProperty("doNotUseComputationService", "false"))) {
                return enabled("enabled because ComputationService is supposed to be in use");
            } else {
                return disabled("disabled because ComputationService is not supposed to be in use");
            }
        }
    }

    public static class CallbackExtension implements BeforeEachCallback, AfterEachCallback {

        @Override
        public void beforeEach(final ExtensionContext context) {
            System.out.println("Executing test method " + context.getDisplayName() + "...");
        }

        @Override
        public void afterEach(final ExtensionContext context) {
            System.out.println("Executed test method " + context.getDisplayName());
        }
    }

    public static class IgnoreArithmeticExceptionExtension implements TestExecutionExceptionHandler {

        @Override
        public void handleTestExecutionException(final ExtensionContext context,
                                                 final Throwable throwable) throws Throwable {
            if (throwable instanceof ArithmeticException) {
                System.out.println("ArithmeticException has occurred");
            } else {
                throw throwable;
            }
        }
    }
}
