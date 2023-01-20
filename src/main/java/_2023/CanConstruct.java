package _2023;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CanConstruct {

    HashMap<String, Boolean> memo = new HashMap<>();

    private List<String> findPrefixes(String target, List<String> options) {
        return options.stream().filter(target::startsWith).collect(Collectors.toList());
    }

    public Boolean findAllCombinations(String target, List<String> chunks) {
        log.info("stage start: {}", target);

        // base cases
        if (target == null) return false;
        if (target.length() == 0) return true;
        if (memo.containsKey(target)) return memo.get(target);


        List<String> prefixes = findPrefixes(target, chunks);
        log.info("prefixes: {}", prefixes);
        ArrayList<String> results = new ArrayList<>();

        boolean combinationFound = false;
        for (String prefix : prefixes) {
            String remainder = target.substring(prefix.length());
            combinationFound = findAllCombinations(remainder, chunks);
            log.info("FOR start: prefix: {}, rem: {}, found: {}", prefix, remainder, combinationFound);

            if (combinationFound) break;
        }

        memo.put(target, combinationFound);
        return combinationFound;
    }


    public static void main(String[] args) {

        CanConstruct allConstruct = new CanConstruct();
//        allConstruct.findAllCombinations("abcdef", List.of("ab", "c", "abc", "cd", "ef", "def", "abcd"));
        allConstruct.findAllCombinations("abcdef", List.of("x", "fg", "ab", "f", "fe", "cd", "e"));
    }

}
