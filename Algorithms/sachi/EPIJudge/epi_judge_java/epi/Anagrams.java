package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class Anagrams {
    @EpiTestComparator
    public static BiPredicate<List<List<String>>, List<List<String>>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                for (List<String> l : expected) {
                    Collections.sort(l);
                }
                expected.sort(new LexicographicalListComparator<>());
                for (List<String> l : result) {
                    Collections.sort(l);
                }
                result.sort(new LexicographicalListComparator<>());
                return expected.equals(result);
            };

    @EpiTest(testDataFile = "anagrams.tsv")

    public static List<List<String>> findAnagrams(List<String> dictionary) {
        // TODO - you fill in here.
        return null;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Anagrams.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
