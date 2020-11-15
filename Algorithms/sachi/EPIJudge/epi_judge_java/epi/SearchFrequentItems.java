package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class SearchFrequentItems {

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

    public static List<String> searchFrequentItems(int k,
                                                   Iterable<String> stream) {
        // TODO - you fill in here.
        return null;
    }

    @EpiTest(testDataFile = "search_frequent_items.tsv")
    public static List<String> searchFrequentItemsWrapper(int k,
                                                          List<String> stream) {
        return searchFrequentItems(k, stream);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFrequentItems.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
