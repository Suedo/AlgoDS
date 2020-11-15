package CompetitiveProg;


import java.util.*;

import static CompetitiveProg.Helpers.allSubstrings;
import static java.util.stream.Collectors.groupingBy;

class Anagram{
    private String value;
    
    public Anagram(String value) {
        this.value = value;
    }
    
    private int hash() {
        int hash = 0;
        for(int c : value.toCharArray()){
            hash += c;
        }
        return hash;
    }
    
    public int compareTo(Anagram other) {
        return hash() - other.hash();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anagram)) return false;
        Anagram other = (Anagram) o;
        return other.hashCode() == hashCode();
    }
    
    @Override
    public int hashCode() {
        return hash();
    }
    
    @Override
    public String toString() {
        return value;
    }
}

public class AnagramPairs {
    
    static Map<Integer, List<Anagram>> anagramPairs(String s) {
        Map<Integer, List<Anagram>> aMap = allSubstrings(s)
                .parallelStream()
                .map(Anagram::new)
                //                .collect(Collectors.toCollection(ArrayList::new))
                //                .stream()
                .collect(groupingBy(Anagram::hashCode));
        return aMap;
    }
    
    static int numOfAnagramPairs(String s) {
        Map<Integer, List<Anagram>> amap = anagramPairs(s);
        // print all the anagrams by their hashcode, this also gives us the anagram pairs
        amap.forEach((k,v) -> System.out.println(String.format("Key: %5s, value: %20s",k,v)));
    
        long pairCount = amap.values()
                .stream()
//                .filter(anagrams -> anagrams.size() >= 2)
                .peek(System.out::println)
                .count();
        
        return (int)pairCount;
    }
    
    public static void main(String[] args) {
        System.out.println(numOfAnagramPairs("kkkk"));
    }
    
}

/*
Anagrams for string "abba", grouped by hashcode. We obtain anagram pairs from here like [a, a], [abb, bba] etc

Key:    97, value:               [a, a]
Key:    98, value:               [b, b]
Key:   195, value:             [ab, ba]
Key:   196, value:                 [bb]
Key:   293, value:           [abb, bba]
Key:   390, value:               [abba]

Pairs:
[a, a]
[b, b]
[ab, ba]
[abb, bba]

Final answer:
4
 */
