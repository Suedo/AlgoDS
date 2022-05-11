package _2020.CompetitiveProg;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Helpers {
    
    /**
     * Prints a 2D array
     *
     * @param arr array to print
     */
    static void show2DArr(int[][] arr) {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                str.append(String.format("%2d ", arr[i][j]));
            }
            str.append("\n");
        }
        System.out.println(str);
    }
    
    static void show2DArr(String[][] arr) {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                str.append(String.format("%8s ", arr[i][j]));
            }
            str.append("\n");
        }
        System.out.println(str);
    }
    
    /**
     * Reverses a given string, padding it with delim if provided.
     *
     * @param a     string to reverse
     * @param delim optional delim, if not needed, give null
     * @return reversed string
     */
    static String reverse(String a, String delim) {
        if (delim == null) delim = "";
        String[] arr = a.split("");
        StringBuilder s = new StringBuilder("");
        for (int i = arr.length - 1; i >= 0; i--) {
            s.append(arr[i] + delim);
        }
        return s.toString().trim();
    }
    
    /**
     * converts a given string array to int array. helpful for annoying HackerRank apis
     *
     * @param a string array to convert
     * @return int array of input
     */
    static int[] convertToIntArr(String[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = Integer.parseInt(a[i]);
        }
        return b;
    }
    
    static String[] convertToStringArr(int[] a) {
        String[] b = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = String.valueOf(a[i]);
        }
        return b;
    }
    
    static String[][] get2DStringArr(int m, int n) {
        String[][] memo = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = "";
            }
        }
        return memo;
    }
    
    /**
     * Check if s2 is a substring of s1
     *
     * @param s1 String one
     * @param s2 String two
     * @return true if s2 is a substring of s1, false otherwise
     */
    static boolean isSubstring(String s1, String s2) {
        return s1.indexOf(s2, 0) > 0;
    }
    
    /**
     * Get all substrings of a given string;
     *
     * @param s
     * @return
     */
    static ArrayList<String> allSubstrings(String s) {
        ArrayList<String> l = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                l.add(s.substring(i, j + 1));
            }
        }
        return l;
    }
    
    /**
     * Factorial of input number
     *
     * @param n int
     * @return
     */
    static BigDecimal factorial(int n) {
        // return LongStream.rangeClosed(1, n).reduce(1, (long x, long y) -> x * y);
        
        // the above method cannot be applied with BigDecimal, as it gives error
        // "BigDecimal cannot be converted to long"
        // so, the basic way
        BigDecimal temp = BigDecimal.ONE;
        for (long i = 1; i <= n; i++) {
            temp = temp.multiply(BigDecimal.valueOf(i));
        }
        return temp;
    }
    
    static long nCr(int n, int r) {
        return (factorial(n).divide(factorial(r).multiply(factorial(n - r)))).longValue();
    }
    
    static long nC2(int n) {
        return (n * (n-1))/2;
    }
    
    public static void main(String[] args) {
        System.out.println(factorial(93));
    }
    
    
}
