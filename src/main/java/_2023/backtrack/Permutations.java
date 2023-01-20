package _2023.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    /**
     * @param given     the given list of items to permute
     * @param candidate possible permutation candidate
     * @param state     when a valid candidate is found, it's copy/snapshot is added to the states list
     * @param <T>
     */
    public static <T> void dfs(List<T> given, List<T> candidate, List<List<T>> state, int n) {

        if (candidate.size() == n && given.size() == 0) {
            state.add(new ArrayList<>(candidate));
            return;
        }

        for (int i = 0; i < given.size(); i++) {
            ArrayList<T> copy = new ArrayList<>(given);
            T current = copy.remove(i);
            System.out.println("modified copy: " + copy);
            candidate.add(current);
            dfs(copy, candidate, state, n);
            candidate.remove(current); // without this, (candidate.size() == n) will always be true after 1st pass
        }

        System.out.println("> " + state);
    }

    public static void main(String[] args) {
        Permutations.dfs(List.of(1,2,3), new ArrayList<>(), new ArrayList<>(), 3);
    }
}
