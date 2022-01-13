package org.sparta.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class CollectionChecker {

    public boolean containsDuplicates(Collection<Object> collection){
        Collection<Object> newCollection = new ArrayList<>(collection);

        for (Object o : newCollection) {
            newCollection.remove(o);
            if(newCollection.contains(o)) {
                return true;
            }
        }
         return false;
    }

    @Nested
    @DisplayName("CollectionChecker") 
    public class CollectionCheckerTests{

        @Test
        @DisplayName("Test that containsDuplicates is true for duplicate objects")
        void testThatContainsDuplicatesIsTrueForDuplicateObjects() {
            ArrayList<Object> arraylist = new ArrayList<>();
            arraylist.add("LOL");
            arraylist.add("LOL");

            Assertions.assertTrue(containsDuplicates(arraylist));
        }

        @Test
        @DisplayName("Test that containsDuplicates is false for distinct objects")
        void testThatContainsDuplicatesIsFalseForDistinctObjects() {
            ArrayList<Object> arraylist = new ArrayList<>();
            arraylist.add(":)");
            arraylist.add(":(");
            Assertions.assertFalse(containsDuplicates(arraylist));
        }
        
    }
}
