package _2023;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;

@Log4j2
public class CanSum {

    private HashMap<Integer, Boolean> memo = new HashMap<>();

    public Boolean pathPossible(Integer target, List<Integer> options) {
        log.info("stage start: target: {}", target);

        // base cases
        if (target == 0) return true;
        if (target < 0) return false;
        if (memo.containsKey(target)) return memo.get(target);

        boolean pathFound = false;

        for (int i = 0; i < options.size() && !pathFound; i++) {
            Integer ithVal = options.get(i);
            Integer rem = target - ithVal;
            pathFound = pathPossible(rem, options);
            log.info("for target: {} ithVal {}, rem {} pathFound {}",target, ithVal, rem, pathFound);
        }

        log.info("stage end: target: {}, found {}", target, pathFound);
        memo.put(target, pathFound);
        return pathFound;
    }


    public static void main(String[] args) {

        CanSum allSum = new CanSum();
        Boolean possible = allSum.pathPossible(100, List.of(3, 9));
        System.out.println(possible);

    }


}
