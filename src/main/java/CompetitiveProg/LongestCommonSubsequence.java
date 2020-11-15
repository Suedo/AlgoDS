package CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static CompetitiveProg.Helpers.get2DStringArr;
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
    
    private static String longer(String a, String b) {
        if (a.length() > b.length()) return a;
        return b;
    }
    
    public static String lcsBottomUp(String[] p, String[] q, int pLen, int qLen) {
        String[][] lookup = get2DStringArr(pLen + 1, qLen + 1);
        for (int i = 0; i <= pLen; i++) {
            for (int j = 0; j <= qLen; j++) {
                if (i == 0 || j == 0) lookup[i][j] = "";
                else if (p[i - 1].equals(q[j - 1])) {
                    // this step creates value, others are based off this value
                    lookup[i][j] = lookup[i - 1][j - 1] + p[i - 1];
                }
                else lookup[i][j] = longer(lookup[i - 1][j], lookup[i][j - 1]);
            }
        }
        show2DArr(lookup);
        return lookup[pLen][qLen];
    }
    
    public static void main(String[] args) {
        
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {
            String[] p = r.readLine().trim().split(" +");
            String[] q = r.readLine().trim().split(" +");
            LongestCommonSubsequence LCS = new LongestCommonSubsequence();
            
            memo = get2DStringArr(p.length, q.length);
            String lcs = lcsBottomUp(p, q, p.length, q.length);
            System.out.println(lcs);
            //            show2DArr(memo);
            
        } catch (IOException e) {
            System.err.println("error");
        }
    }
}

/*
3 9 8 3 9 7 9 7 0
3 3 9 9 9 1 7 2 0 6

lcsMemoized:

399970
    null     null     null     null     null     null     null     null     null     null     null
    null        3        3        3        3        3        3        3        3        3        3
    null        3        3       39       39       39       39       39       39       39       39
    null        3        3       39       39       39       39       39       39       39       39
    null        3       33       39       39       39       39       39       39       39       39
    null        3       33      339      399      399      399      399      399      399      399
    null        3       33      339      399      399      399     3997     3997     3997     3997
    null     null     null     null     null     3999     3999     3997     3997     3997     3997
    null     null     null     null     null     null     null    39997    39997    39997    39997
    null     null     null     null     null     null     null     null     null   399970   399970
    
    
lcsBottomUp:

                
                3        3        3        3        3        3        3        3        3        3
                3        3       39       39       39       39       39       39       39       39
                3        3       39       39       39       39       39       39       39       39
                3       33       33       33       33       33       33       33       33       33
                3       33      339      339      339      339      339      339      339      339
                3       33      339      339      339      339     3397     3397     3397     3397
                3       33      339     3399     3399     3399     3399     3399     3399     3399
                3       33      339     3399     3399     3399    33997    33997    33997    33997
                3       33      339     3399     3399     3399    33997    33997   339970   339970

339970
 */
