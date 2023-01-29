package _2023.misc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LongestSubstringWithoutRepeat {


    public int findAllSublists(String s) {

        if (s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        List<String> sublists = new ArrayList<>();
        HashSet<Character> set = null;

        for (int i = 0; i < chars.length; i++) {
            set = new HashSet<>();
            set.add(chars[i]);
            int end = i + 1;
            while (end < chars.length && set.add(chars[end])) {
                end++;
            }
            sublists.add(s.substring(i, end));
            // i = end - 1; // "dvdf" > expected : 3, because vdf. keeping this will give 2, because ["dv", "df"]
        }

        System.out.println(sublists);
        return sublists.stream().mapToInt(each -> each.length()).max().getAsInt(); // max sublist length


    }

    // fares poorly
    public int lengthOfLongestSubstring(String s) {

        if (s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        HashSet<Character> set = null;
        int maxLen = 0;

        for (int i = 0; i < chars.length; i++) {
            set = new HashSet<>();
            set.add(chars[i]);
            int end = i + 1;
            while (end < chars.length && set.add(chars[end])) {
                end++;
            }
            maxLen = Math.max(maxLen, end - i);
        }

        // System.out.println(sublists);
        return maxLen;


    }

    // not mine, from leetcode fastest answer
    public int lengthOfLongestSubstring_best(String s) {
        int ans = 0;
        int n = s.length();

        //This variable stores the starting index of the current substring. Initially, it is set to 0.
        int window = 0;

        /*
        This array stores the last index at which each character (from ASCII value 32 to 127) was seen.
        Initially,all the values in the array are set to 0.
        */
        int[] idx = new int[96];
        for (int i = 0; i < n; i++) {
            /* Converts the current character to its ASCII value, subtracts 32 to shift the range to 0-95,
            and assigns the value to variable c.*/
            int c = s.charAt(i) - 32;

            /*
            Updates the starting index of the current substring. If the current character has been
            seen before, the starting index is set to the last index at which that character was seen.
            If the current character has not been seen before, the starting index remains the same.
            */
            window = Math.max(idx[c], window);

            /**
             Updates the length of the longest substring without repeating
             characters that we have seen so far. The current substring is from window
             to i+1, so the length of current substring is i-window+1, ans is set to the
             max of the current length and the previous one.
             */
            ans = Math.max(ans, i - window + 1);
            idx[c] = i + 1;
        }
        return ans;
    }


    //    public static void main(String[] args) {
    //        LongestSubstringWithoutRepeat sub = new LongestSubstringWithoutRepeat();
    //        int max = sub.lengthOfLongestSubstring("pwwkew");
    //        System.out.println(max);
    //        System.out.println(sub.list);
    //    }

    @ParameterizedTest
    @CsvSource({"aab", "abcd", "abcabca", "abcabcd", "pwwkew", "dvdf"})
    void test(String str) {
        LongestSubstringWithoutRepeat sub = new LongestSubstringWithoutRepeat();
        System.out.println(String.format("---------------- %s ----------------", str));
        int max = sub.findAllSublists(str);
        System.out.println(max);
    }
}
