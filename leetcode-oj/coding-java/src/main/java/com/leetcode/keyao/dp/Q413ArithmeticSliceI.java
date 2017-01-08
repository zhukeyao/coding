package com.leetcode.keyao.dp;

/**
 * Created by keyao on 11/30/16.
 */
public class Q413ArithmeticSliceI {
    public int countSlice(int input[]) {
        int sequenceCount[] = new int[input.length];
        int gaps[] = new int[input.length];

        sequenceCount[0] = 1;
        gaps[0] = Integer.MIN_VALUE;
        for (int i=1; i<input.length; i++) {
            int gap = input[i] - input[i-1];
            if (gap == gaps[i-1]) {
                sequenceCount[i] = sequenceCount[i-1] + 1;
            }
            else
                sequenceCount[i]=2;
            gaps[i] = gap;
        }

        int count = 0;
        for (int i=2; i<input.length; i++) {
            if (sequenceCount[i] < 3) continue;
            count += sequenceCount[i] - 2;
        }
        return count;
    }

    static public void main(String[] arg) {
        Q413ArithmeticSliceI solution = new Q413ArithmeticSliceI();
        int input[] = {1,2,3,4,5};
        int ret = solution.countSlice(input);
        System.out.println("result = " + ret);
    }
}
