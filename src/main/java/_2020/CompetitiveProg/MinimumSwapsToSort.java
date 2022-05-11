package _2020.CompetitiveProg;

import java.util.Arrays;

//https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
// basically, a selection sort implementation
public class MinimumSwapsToSort {
    static int minimumSwaps(int[] arr) {
        // selection sort algo with one crucial step added extra
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;
            int minVal = arr[i];
            // linear search for minimum
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < minVal) {
                    minPos = j;
                    minVal = arr[j];
                }
            }
            // this is the crucial step that reduces the number of swaps
            if (i != minPos) {
                swap(arr, i, minPos);
                count++;
            }
        }
        return count;
    }


    private static void swap(int[] arr, int i, int j) {
        System.out.println(Arrays.toString(arr));
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 3, 2, 4, 5, 6};
        System.out.println(minimumSwaps(arr));
    }
}
