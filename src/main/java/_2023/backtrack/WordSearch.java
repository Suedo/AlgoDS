package _2023.backtrack;

import java.io.FilterOutputStream;

public class WordSearch {

    public int boardRows, boardCols;
    public boolean found = false;


    public void dfs(char[][] board, int row, int col, char[] word, int wordPos) {

        if (found) return;
        if (wordPos == word.length) {
            found = true;
            return;
        }
        if (row < 0 || row > boardRows || col < 0 || col > boardCols) return;
        if (board[row][col] != word[wordPos]) return;


        System.out.println(String.format("at: board[%d][%d]: %s, char[%d]: %s",
                                         row, col, board[row][col], wordPos, word[wordPos]));

        dfs(board, row - 1, col, word, wordPos + 1);
        dfs(board, row + 1, col, word, wordPos + 1);
        dfs(board, row, col - 1, word, wordPos + 1);
        dfs(board, row, col + 1, word, wordPos + 1);


        return;
    }


    // https://leetcode.com/problems/word-search/
    public static void main(String[] args) {

        char[][] board = {"ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray()};
        String word = "ABCCED";

        WordSearch wordSearch = new WordSearch();
        wordSearch.boardRows = board.length - 1;
        wordSearch.boardCols = board[0].length - 1;

        wordSearch.dfs(board, 0, 0, word.toCharArray(), 0);
        System.out.println("found? " + wordSearch.found);


    }
}
