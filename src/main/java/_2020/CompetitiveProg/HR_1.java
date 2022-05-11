package _2020.CompetitiveProg;

import java.util.ArrayList;
import java.util.Arrays;

// https://www.hackerrank.com/challenges/java-loops/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
public class HR_1 {

    public static void process(int a, int b, int n) {

        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            ArrayList<Double> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                list.add((Math.pow(2, j) * b));
            }
            double sum = list.stream().mapToDouble(Double::doubleValue).sum();
            arr[i] = a + sum;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        HR_1.process(0, 2, 10);
        HR_1.process(5, 3, 5);
    }
}
