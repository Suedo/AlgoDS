package _2023;

import java.math.BigInteger;
import java.util.Arrays;

public class FibonacciTab {

    private static BigInteger[] setup(int num) {
        BigInteger[] arr = new BigInteger[num + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = BigInteger.ZERO; // fill
        }
        arr[1] = BigInteger.ONE;
        return arr;
    }


    public static void go(int num) {
        BigInteger[] arr = setup(num);
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1].add(arr[i - 2]);
        }

        System.out.println(String.format("Fibonacci(%d) is: %d and total sequence is:", num, arr[num]));
        System.out.println(Arrays.toString(arr));


    }


    public static void main(String[] args) {
        FibonacciTab.go(99);
    }
}
