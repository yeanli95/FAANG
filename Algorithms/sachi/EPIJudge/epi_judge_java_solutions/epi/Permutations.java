package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class Permutations {
    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                for (List<Integer> l : expected) {
                    Collections.sort(l);
                }
                expected.sort(new LexicographicalListComparator<>());
                for (List<Integer> l : result) {
                    Collections.sort(l);
                }
                result.sort(new LexicographicalListComparator<>());
                return expected.equals(result);
            };

    @EpiTest(testDataFile = "permutations.tsv")

    public static List<List<Integer>> permutations(List<Integer> A) {

        List<List<Integer>> result = new ArrayList<>();
        // Generate the first permutation in dictionary order.
        Collections.sort(A);
        do {
            result.add(new ArrayList<>(A));
            A = NextPermutation.nextPermutation(A);
        } while (!A.isEmpty());
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Permutations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
