package com.github.constantinet.junit5showcase;

import java.util.Arrays;
import java.util.Objects;

public class ComputationService {

    public long add(final long addend0, final long addend1) {
        return addend0 + addend1;
    }

    public long subtract(final long minuend, final long subtrahend) {
        return minuend - subtrahend;
    }

    public long multiply(final long multiplicand, final long multiplier) {
        return multiplicand * multiplier;
    }

    public long divide(final long dividend, final long divisor) {
        return dividend / divisor;
    }

    public boolean isOdd(final long number) {
        return number % 2 != 0;
    }

    public long[] filterOutOddNumbers(final long... numbers) {
        Objects.requireNonNull(numbers, "numbers array can not be null");

        return Arrays.stream(numbers)
                .filter(number -> number % 2 == 0)
                .toArray();
    }

    public void doHeavyComputation(final long numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}