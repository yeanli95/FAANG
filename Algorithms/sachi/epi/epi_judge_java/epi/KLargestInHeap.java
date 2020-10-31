package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class KLargestInHeap {
    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

    @EpiTest(testDataFile = "k_largest_in_heap.tsv")

    public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
        // TODO - you fill in here.
        return null;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
