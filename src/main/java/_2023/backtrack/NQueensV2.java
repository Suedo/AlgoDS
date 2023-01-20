package _2023.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Optimized
public class NQueensV2 {

    public static List<String> printBoard(List<Integer> validBoard, int n) {
        char[][] board = new char[n][n];
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
            board[i][validBoard.get(i)] = 'Q';
            res.add(new String(board[i]));
        }
        return res;
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

    public void dfs(LinkedList<Integer> candidate, List<List<String>> state, int n) {
        if (candidate.size() > 1 && !isValid(candidate)) {
            return;
        }
        if (candidate.size() == n) {
            state.add(printBoard(candidate, n));
            return;
        }

        // permutations loop
        for (int i = 0; i < n; i++) {
            candidate.add(i); // adds to the end
            dfs(candidate, state, n);
            candidate.removeLast(); // backtrack
        }
    }

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> state = new ArrayList<>();
        dfs(new LinkedList<>(), state, n);

        return state;
    }

    public static void main(String[] args) {
        NQueensV2 nQueens = new NQueensV2();
        Instant start = Instant.now();
        List<List<String>> sol = nQueens.solveNQueens(4);// [[1, 3, 0, 2], [2, 0, 3, 1]], [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
        System.out.println("completed in (ms): " + Duration.between(start, Instant.now()).toMillis());
        System.out.println("\n\n");
        System.out.println(sol);
    }
}
