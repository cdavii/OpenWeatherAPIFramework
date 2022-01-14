package org.sparta.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StringChecker {

    public static boolean contains(String lookingFor, String location) {
        if(lookingFor == null || location == null) {
            return false;
        } else {
            return location.contains(lookingFor);
        }
    }

    public static boolean containsAt(String lookingFor, String location, int index) {
        return location.indexOf(lookingFor, index) != -1;
    }

    public static boolean isNull(String string) {
        return string == null;
    }

    public static boolean isEmpty(String string) {
        return string.isEmpty();
    }

    public static boolean isLength(String string, int length) {
        if(string == null) {
            return false;
        } else {
            return string.length() == length;
        }
    }

    @Nested
    @DisplayName("StringCheckerTest")
    public class StringCheckerTests {

        @Test
        @DisplayName("Test sentence contains a word")
        void testSentenceContainsAWord() {
            String sentence = "word is in sentence";
            Assertions.assertTrue(StringChecker.contains("word",sentence));
        }

        @Test
        @DisplayName("Test that word is not in a sentence")
        void testThatWordIsNotInASentence() {
            Assertions.assertFalse(StringChecker.contains("word", "in a sentence"));
        }

        @Test
        @DisplayName("Test sentence contains word at a specific location")
        void testSentenceContainsWordAtASpecificLocation() {
            Assertions.assertTrue(StringChecker.containsAt("word", "word is at the location 0", 0));
        }

        @Test
        @DisplayName("Test that a string is null")
        void testThatAStringIsNull() {
            String string = null;
            Assertions.assertTrue(StringChecker.isNull(string));
        }

        @Test
        @DisplayName("Test that a string is not null")
        void testThatAStringIsNotNull() {
            String string = "hello, world";
            Assertions.assertFalse(StringChecker.isNull(string));
        }

        @Test
        @DisplayName("Test that a string a string is empty")
        void testThatAStringIsEmpty() {
            Assertions.assertTrue(StringChecker.isEmpty(""));
        }

        @Test
        @DisplayName("Test that a string a string is not empty")
        void testThatAStringIsNotEmpty() {
            Assertions.assertFalse(StringChecker.isEmpty("hello"));
        }

        @Test
        @DisplayName("Test that an empty string has a length of zero")
        void testThatAnEmptyStringHasALengthOfZero() {
            Assertions.assertTrue(StringChecker.isLength("", 0));
        }

        @Test
        @DisplayName("Test that an empty string has a length of zero")
        void testWordHasALength4Letters() {
            Assertions.assertTrue(StringChecker.isLength("word", 4));
        }

    }
}
