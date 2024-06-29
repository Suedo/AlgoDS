package _2023;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class AllConstruct {

    HashMap<String, List<String>> memo = new HashMap<>();
    String delim = ">>";

    private List<String> findPrefixes(String target, List<String> options) {
        return options.stream().filter(target::startsWith).collect(Collectors.toList());
    }

    private String growByOne(String path, String toAdd) {
//        String[] split = path.split(delim);
//        String[] grow = new String[split.length + 1];
//        System.arraycopy(split, 0, grow, 0, split.length);
//        grow[split.length] = toAdd;
//        return String.join(delim, grow);

        StringBuilder builder = new StringBuilder("");
        for (String each : path.split(delim)) {
            builder.append(each);
            builder.append(delim);
        }
        builder.append(toAdd);
        return builder.toString();
    }

    public List<String> findAllCombinations(String target, List<String> chunks) {
        log.info("stage start: {}", target);

        // base cases
        if (target == null) return null;
        if (target.length() == 0) return new ArrayList<>();
        if (memo.containsKey(target)) return memo.get(target);


        List<String> prefixes = findPrefixes(target, chunks);
        log.info("prefixes: {}", prefixes);
        ArrayList<String> results = new ArrayList<>();

        for (String prefix : prefixes) {
            String remainder = target.substring(prefix.length());
            List<String> subcombinations = findAllCombinations(remainder, chunks);
            log.info("FOR start: prefix: {}, rem: {}, subpaths: {}", prefix, remainder, subcombinations);

            if (subcombinations == null) {
                // do nothing
            } else if (subcombinations.size() == 0) {
                results.add(prefix);
            } else {
                for (String path : subcombinations) {
                    results.add(growByOne(path, prefix));
                }
            }
            log.info("FOR end: prefix: {}, results: {}", prefix, results);
        }

        if (results.size() > 0) {
            memo.put(target, results);
            log.info("stage end: target {}, results: {}", target, results);
            return results;
        } else return null;
    }


    public static void main(String[] args) {

        AllConstruct allConstruct = new AllConstruct();
        List<String> finalList = allConstruct.findAllCombinations("abcdef", List.of("ab", "c", "abc", "cd", "ef", "def", "abcd"));
        System.out.println(finalList);
    }

}
