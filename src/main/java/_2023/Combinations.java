package _2023;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Combinations {

    /*
     * nCk problem
     */
    public List<List<String>> N_Choose_K(List<String> sample, int k) {

        return allCombinations(sample)
                .stream()
                .filter(strings -> strings.size() == k)
                .toList();
    }

    /**
     * https://leetcode.com/problems/combination-sum-iii/
     * n is the given number, find all possible combinations of length k,
     * such that sum of K elems == n,
     * and each of K elems is used at most once,
     * also each of L elems can be only 1 to 9
     *
     * @param k 2 <= k <= 9,
     * @param n 1 <= n <= 60
     * @return
     *
     * NOTE: this is a very poor solution, as it finds all combinations, and the selects a part
     */
    public List<List<Integer>> combinationSum(int k, int n) {
        List<Integer> sample = List.of(9, 8, 7, 6, 5, 4, 3, 2, 1);
        List<List<Integer>> result = allCombinations(sample)
                .stream()
                .filter(elems -> elems.size() == k)
                .filter(integers -> integers.stream().reduce(0, Integer::sum) == n)
                .toList();
        return result;
    }

    private <T> List<List<T>> emptyListOfList() {
        ArrayList<T> child = new ArrayList<>();
        List<List<T>> parent = new ArrayList<>();
        parent.add(child);
        return parent;
    }

    public <T> List<List<T>> allCombinations(List<T> sample) {

        if (sample.size() == 0) return emptyListOfList();

        T first = sample.get(0);
        List<T> rest = sample.subList(1, sample.size());

        List<List<T>> stageResult = new ArrayList<>();
        List<List<T>> restCombinations = allCombinations(rest);

        // the whole idea of combinations is : "how to combine this element into this collection"
        // which has two outcomes: 1. you either include the element, or 2. you exclude it
        // this is to be done at every step, for each elem of the returned restCombinations

        for (List<T> combination : restCombinations) {
            // exclude first, take the combination as is
            stageResult.add(combination);

            // include first, but in a copy of the collection
            // otherwise the same collection will be modified
            ArrayList<T> copy = new ArrayList<>(combination);
            copy.add(first);
            stageResult.add(copy);
        }

        return stageResult;
    }

    public static void main(String[] args) {

        Combinations combinations = new Combinations();
//        List<List<String>> lists = combinations.allCombinations(List.of("a", "b", "c"));
//        lists.forEach(System.out::println);

        List<List<Integer>> lists = combinations.combinationSum(9, 45);
        lists.forEach(System.out::println);


    }
}
