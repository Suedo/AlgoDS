package CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static CompetitiveProg.Helpers.show2DArr;


public class LongestCommonSubsequence {
    
    private static final String path = "Files/lcs.txt";
    
    private static void debug(String[] p, String[] q, int n, int m) {
        String op = String.format("P: %s \t\t Q: %s, n: %2d, m: %2d", Arrays.toString(p), Arrays.toString(q), n, m);
        System.out.println(op);
    }
    
    private static String[][] memo;
    
    public static String lcsMemoized(String[] p, String[] q, int pLen, int qLen) {
        String result = "";
        // base case
        if (pLen == 0 || qLen == 0) return "";
        else if (memo[pLen][qLen] != null) return memo[pLen][qLen];
        else if (p[pLen - 1].equals(q[qLen - 1])) {
            // common last character
            result = lcsMemoized(p, q, pLen - 1, qLen - 1) + p[pLen - 1];
        } else {
            String op1 = lcsMemoized(p, q, pLen - 1, qLen);
            String op2 = lcsMemoized(p, q, pLen, qLen - 1);
            if (op2.length() > op1.length()) result = op2;
            else result = op1;
        }
        memo[pLen][qLen] = result;
        return result;
    }
    
    public static void main(String[] args) {
        
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {
            String[] p = r.readLine().trim().split(" +");
            String[] q = r.readLine().trim().split(" +");
            LongestCommonSubsequence LCS = new LongestCommonSubsequence();
            
            memo = new String[p.length + 1][q.length + 1];
            for (int i = 0; i < p.length; i++) {
                for (int j = 0; j < q.length; j++) {
                    memo[i][j] = null;
                }
            }
            
            String lcs = lcsMemoized(p, q, p.length, q.length);
            System.out.println(lcs);
            
        } catch (IOException e) {
            System.err.println("error");
        }
    }
    
    
}
