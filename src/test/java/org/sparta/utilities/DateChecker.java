package org.sparta.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class DateChecker {

    public boolean datesMatch(LocalDate date1, LocalDate date2){
        return date1.isEqual(date2);
    }

    public boolean datesMatch(LocalDate date1, int epoch){
        return datesMatch(date1, ValueConverter.makeDateFromEpoch(epoch));
    }

    public boolean isTodayOrEarlier(LocalDate date){
        return !date.isAfter(LocalDate.now());
    }

    public boolean isRecentPast(LocalDate date){
        return isTodayOrEarlier(date) && date.isAfter(LocalDate.now().minusDays(1));
    }

    @Nested
    @DisplayName("DateChecker Tests")
    public class DateCheckerTests {

        @Test
        @DisplayName("Check that todays date matches todays date")
        void checkThatTodaysDateMatchesTodaysDate() {
            Assertions.assertTrue(datesMatch(LocalDate.now(), LocalDate.now()));
        }

        @Test
        @DisplayName("Check that different dates do not match")
        void checkThatDifferentDatesDoNotMatch() {
            Assertions.assertFalse(datesMatch(LocalDate.now(), LocalDate.of(2000,4,1)));
        }

        @Test
        @DisplayName("Test that datesMatch works with an epoch")
        void testThatDatesMatchWorksWithAnEpoch() {
            Assertions.assertTrue(datesMatch(LocalDate.now(), Math.round(System.currentTimeMillis()/1000)));
        }

        @Test
        @DisplayName("Test isPast is true for past")
        void testIsPastIsTrueForPast() {
            Assertions.assertTrue(isTodayOrEarlier(LocalDate.now().minusDays(1)));
        }

        @Test
        @DisplayName("Test isPast is false for future")
        void testIsPastIsFalseForFuture() {
            Assertions.assertFalse(isTodayOrEarlier(LocalDate.now().plusDays(1)));
        }

        @Test
        @DisplayName("Test that two days ago is not recent past")
        void testThatTwoDaysAgoIsNotRecentPast() {
            Assertions.assertFalse(isRecentPast(LocalDate.now().minusDays(2)));
        }

        @Test
        @DisplayName("Test that moments ago is recent past")
        void testThatMomentsAgoIsRecentPast() {
            Assertions.assertTrue(isRecentPast(LocalDate.now()));

        }
    }
}
