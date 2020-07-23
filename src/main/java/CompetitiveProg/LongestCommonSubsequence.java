package CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class LongestCommonSubsequence {
    
    private static final String path = "Files/lcs.txt";
    
    static void show(int[][] arr) {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                str.append(String.format("%2d ", arr[i][j]));
            }
            str.append("\n");
        }
        System.out.println(str);
    }
    
    static String reverse(String a, String delim) {
        if (delim == null) delim = "";
        String[] arr = a.split("");
        StringBuilder s = new StringBuilder("");
        for (int i = arr.length - 1; i >= 0 ; i--) {
            s.append(arr[i] + delim);
        }
        return s.toString();
    }
    
    private static void debug(String[] p, String[] q, int n, int m) {
        String op = String.format("P: %s \t\t Q: %s, n: %2d, m: %2d", Arrays.toString(p), Arrays.toString(q), n, m);
        System.out.println(op);
    }
    
    private int LCSCount(String[] p, String[] q, int n, int m, int count, int[][] memo) {
        
//        debug(p, q, n, m);
        if (n < 0 || m < 0) return count;   // base case, any one array has been fully traversed, and thus index < 0
        
        int existing = memo[n][m];
        if (existing != -1) return existing;
        
        if (p[n].equals(q[m])) {       // if common ending character
            count = LCSCount(p, q, n - 1, m - 1, count + 1, memo); // LCS with one less character for both
        } else {
            int p0q1 = LCSCount(p, q, n, m - 1, count, memo);
            int p1q0 = LCSCount(p, q, n - 1, m, count, memo);
            count = Math.max(p0q1, p1q0);
        }
        memo[n][m] = count; // memoize
        return count;
    }
    
    private String LCSValue(String[] p, String[] q, int n, int m, String val, String[][] memo) {
        
        // debug(p, q, n, m);
        if (n < 0 || m < 0) return val;   // base case, any one array has been fully traversed, and thus index < 0
        
        String existing = memo[n][m];
        if (existing != null) return existing;
        
        if (p[n].equals(q[m])) {       // if common ending character
            val = LCSValue(p, q, n - 1, m - 1, val + p[n], memo); // LCS with one less character for both
        } else {
            String a = LCSValue(p, q, n, m - 1, val, memo);
            String b = LCSValue(p, q, n - 1, m, val, memo);
            
            if(a.length() > b.length()) val = a;
            else val = b;
        }
        memo[n][m] = val; // memoize
        return val;
    }
    
    public static void main(String[] args) {
        
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {
            String[] p = r.readLine().trim().split(" +");
            String[] q = r.readLine().trim().split(" +");
            LongestCommonSubsequence lcs = new LongestCommonSubsequence();
            
            int[][] countMemo = new int[p.length][q.length];
            String[][] valueMemo = new String[p.length][q.length];
            for (int i = 0; i < p.length; i++) {
                for (int j = 0; j < q.length; j++) {
                    countMemo[i][j] = -1;
                    valueMemo[i][j] = null;
                }
            }
            
            int lcsCount = lcs.LCSCount(p, q, p.length - 1, q.length - 1, 0, countMemo);
            System.out.println(lcsCount);
            // show(countMemo);
            
            String common = lcs.LCSValue(p, q, p.length - 1, q.length - 1, "", valueMemo);
            System.out.println(reverse(common, " "));
        } catch (IOException e) {
            System.err.println("error");
        }
    }
    
    
}
