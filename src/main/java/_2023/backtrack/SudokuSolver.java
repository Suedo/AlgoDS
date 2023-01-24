package _2023.backtrack;

import java.time.Duration;
import java.time.Instant;

public class SudokuSolver {

    char dot = '.';
    char[] valids = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    // https://leetcode.com/problems/sudoku-solver/
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    private boolean isValid(char[][] board, int row, int col, char option) {

        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == option || board[i][col] == option) return false;
        }

        int subGridStartRow = row - row % 3;
        int subGridStartCol = col - col % 3;
        for (int i = subGridStartRow; i < subGridStartRow + 3; i++) {
            for (int j = subGridStartCol; j < subGridStartCol + 3; j++) {
                if (board[i][j] == option) return false;
            }
        }
        return true;
    }

    public boolean dfs(char[][] board, int row, int col) {
        // System.out.println(String.format("at [%d, %d]", row, col));

        if (row == 9) return true; // solved
        if (col == 9) return dfs(board, row + 1, 0); // next row
        if (board[row][col] != dot) return dfs(board, row, col + 1);

        for (char option : valids) {
            if (isValid(board, row, col, option)) {
                board[row][col] = option;
                // System.out.println(String.format("    [%d, %d], v: %s", row, col, option));
                if (dfs(board, row, col + 1)) return true;
                board[row][col] = dot; // backtrack
            }
        }
        return false;
    }

    public void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("");
        }
    }


    public static void main(String[] args) {

        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        SudokuSolver sudokuSolver = new SudokuSolver();
        Instant start = Instant.now();
        sudokuSolver.solveSudoku(board);
        Instant stop = Instant.now();

        sudokuSolver.print(board);
        System.out.println("\n\n");
        System.out.println("time taken (ms): " + Duration.between(start, stop).toMillis());
    }
}
