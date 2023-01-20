package _2023.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NQueens {

    public static List<List<String>> printBoard(List<List<Integer>> state) {
        List<List<String>> stateString = new ArrayList<>();
        if(state.size() == 0) return stateString;
        int n = state.get(0).size();
        for (List<Integer> solution : state) {
            List<String> QBoard = new ArrayList<>();
            for (int i = 0; i < solution.size(); i++) {
                String row = ".".repeat(solution.get(i)) + "Q" + ".".repeat(n - solution.get(i) - 1);
                QBoard.add(row);
            }
            stateString.add(QBoard);
        }
        return stateString;
    }

    public boolean isValid(List<Integer> candidate) {
        int lastQueenRow = candidate.size() - 1; // most recent
        Integer lastQueenCol = candidate.get(lastQueenRow);
        for (int i = 0; i < candidate.size() - 1; i++) {
            Integer currentColPos = candidate.get(i);
            if (currentColPos == lastQueenCol) return false;

            int rowDiff = Math.abs(lastQueenRow - i);
            int colDiff = Math.abs(lastQueenCol - currentColPos);

            if (colDiff == rowDiff) return false; // same diagonal, same slope
        }
        return true;
    }

    public void dfs(List<Integer> given, List<Integer> candidate, List<List<Integer>> state, int n) {

        if (candidate.size() > 1 && !isValid(candidate)) return;
        if (candidate.size() == n) {
            state.add(new ArrayList<>(candidate));
            return;
        }

        // permutations loop
        for (int i = 0; i < given.size(); i++) {
            ArrayList<Integer> copy = new ArrayList<>(given);
            Integer current = copy.remove(i);
            candidate.add(current);
            dfs(copy, candidate, state, n);
            candidate.remove(current); // backtrack
        }


    }

    public List<List<String>> solveNQueens(int n) {

        List<List<Integer>> state = new ArrayList<>();
        List<Integer> given = IntStream.range(0, n).boxed().toList();
        dfs(given, new ArrayList<>(), state, n);

        System.out.println("\n\n");
        System.out.println(state);
        List<List<String>> QBoard = printBoard(state);

        System.out.println("\n\n");
        System.out.println(QBoard);
        return QBoard;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        nQueens.solveNQueens(4); // [[1, 3, 0, 2], [2, 0, 3, 1]], [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
    }
}
