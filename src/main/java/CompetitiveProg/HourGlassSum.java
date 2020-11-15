package CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HourGlassSum {

  private static String path = "Files/hourglass-ip.txt";
  private static int HourGlassLen = 3;

  public static int hourglassSum(int[][] arr) {
    ArrayList<Integer> sumList = new ArrayList<>();
    for (int i = 0; i <= 6 - HourGlassLen; i++) {
      for (int j = 0; j <= 6 - HourGlassLen; j++) {
        sumList.add(calculateSum(arr, i, j));
      }
    }
    int maxSum = -100;
    for (Integer sum : sumList) {
      maxSum = sum > maxSum ? sum : maxSum;
    }
    return maxSum;
  }

  static int calculateSum(int[][] arr, int i, int j) {
    // 3x3 , minus 1st and 3rd elem of 2nd row
    int sum = 0;
    for (int k = i; k < i + 3; k++) {
      for (int l = j; l < j + 3; l++) {
        sum += arr[k][l];
      }
    }
    int row2col1 = arr[i + 1][j];
    int row2col3 = arr[i + 1][j + 2];

    sum -= row2col1 + row2col3;
    return sum;
  }

  public static void main(String[] args) {
    try (BufferedReader r = new BufferedReader(new FileReader(path))) {
      int[][] arr = new int[6][6];
      String[] nums;
      for (int i = 0; i < 6; i++) {
        nums = r.readLine().trim().split(" +");
        assert nums.length == 6 : "Each line must have 6 numbers";
        for (int j = 0; j < nums.length; j++) {
          arr[i][j] = Integer.parseInt(nums[j]);
        }
      }
      int maxSum = HourGlassSum.hourglassSum(arr);
      System.out.println(maxSum);
    } catch (IOException e) {
      System.err.println("IOException");
    }
  }
}
/*
 * 6x6 input array, arr:
 * 
 * -9 -9 -9 1 1 1 0 -9 0 4 3 2 -9 -9 -9 1 2 3 0 0 8 6 6 0 0 0 0 -2 0 0 0 0 1 2 4
 * 0
 * 
 * Each hourglass pattern: a b c d e f g
 * 
 * Thus, 1st hourglass pattern at arr[0][0]: -9 -9 -9 -9 -9 -9 -9
 * 
 * hourglass sum: -63
 * 
 * 
 * Total 4x4 array of sums obtained from arr: -63, -34, -9, 12, -10, 0, 28, 23,
 * -27, -11, -2, 10, 9, 17, 25, 18
 * 
 * maximum = 28
 */
