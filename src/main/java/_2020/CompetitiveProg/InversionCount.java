package _2020.CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class InversionCount {

    private static int[] aux;
    private static String path = "Files/in/in/inv-count-single.txt";


    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        aux = Arrays.copyOf(arr, arr.length);
        long inv = mergeSort(arr, 0, arr.length - 1);
        return inv;
    }

    public static void sort(int[] arr) {
        aux = Arrays.copyOf(arr, arr.length);
        long inv = mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(inv);
    }

    public static long mergeSort(int[] arr, int lo, int hi) {
        if (hi <= lo) return 0;
        int mid = (lo + hi) / 2;
        long inv = mergeSort(arr, lo, mid);
        inv += mergeSort(arr, mid + 1, hi);
        inv += merge(arr, lo, mid, hi);
        return inv;
    }


    public static long merge(int[] arr, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        long inv = 0;
        for (int k = lo; k <= hi; k++) { // hi is a valid index, so inclusive
            aux[k] = arr[k];
        }
        // System.out.printf("Arr: %s, lo: %d, mid: %d, hi: %d, inv: %d\n", Arrays.toString(arr), lo, mid, hi, inv);
        for (int k = lo; k <= hi; k++) {                        // k is inclusive, as it is a valid index
            if      (i > mid)           arr[k] = aux[j++];      // since i is inclusive of mid, j starts at mid + 1 above
            else if (j > hi)            arr[k] = aux[i++];
            else if (aux[i] <= aux[j])   arr[k] = aux[i++];     // keeping <results in excess calls to the inv calculating `else` case below, causing incorrect result
            else {
                inv += mid - (i - 1); // i'th position needs to be included in count
                // System.out.printf("Inv :: Arr: %s, i: %d, j: %d, k: %d mid:%d inv:%d\n", Arrays.toString(arr), i, j, k, mid, mid - (i - 1));
                arr[k] = aux[j++]; // inversion
            }
        }
        return inv;
    }

    public static void main(String[] args) {
        int[] bigarr = new int[100000];
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {
            String[] sarr = r.readLine().split(" +");
            for (int i = 0; i < sarr.length; i++ ){
                bigarr[i] = Integer.parseInt(sarr[i]);
            }
            sarr = null; // gc
            long inv = countInversions(bigarr);
            System.out.println(Arrays.toString(bigarr));
            System.out.println(inv);
        } catch (IOException e) {
            System.err.println("IOException");
        }
    }
}
