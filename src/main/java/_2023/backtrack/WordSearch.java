package _2023.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class WordSearch {

    public boolean found = false;

    // https://leetcode.com/problems/word-search/description/
    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.println(String.format("------------- [%d, %d] -------------", i, j));
                dfs(board, i, j, word.toCharArray(), 0);
                if (found) break;
            }
        }

        return found;
    }


    public void dfs(char[][] board, int row, int col, char[] word, int wordPos) {

        if (found) return;
        if (wordPos == word.length) {
            found = true;
            return;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return;
        if (board[row][col] != word[wordPos]) return;


        System.out.println(String.format("at: board[%d][%d]: %s, char[%d]: %s",
                                         row, col, board[row][col], wordPos, word[wordPos]));

        char curr = board[row][col];
        board[row][col] = 0; // remove
        dfs(board, row - 1, col, word, wordPos + 1);
        dfs(board, row + 1, col, word, wordPos + 1);
        dfs(board, row, col - 1, word, wordPos + 1);
        dfs(board, row, col + 1, word, wordPos + 1);
        board[row][col] = curr; // add back, backtracking
    }


    // https://leetcode.com/problems/word-search/
    public static void main(String[] args) {

        // String[][] strArr = {{"A", "B", "C", "E"}, {"S", "F", "C", "S"}, {"A", "D", "E", "E"}};
        String[][] strArr = {{"A","B","C","E"},{"S","F","C","S"},{"A","D","E","E"}};
        String word = "EED";

        char[][] board = new char[strArr.length][strArr[0].length];

        for (int i = 0; i < strArr.length; i++) {
            System.out.println(Arrays.toString(strArr[i]));
            char[] chars = String.join("", strArr[i]).toCharArray();
            board[i] = chars;
        }
        System.out.println("\n\n");

        WordSearch wordSearch = new WordSearch();
        Instant start = Instant.now();
        wordSearch.exist(board, word);
        long runTimeInMs = Duration.between(start, Instant.now()).toMillis();
        System.out.println("found? " + wordSearch.found);
        System.out.println("runtime (ms) : " + runTimeInMs);


    }
}


/*
[A, B, C, E]
[S, F, C, S]
[A, D, E, E]



------------- [0, 0] -------------
------------- [0, 1] -------------
------------- [0, 2] -------------
------------- [0, 3] -------------
at: board[0][3]: E, char[0]: E
------------- [1, 0] -------------
------------- [1, 1] -------------
------------- [1, 2] -------------
------------- [1, 3] -------------
------------- [2, 0] -------------
------------- [2, 1] -------------
------------- [2, 2] -------------
at: board[2][2]: E, char[0]: E
at: board[2][3]: E, char[1]: E
------------- [2, 3] -------------
at: board[2][3]: E, char[0]: E
at: board[2][2]: E, char[1]: E
at: board[2][1]: D, char[2]: D
found? true
 */
