package CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static CompetitiveProg.Helpers.allSubstrings;
import static CompetitiveProg.Helpers.nCr;
import static java.util.stream.Collectors.groupingBy;

public class AnagramPairsSimple {
    
    // anagrams will have same ascii sum of individual chars
    static int asciiSum(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            sum += c;
        }
        return sum;
    }
    
    static int numOfAnagramPairs(String s) {
        // get all substrings, and group them by ascii value
        // thus, all substrings in a group are anagrams
        Map<Integer, List<String>> subs = allSubstrings(s).stream().collect(groupingBy(AnagramPairsSimple::asciiSum));
        
        // we have to find anagram pair, this make pairs from each group
        // If a group has N members, then num of anagram pairs == nC2
        // add them up to get total count
        long sum = subs.values()
                .stream()
                .filter(list -> list.size() >= 2)
//                .peek(System.out::println)
                .mapToLong(list -> nCr(list.size(), 2))
                .sum();
        
        return (int) sum;
    }
    
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("Files/anagram-pair.txt"))) {
            String line = "";
            while ((line = r.readLine()) != null) {
                System.out.println(numOfAnagramPairs(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("error");
        }
    }
}
