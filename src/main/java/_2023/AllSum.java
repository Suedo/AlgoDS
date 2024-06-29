package _2023;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class AllSum {

    private HashMap<Integer, List<String>> memo = new HashMap<>();
    private final String delim = ">";

    public List<String> allPaths(Integer target, List<Integer> options) {
        log.info("stage start: target: {}", target);

        // base cases
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;
        if (memo.containsKey(target)) return memo.get(target);

        List<String> allSubPaths = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            Integer ithVal = options.get(i);
            Integer rem = target - ithVal;
            List<String> subPaths = allPaths(rem, options);

            log.info("FOR start: ithVal: {}, rem: {}, subpaths: {}", ithVal, rem, subPaths);

            if (subPaths == null) {
                // nothing to do
            } else if (subPaths.isEmpty()) {
                // add empty list appended with ithVal
                allSubPaths.add(String.valueOf(ithVal));
            } else {
                // many paths obtained
                // take each, append ithVal to it
                // take the updated path, add to total paths list

                for (String eachPath : subPaths) {
                    String[] eachPathUpdated = ArrayUtils.add(eachPath.split(delim), String.valueOf(ithVal));
                    allSubPaths.add(StringUtils.join(eachPathUpdated, delim));
                }
            }
        }
        log.info("Stage end: target: {}, paths: {}", target, allSubPaths);
        if (allSubPaths.size() > 0) {
            memo.put(target, allSubPaths);
            return allSubPaths;
        } else return null;
    }


    public static void main(String[] args) {

        AllSum allSum = new AllSum();
        allSum.allPaths(8, List.of(2, 4, 5)); // [2>2>2>2, 4>2>2, 2>4>2, 2>2>4, 4>4]

    }


}
