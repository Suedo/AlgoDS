import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
    Prefix Sum Algorithms are useful when you need to compute sums over many different subarrays
    or perform multiple queries on different subarray ranges.
    They are very powerful when combined with other techniques (like hashing) for more complex problems,
    such as counting subarrays with specific properties (e.g., sum equals k, sum divisible by k).
 */
public class PrefixSum {

    // range sum query
    public int getSumBetweenRange(Integer[] prefixSum, int start, int end) {
        var sum = start == 0 ? prefixSum[end] : prefixSum[end] - prefixSum[start - 1];
        // System.out.println(sum);
        return sum;
        // start is inclusive, so -1 is needed to also include start-th elem
    }


    @Test
    void test_RangeSum() {
        // [-2, 0, 3, -5, 2, -1]
        var arr = new Integer[]{-2, 0, 3, -5, 2, -1};
        var prefixSum = new Integer[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        System.out.println(Arrays.asList(prefixSum));
        getSumBetweenRange(prefixSum, 0, 2);
        getSumBetweenRange(prefixSum, 2, 5);
        getSumBetweenRange(prefixSum, 0, 5);
    }

    @Test
    void subarraySum() {
        // https://www.geeksforgeeks.org/number-subarrays-sum-exactly-equal-k/
        // https://leetcode.com/problems/subarray-sum-equals-k/description/
        var arr = new Integer[]{10, 2, -2, -20, 10};
        var k = -10;

        var prefixSum = new Integer[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        record Range(int start, int end) {
            @Override
            public String toString() {
                return String.format("[%s,%s]", this.start, this.end);
            }
        }

        // two pointer + hashmap
        var map = new HashMap<Integer, Set<Range>>();

        int i = 0, j = 0;
        // window opening
        while (j < arr.length)
            map.computeIfAbsent(getSumBetweenRange(prefixSum, i, j), any -> new HashSet<>()).add(new Range(i, j++));
        j--; // offset the extra +1 at the end, to stay in bounds
        i++; // we have already covered the range i=0 to j=arr.length

        // window closing
        while (i < arr.length)
            map.computeIfAbsent(getSumBetweenRange(prefixSum, i, j), any -> new HashSet<>()).add(new Range(i++, j));


        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }


}
