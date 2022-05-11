package CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static CompetitiveProg.Helpers.*;
import static java.util.stream.Collectors.groupingBy;

public class AnagramPairsSimple {
    
    // anagrams will have same ascii sum of individual chars
    static long asciiSum(String s) {
        long sum = 0;
        for (char c : s.toCharArray()) {
            sum += c;
        }
        return sum;
    }
    
    static long numOfAnagramPairs(String s) {
        // get all substrings, and group them by ascii value
        // thus, all substrings in a group are anagrams
        Map<Long, List<String>> subs = allSubstrings(s).stream().collect(groupingBy(AnagramPairsSimple::asciiSum));
        
        // we have to find anagram pair, this make pairs from each group
        // If a group has N members, then num of anagram pairs == nC2
        // add them up to get total count
        long sum = subs.values()
                .stream()
                .filter(list -> list.size() >= 2)
//                .peek(System.out::println)
                .mapToLong(list -> nC2(list.size()))
                .sum();
        
        return sum;
    }
    
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("Files/in/in/anagram-pair.txt"))) {
            String line = "";
            while ((line = r.readLine()) != null) {
                System.out.println(numOfAnagramPairs(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("error");
        }
    }
}


/*
 Failing for certain large inputs, example:

For input lines:

dbcfibibcheigfccacfegicigcefieeeeegcghggdheichgafhdigffgifidfbeaccadabecbdcgieaffbigffcecahafcafhcdg
dfcaabeaeeabfffcdbbfaffadcacdeeabcadabfdefcfcbbacadaeafcfceeedacbafdebbffcecdbfebdbfdbdecbfbadddbcec
gjjkaaakklheghidclhaaeggllagkmblhdlmihmgkjhkkfcjaekckaafgabfclmgahmdebjekaedhaiikdjmfbmfbdlcafamjbfe
fdbdidhaiqbggqkhdmqhmemgljaphocpaacdohnokfqmlpmiioedpnjhphmjjnjlpihmpodgkmookedkehfaceklbljcjglncfal
bcgdehhbcefeeadchgaheddegbiihehcbbdffiiiifgibhfbchffcaiabbbcceabehhiffggghbafabbaaebgediafabafdicdhg
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
mhmgmbbccbbaffhbncgndbffkjbhmkfncmihhdhcebmchnfacdigflhhbekhfejblegakjjiejeenibemfmkfjbkkmlichlkbnhc
fdacbaeacbdbaaacafdfbbdcefadgfcagdfcgbgeafbfbggdedfbdefdbgbefcgdababafgffedbefdecbaabdaafggceffbacgb
bahdcafcdadbdgagdddcidaaicggcfdbfeeeghiibbdhabdhffddhffcdccfdddhgiceciffhgdibfdacbidgagdadhdceibbbcc
dichcagakdajjhhdhegiifiiggjebejejciaabbifkcbdeigajhgfcfdgekfajbcdifikafkgjjjfefkdbeicgiccgkjheeiefje

The Correct Output is shown as:

1464
2452
873
585
1305
166650
840
2134
1571
1042

But My output is:

4763
10574
3759
2701
4412
166650
3990
6781
6838
5315

The following brute force JS algo gives correct op:

function sherlockAndAnagrams(s) {
    let count = 0;

    // Size of our sliding window
    for (let i = 1; i < s.length; i++) {
        let found = {};
        
        // Starting index of our sliding window
        for (let j = 0; j + i <= s.length; j++) {
            let substr = s.substr(j, i);
            substr = substr.split('').sort().join('');
            if (found[substr]) {
                count += found[substr];
                found[substr]++;
            } else {
                found[substr] = 1;
            }
        }
    }

    return count;
}

 */