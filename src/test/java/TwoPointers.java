import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class TwoPointers {

    @Test
    void longestSubstringWithoutRepeat() {
        var str = "GEEKSFORGEEKS";
        int i = 0;
        var substrings = new HashSet<String>();

        while (i < str.length()) {
            int j = substringWithoutRepeat(str, i);
            final String substr = str.substring(i, j);
            System.out.println(String.format("%d, %d: %s", i, j, substr));
            substrings.add(substr);
            i++;
        }

        System.out.println(substrings);
        System.out.println("Longest substring length: "
                + substrings.stream().mapToInt(String::length).max().orElse(-1));
    }

    private int substringWithoutRepeat(String s, int i) {
        var set = new HashSet<Character>();
        while (i < s.length() && set.add(s.charAt(i))) { // primitives are passed by value
            i++;
        }
        return i;
    }

    public <T> int removeDupesInplace(T[] arr) {
        int i = 0, j = 1;

        while (true) {
            while (j < arr.length && arr[i].equals(arr[j])) {
                j++; // increase j till it finds the next element != i
            }
            if (j < arr.length) arr[++i] = arr[j];
            else break;
        }
        System.out.println(String.format("%d, %d, %s", i, j, Arrays.toString(arr)));
        return i + 1; // num of unique elems, i starts with 0
    }

    @Test
    void removeDuplicates() {
        var arr = new String[]{"E", "E", "E", "E", "F", "G", "G", "K", "K", "O", "R", "S", "S"}; // geeksforgeeks
        var numarr = new Integer[]{1, 1, 2};
        //removeDupesInplace(arr);
        System.out.println(removeDupesInplace(numarr));
        ;
    }

    @Test
    void getFirstNonRepeat() {
        var s = "racecars"; // e
        HashMap<String, Integer> map = new HashMap<>();
        for (String each : s.split("")) {
            map.put(each, map.getOrDefault(each, 0) + 1);
        }
        for (String each : s.split("")) {
            if (map.get(each) == 1) {
                System.out.println(each);
                break;
            }
        }
    }


    @Test
    void test() {
        var arr = "GEEKSFORGEEKS".split("");
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
