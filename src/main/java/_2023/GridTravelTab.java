package _2023;


/*
    Algorithm implemented in a tabular approach, hence the suffix 'tab'
 */
public class GridTravelTab {

    record ObstaclePoint(int x, int y) {}

    private int[][] setup(int row, int col) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = 0; // fill
            }
        }
        arr[1][1] = 1;
        return arr;
    }


    /*
        https://leetcode.com/problems/unique-paths/description/
     */
    private void countPaths(int row, int col) {
        int[][] arr = setup(row + 1, col + 1);

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (i == 1 && j == 1) ; // do not overwrite seed value
                else arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }

        System.out.println(arr[row][col]);
    }


    /*
        https://leetcode.com/problems/unique-paths-ii/description/
     */
    private int countPathsObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;


        int[][] arr = setup(row + 1, col + 1);
        if (obstacleGrid[0][0] == 1) arr[1][1] = 0; // unseed: if seed value location is itself an obstacle

        int newRow = row + 1, newCol = col + 1;

        for (int i = 1; i < newRow; i++) {
            for (int j = 1; j < newCol; j++) {
                if (i == 1 && j == 1) ; // do not overwrite seed value
                else if (obstacleGrid[i - 1][j - 1] == 1) ; // ignore obstacles
                else arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[row][col];
    }


    public static void main(String[] args) {
        GridTravelTab gridTravel = new GridTravelTab();
        //gridTravel.countPaths(3, 7);
        int[][] obstacles = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(gridTravel.countPathsObstacles(obstacles));
    }

}
