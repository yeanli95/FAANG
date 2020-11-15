package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.List;
import java.util.function.BiPredicate;

public class NQueens {
    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                expected.sort(new LexicographicalListComparator<>());
                result.sort(new LexicographicalListComparator<>());
                return expected.equals(result);
            };

    @EpiTest(testDataFile = "n_queens.tsv")

    public static List<List<Integer>> nQueens(int n) {
        // TODO - you fill in here.
        return null;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NQueens.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
