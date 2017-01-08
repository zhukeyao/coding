package com.leetcode.keyao.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keyao on 11/18/16.
 *
 * Note: Given item i, the key to to pre-compute forward product [0..i-1] (f[i-1]),
 *       and backward product [i+1 .. n-1] (b[i+1])
 *       result = f[i-1] * b[i+1]
 */
public class Q238ProductArray {
    List<Integer> productExceptSelf(int[] input) {
        List<Integer> result = new ArrayList<>();
        if (input.length <= 1)  return result;
        if (input.length == 2) {
            result.add(input[1]);
            result.add(input[0]);
            return result;
        }

        int[] b = new int[input.length];
        int[] f = new int[input.length];
        for (int i=0; i<input.length; i++) {
            b[i] = 1;
            f[i]= 1;
        }
        b[input.length-1] = input[input.length-1];
        for (int i = input.length - 2; i>=0; i--) {
            b[i] = b[i+1] * input[i];
        }
        f[0] = input[0];
        for (int i = 1; i<input.length; i++) {
            f[i] = f[i-1] * input[i];
        }
        result.add(b[1]);
        for (int i=1; i<input.length-1;i++) {
            result.add(f[i-1]*b[i+1]);
        }
        result.add(f[input.length-2]);
        return result;
    }

    public static void main(String[] arg) {
        Q238ProductArray solution = new Q238ProductArray();
        int input[] = {1,2,3,4};
        List<Integer> result = solution.productExceptSelf(input);
        System.out.println(result);
    }
}
