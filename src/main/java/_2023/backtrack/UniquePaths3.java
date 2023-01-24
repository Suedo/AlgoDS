package _2023.backtrack;

import java.time.Duration;
import java.time.Instant;

public class UniquePaths3 {

    int pathLen = 0;
    int sx = 0, sy = 0; // start point
    int ex = 0, ey = 0; // end point

    // https://leetcode.com/problems/unique-paths-iii/
    public int uniquePathsIII(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) pathLen++;
                else if (grid[i][j] == 1) {
                    sx = i; sy = j;
                } else if (grid[i][j] == 2) {
                    ex = i; ey = j;
                }
            }
        }

        return dfs(grid, sx, sy, 0);

    }

    public int dfs(int[][] grid, int row, int col, int currLen) {
        if (row == ex && col == ey) {
            System.out.println(String.format("!!! [%d,%d] len:%d, path:%d", row, col, currLen, pathLen));
            if (currLen - 1 == pathLen)
                return 1; // 1 path found, the -1 is needed, because currLen is incremented at prev step
            else return 0; // not all covered
        }
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return 0; // bounds
        if (grid[row][col] < 0) return 0; // obstacle

        System.out.println(String.format("[%d,%d] len:%d", row, col, currLen));

        currLen++;
        grid[row][col] = -2; // visited
        int total = dfs(grid, row - 1, col, currLen) +
                dfs(grid, row + 1, col, currLen) +
                dfs(grid, row, col - 1, currLen) +
                dfs(grid, row, col + 1, currLen);
        grid[row][col] = 0;
        currLen--;
        return total;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        UniquePaths3 uniquePaths3 = new UniquePaths3();

        Instant start = Instant.now();
        int count = uniquePaths3.uniquePathsIII(grid);
        System.out.println(String.format("count = %d, time(ms): %d",
                                         count,
                                         Duration.between(start, Instant.now()).toMillis()));
    }
}
