package _2020.CompetitiveProg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

// https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
public class RansomNote {
    
    private final static String path = "Files/in/in/ransome-note.txt";
    
    private static HashMap<String, Integer> magMap = new HashMap<>();
    
    static void checkMagazine(String[] magazine, String[] note) {
        Arrays.stream(magazine).forEach(word -> {
            word = word.trim();
            Integer val = magMap.putIfAbsent(word, 1);
            if (val != null) magMap.put(word, val + 1);
        });
        
        for (String noteWord : note) {
            noteWord = noteWord.trim();
            if (!magMap.containsKey(noteWord)) {
                System.out.println("No");
                return;
            }
            Integer count = magMap.get(noteWord); // use the word in note
            if (count > 1) magMap.put(noteWord, count - 1);
            else magMap.remove(noteWord); // remove word when all copies used up
        }
        System.out.println("Yes");
    }
    
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {
            String[] sizes = r.readLine().split(" +");
            String[] mag = r.readLine().split(" +");
            String[] note = r.readLine().split(" +");
            checkMagazine(mag, note);
        } catch (IOException e) {
            System.err.println("Error");
        }
    }
}
