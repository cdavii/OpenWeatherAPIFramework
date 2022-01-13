package org.sparta.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NumberChecker {

    public static boolean withinRange(Number lowerBound, Number upperBound, Number candidateNumber){
        return(candidateNumber.doubleValue() <= upperBound.doubleValue() && candidateNumber.doubleValue() >= lowerBound.doubleValue());
    }

    public static boolean isPositive(Number number){
        return number.doubleValue() > 0;
    }

    @Nested
    @DisplayName("NumberCheckerTests")
    public class NumberCheckerTests{

        @Test
        @DisplayName("Test that withinRange returns true for int in range")
        void testThatWithinRangeReturnsTrueForIntInRange() {
            Assertions.assertTrue(withinRange(5,25,15));
        }

        @Test
        @DisplayName("Test that withinRange returns true for Double in range")
        void testThatWithinRangeReturnsTrueForDoubleInRange() {
            Assertions.assertTrue(withinRange(14.24,91.782111,29.3131));
        }

        @Test
        @DisplayName("Test that withinRange is false for numbers out of range")
        void testThatWithinRangeIsFalseForNumbersoutOfRange() {
            Assertions.assertTrue(withinRange(14.24,91.782111,1000.1));
        }

        @Test
        @DisplayName("Test that withinRange works for numbers of different types")
        void testThatWithinRangeWorksForNumbersOfDifferentTypes() {
            Assertions.assertTrue(withinRange(0.00000004,60,11));
        }

        @Test
        @DisplayName("Test that isPositive works with positive int")
        void testThatIsPositiveWorksWithPositiveInt() {
            Assertions.assertTrue(isPositive(1));
        }

        @Test
        @DisplayName("Test that isPositive is false for 0")
        void testThatIsPositiveIsFalseFor0() {
            Assertions.assertFalse(isPositive(0));
        }

        @Test
        @DisplayName("Test that negative double is false")
        void testThatNegativeDoubleIsFalse() {
            Assertions.assertFalse(isPositive(-14.12));
        }

    }
}
