package _2023.DP;

import java.util.Arrays;

public class HouseRobber2 {

    // https://leetcode.com/problems/house-robber-ii/
    public int rob(int[] nums) {
        int[] numsMinus1 = new int[nums.length - 1];
        // [0, n-1]
        System.arraycopy(nums, 0, numsMinus1, 0, numsMinus1.length);
        System.out.println(String.format("[0 -> n-1]: %s", Arrays.toString(numsMinus1)));
        int rob1 = houseRobberHelper(numsMinus1);
        // [1, n]
        System.arraycopy(nums, 1, numsMinus1, 0, numsMinus1.length);
        System.out.println(String.format("[1 -> n]: %s", Arrays.toString(numsMinus1)));
        int rob2 = houseRobberHelper(numsMinus1);
        System.out.println(String.format("[0]: %d, rob1: %d, rob2: %d", nums[0], rob1, rob2));
        return Math.max(nums[0], Math.max(rob1, rob2));
    }

    // this is also the solution for house robber 1
    public int houseRobberHelper(int[] nums) {
        int rob1back = 0, rob2back = 0;

        for (int i = 0; i < nums.length; i++) {
            int curr = Math.max(rob2back + nums[i], rob1back);

            rob2back = rob1back;
            rob1back = curr;
        }

        return rob1back;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 5};
        HouseRobber2 hr2 = new HouseRobber2();
        System.out.println(hr2.rob(arr));
    }
}
