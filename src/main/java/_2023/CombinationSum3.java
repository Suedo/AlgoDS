package _2023;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {

    private List<List<Integer>> state;

    /**
     * https://leetcode.com/problems/combination-sum-iii/description/
     *
     * @param k num of elems to choose from nums
     * @param n k elems should add up to this value
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        state = new ArrayList<>();
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        dfs(integers, new ArrayList<>(), state, n, k);
        return state;
    }

    private int sumOf(List<Integer> list) {
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum;
    }

    /**
     * @param n remaining targetsum
     * @param k remaining digits left to form targetsum
     */
    private void dfs(List<Integer> rem, List<Integer> candidate, List<List<Integer>> state, int n, int k) {
        if (k == 0) {
            if (n == 0) {
                System.out.println("++ " + candidate);
                state.add(new ArrayList<>(candidate));
            }
            return;
        }

        for (int i = 0; i < rem.size(); i++) {
            Integer current = rem.get(i);
            ArrayList<Integer> rest = new ArrayList<>(rem.subList(i + 1, rem.size()));
            System.out.println("working on : " + current);
            candidate.add(current);
            dfs(rest, candidate, state, n - current, k - 1);
            candidate.remove(current);
        }
        System.out.println("> " + state);
    }

    private List<List<Integer>> iterative(int k, int n) {

        state = new ArrayList<>();
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<List<Integer>> candidates = new ArrayList<>();
        candidates.add(new ArrayList<>());


        for (int idx = 0; idx < integers.size(); idx++) {
            Integer current = integers.get(idx);

            List<List<Integer>> additions = new ArrayList<>();
            for (int i = 0; i < candidates.size(); i++) {
                ArrayList<Integer> withoutCurrent = new ArrayList<>(candidates.get(i));
                additions.add(withoutCurrent);
                List<Integer> withCurrent = candidates.get(i);
                withCurrent.add(current);

                int sum = sumOf(withCurrent);
                if (sum == n && withCurrent.size() == k) {
                    state.add(new ArrayList<>(withCurrent));
                } else if (sum < n && withCurrent.size() < k) {
                    // this candidate can still give a valid output
                    additions.add(new ArrayList<>(withCurrent));
                }
            }
            candidates = additions; // since we cannot modify candidates with iterating over it

            String format = String.format("current: %s candidates: %s, state: %s", current, candidates, state);
            System.out.println(format);
        }
        return state;

    }


    public static void main(String[] args) {
        CombinationSum3 combinationSum3 = new CombinationSum3();
        List<List<Integer>> state = combinationSum3.iterative(2, 5);
        System.out.println("\n\n");
        System.out.println(state);

    }
}
