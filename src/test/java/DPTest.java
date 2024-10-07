import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class DPTest {

    /**
     * https://leetcode.com/problems/partition-equal-subset-sum/
     */
    public static boolean canPartition(int[] nums) {

        final int sum = IntStream.of(nums).sum();
        final var target = sum / 2;
        log.info("sum is {}, thus we need to find subset for {}", sum, target);
        log.info("{}", sum % 2 != 0 ? "not possible, sum must be even for equal partition" : "possible");

        var memo = new Boolean[nums.length][target + 1];

        var ans = canPartition(nums, target, 0, memo);
        log.info("Subset sum exists: {}", ans);

        return ans;
    }

    private static boolean canPartition(final int[] nums, Integer target, int index, Boolean[][] memo) {
        log.info("target: {}, index: {}, memo: {}", target, index, memo);
        if (target == 0) return true;
        if (target < 0 || index >= nums.length) return false;
        if (memo[index][target] != null) {
            log.info("found in memo[{}]: {}", index, memo[index][target]);
            return memo[index][target];
        }

        var include = canPartition(nums, target - nums[index], index + 1, memo);
        var exclude = canPartition(nums, target, index + 1, memo);

        memo[index][target] = include || exclude;

        return memo[index][target];
    }


    public static List<List<Integer>> equalSubsets(int[] nums) {
        final int sum = IntStream.of(nums).sum();
        if (sum % 2 != 0) {
            log.info("cannot have equal subsets when sum({}) is not even", sum);
            return List.of();
        }

        Map<String, List<List<Integer>>> memo = new HashMap<>();
        var results = equalSubsets(nums, sum / 2, 0, memo);

        log.info("results: {}", results);
        return results;
    }

    /**
     * arr: 2,3,5
     * <p>
     * trigger: arr, 5, 0, {}
     * <p>
     * inc, i=0: arr, 5-2=3, 0+1=1, {}
     * inc, i=1: arr, 3-3=0, 1+1=2, {}
     * i: basecase t=0, return [[]]
     * foreach([[]]) : add arr[1] >> [[3]], {"3,1": [[3]]}
     * foreach([[]]) : add arr[0] >> [[2,3]], {"5,0": [[2,3]]}
     */
    private static List<List<Integer>> equalSubsets(int[] nums, int target, int index, Map<String, List<List<Integer>>> memo) {
        log.info("target: {}, index: {}", target, index);
        if (target == 0) {
            log.info("base found: target: {}, index: {}", target, index);
            List<List<Integer>> base = new ArrayList<>();
            base.add(new ArrayList<>());
            return base;
        }
        if (target < 0 || index >= nums.length) {
            log.info("overshot: target: {}, index: {}", target, index);
            return new ArrayList<>();
        }

        var key = String.format("%s,%s", target, index);
        if (memo.get(key) != null) {
            log.info("MEMOIZED: key: {}, value: {}", key, printList(memo.get(key)));
            return memo.get(key);
        }

        var include = equalSubsets(nums, target - nums[index], index + 1, memo);
        var exclude = equalSubsets(nums, target, index + 1, memo);
        include.forEach(answer -> answer.add(nums[index]));

        log.info("key: {}, include: {}, exclude: {}", key, printList(include), printList(exclude));
        exclude.addAll(include);

        memo.put(key, new ArrayList<>(exclude)); // copy of the combined answers
        return memo.get(key);
    }


    // count subsets
    public int subsetCount(int[] nums, int sum) {
        return subsetCount(nums, sum, 0, new HashMap<>());
    }

    public int subsetCount(int[] nums, int target, int index, Map<String, Integer> memo) {
        var key = String.format("%s,%s", target, index);
        if (target == 0) return 1;
        if (index >= nums.length || target < 0) return 0;
        if (memo.get(key) != null) return memo.get(key);

        var include = subsetCount(nums, target - nums[index], index + 1, memo);
        var exclude = subsetCount(nums, target, index + 1, memo);

        memo.put(key, include + exclude);
        return memo.get(key);
    }

    @Test
    void subsetCOuntTest() {
        System.out.println(subsetCount(new int[]{1, 1, 2, 3}, 4));
    }


    /*
    ^^ the above subset count problem can be used to find a similar problem:
    >> count the subsets with a given difference <<

    how:
        subset1 - subset2 = diff (given)
        subset1 + subset2 = sum of all elems (can be calculated)
        -------------------------------------
        2*subset1 = SumAll - Diff
        thus, subset1 = (SumAll - Diff)/2 <-- modified target
        ^^ we need to find count of subsets with this sum, modified target


     */


    private static String printList(List<List<Integer>> l) {
        return l.stream()
                .map(String::valueOf)
                //.map(each -> each.toString())
                .collect(Collectors.joining(":"));
    }


    // https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/
    // this ONLY WORKS WITH POSITIVE NUMBERS
    public int minimumDifference(int[] nums) {

        var sumAll = IntStream.of(nums).sum();
        if (subsetSum(nums, sumAll / 2, 0, new HashMap<>())) {
            // log.info("array can be equally partitioned");
            return 0;
        }

        var stats = IntStream.of(nums).summaryStatistics();

        // all possible subset sums
        var sortedArray = IntStream.range(stats.getMin(), stats.getMax() + 1)
                .filter(i -> subsetSum(nums, i, 0, new HashMap<>()))
                .toArray();

        var index = Arrays.binarySearch(sortedArray, sumAll / 2);
        var insertionPoint = (index * -1) - 1;
        return sortedArray[insertionPoint] - sortedArray[insertionPoint - 1];

    }


    private boolean subsetSum(int[] nums, int target, int index, Map<String, Boolean> memo) {
        var key = String.format("%s,%s", target, index);
        if (target == 0) return true;
        if (target < 0 || index >= nums.length) return false;
        if (memo.get(key) != null) return memo.get(key);

        var include = subsetSum(nums, target - nums[index], index + 1, memo);
        var exclude = subsetSum(nums, target, index + 1, memo);

        memo.put(key, include || exclude);
        return memo.get(key);
    }


    @Test
    void fun() {
        //equalSubsets(new int[]{1, 2, 3, 4, 5, 6, 7});
        //equalSubsets(new int[]{2, 3, 5});
        //System.out.println(minimumDifference(new int[]{3, 9, 7, 3}));
        //System.out.println(minimumDifference(new int[]{1, 5, 5, 11}));
        System.out.println(minimumDifference(new int[]{2, -1, 0, 4, -2, -9}));
        ;
        // [5,2,3,4], [7,4,3]
    }

    // 0/1 knapsack
    public int knapsackProfit(int maxWeight, int index, int[] profits, int[] weights, Map<String, Integer> memo) {
        if (index == 0 || maxWeight == 0) return 0;
        if (weights[index] > maxWeight) {
            // weight is larger than what the bag can carry, skip it
            knapsackProfit(maxWeight, index - 1, profits, weights, memo);
        }

        var include = knapsackProfit(maxWeight - weights[index], index - 1, profits, weights, memo);
        var exclude = knapsackProfit(maxWeight, index - 1, profits, weights, memo);

        return Math.max(
                profits[index] + include,
                exclude
        );
    }

    public int knapsackProfit(int maxWeight, int n, int[] profits, int[] weights) {

        return knapsackProfit(maxWeight, n - 1, profits, weights, new HashMap<>());
    }

    @Test
    void test01KnapsackProfit() {
        System.out.println(knapsackProfit(3, 4, new int[]{1, 2, 3}, new int[]{4, 5, 1}, new HashMap<>()));
    }


    // unbounded knapsack
    public int coinChange(int[] coins, int target) {
        Map<String, List<List<Integer>>> memo = new HashMap<>();
        var result = coinChange(coins, target, 0, memo);
        if (result.size() == 1 && result.getFirst().size() == 1) return -1;

        return -1; //wip
    }

    public List<List<Integer>> coinChange(int[] coins, int target, int index, Map<String, List<List<Integer>>> memo) {
        var key = String.format("%s,%s", target, index);
        if (target == 0) return List.of(List.of());
        if (index >= coins.length) return List.of();
        if (memo.get(key) != null) return memo.get(key);

        var include = coinChange(coins, target - coins[index], index, memo);
        var exclude = coinChange(coins, target, index + 1, memo);

        include.forEach(integers -> integers.add(coins[index]));
        include.addAll(exclude);

        memo.put(key, include);
        return memo.get(key);
    }


    @Test
    void testBinarySearch() {
        var arr = new int[]{1, 2, 3, 41, 51, 61, 71};
        System.out.println(Arrays.binarySearch(arr, 45)); // -5 == (-insertionPoint - 1), thus insertionPoint == 4
    }
}
