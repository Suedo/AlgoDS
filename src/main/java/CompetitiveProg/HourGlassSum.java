package CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HourGlassSum {
  // Complete the hourglassSum function below.
  private static String path = "Files/hourglass-ip.txt";

  static int hourglassSum(int[][] arr) {
    return 1;
  }

  public static void main(String[] args) {

    String line = "";
    int[][] arr = new int[6][6];
    try (BufferedReader r = new BufferedReader(new FileReader(path))) {
      for (int i = 0; i < 6; i++) {
        line = r.readLine();
        String[] nums = line.trim().split(" +");
        for (int j = 0; j < nums.length; j++) {
          arr[i][j] = Integer.parseInt(nums[j]);
        }
      }
      System.out.println(arr);
    } catch (IOException e) {
      System.err.println("File Not Found");
    }
  }
}
