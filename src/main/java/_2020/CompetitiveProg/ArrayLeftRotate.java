package _2020.CompetitiveProg;

import java.util.Arrays;

public class ArrayLeftRotate {

    // rotate array a to the left by `d` places
    static int[] rotLeft(int[] a, int d) {
        int[] aux = new int[a.length];
        // rotating left by d, means start of array shifts to right by d positions
        for (int i = d, j = 0; i < a.length; i++, j++) {
            aux[j] = a[i];
        }
        // add rotated elements to the right end
        int startOfRotatedElems = a.length - d;
        for (int i = 0, j = startOfRotatedElems; i < d && j < a.length; i++, j++) {
            aux[j] = a[i];
        }
        return aux;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            int[] op = rotLeft(arr, i);
            System.out.println(Arrays.toString(op));
        }
    }
}

/*
op:

[1, 2, 3, 4, 5] // no rotation, or, rotation by 0 elems
[2, 3, 4, 5, 1]
[3, 4, 5, 1, 2]
[4, 5, 1, 2, 3]
[5, 1, 2, 3, 4]

 */
