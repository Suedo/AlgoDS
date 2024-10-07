import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class UnboundedKnapsacks {

    // Maximum sum
    public static int rodCutDP(int[] prices, int bagSize, Map<Integer, Integer> memo) {
        if (bagSize < 0) return 0; // knapsack is full
        if (memo.get(bagSize) != null) {
            return memo.get(bagSize);
        }

        var updatedProfit = 0;
        for (int rodLenth = 1; rodLenth <= bagSize; rodLenth++) {
            updatedProfit = Math.max(updatedProfit, prices[rodLenth - 1] + rodCutDP(prices, bagSize - rodLenth, memo));
        }
        memo.put(bagSize, updatedProfit);
        return memo.get(bagSize);
    }

    // Coin Change – Count Ways to Make Sum
    public static int coinChangeCount(int[] coins, int n, int targetSum, Map<String, Integer> memo) {
        var key = String.format("%s,%s", targetSum, n);
        if (targetSum == 0) return 1;
        if (targetSum < 0 || n < 1) return 0;
        if (memo.get(key) != null) return memo.get(key);

        var include = coinChangeCount(coins, n, targetSum - coins[n - 1], memo); // can use this again, so no decrement n
        var exclude = coinChangeCount(coins, n - 1, targetSum, memo); // excluded means wont be used again, so n is decremented

        memo.put(key, include + exclude);
        return memo.get(key);
    }

    // Coin Change – Minimum Coins to Make Sum
    public static int coinMinimumCount(int[] coins, int sum) {
        final int res = coinMinimumCount(coins, sum, new HashMap<>());
        return res == Integer.MAX_VALUE
                ? -1 // no valid combinations exist
                : res;
    }

    public static int coinMinimumCount(int[] coins, int sum, Map<Integer, Integer> memo) {
        if (sum == 0) return 0; // target achieved, no more coins needed
        if (sum < 0) return Integer.MAX_VALUE; // invalid, overshot
        if (memo.get(sum) != null) return memo.get(sum);


        var min = Integer.MAX_VALUE;
        for (int coin : coins) {
            // adding 1 indicates inclusion, similar to adding prices[i] in an earlier problem
            // we add 1 because we are not concerned with the value of the coin, but just that the coin was used
            var res = coinMinimumCount(coins, sum - coin, memo);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + res);
            }
        }
        memo.put(sum, min);
        return memo.get(sum);
    }

    @Test
    void minCoinToMakeSum() {
        System.out.println(coinMinimumCount(new int[]{4, 6, 2}, 5));
        //System.out.println(coinMinimumCount(new int[]{25, 10, 5}, 30, new HashMap<>()));
        //System.out.println(coinMinimumCount(new int[]{9, 6, 5, 1}, 19, new HashMap<>()));
        //System.out.println(coinMinimumCount(new int[]{1, 4, 10, 5, 7, 19}, 19, new HashMap<>()));
    }

    @Test
    void coinChange() {
        //System.out.println(coinChangeCount(new int[]{1, 2, 3}, 3, 5, new HashMap<>())); // 5
        System.out.println(coinChangeCount(new int[]{2, 5, 3, 6}, 4, 10, new HashMap<>())); // 5
    }

    @Test
    void rodCut() {
        //System.out.println(rodCutDP(new int[]{1, 3, 5}, 3, new HashMap<>()));
        System.out.println(rodCutDP(new int[]{1, 3, 4, 5, 7, 9, 10, 11}, 8, new HashMap<>()));
    }
}
