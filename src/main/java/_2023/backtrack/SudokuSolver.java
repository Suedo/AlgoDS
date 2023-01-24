package _2023.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuSolver {

    List<Set<Character>> rows = new ArrayList<>();
    List<Set<Character>> cols = new ArrayList<>();
    List<Set<Character>> grids = new ArrayList<>();
    char dot = '.';
    //    List<Character> valids = List.of('1', '2', '3', '4', '5', '6', '7', '8', '9');
    char[] valids = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public int gridNum(int i, int j) {
        return ((i / 3) * 3) + j / 3;
    }

    public void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("");
        }
    }

    // https://leetcode.com/problems/sudoku-solver/
    public void solveSudoku(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            grids.add(new HashSet<>());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char curr = board[i][j];
                if (curr != dot) {
                    rows.get(i).add(curr);
                    cols.get(j).add(curr);
                    grids.get(gridNum(i, j)).add(curr);
                }
            }
        }

        dfs(board, 0, 0);
        print(board);
    }

    public void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return;
        if (board[row][col] != dot) return;

        HashSet<Character> total = new HashSet<>(rows.get(row));
        total.addAll(cols.get(col));
        total.addAll(grids.get(gridNum(row, col)));

        List<Character> options = new ArrayList<>();
        for (Character c : valids) {
            if (!total.contains(c)) options.add(c);
        }
        System.out.println(String.format("[%d, %d], "));

        for (Character option : options) {
            board[row][col] = option;
            dfs(board, row, col - 1);
            dfs(board, row, col + 1);
            dfs(board, row - 1, col);
            dfs(board, row + 1, col);
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
        sudokuSolver.solveSudoku(board);
    }
}
