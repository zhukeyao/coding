package com.leetcode.keyao.array;

/**
 * Created by keyao on 11/18/16.
 * Given a array with size N, which store number 1 .. N, there is at a duplicate number in the array.
 * Find the duplicated number;
 *
 * Algo1:  Think the value in the array as pointer pointing to the next array node. This problem can be think of
 *         finding the joint point of a link-list containing loop.  We can start from node 0 and use a faster pointer and
 *         a slow pointer to find the joint node. And its value should be the pointer's value.  Note that, since there will be
 *         two pointer point to the joint point, that means there will be a duplicated value
 *
 *         This algorithm is usally when the array is ready only
 *
 *
 * Algo 2: For each array value, flip its corresponding array node to denote that value has been see before. For example,
 *         If we see "1", we will make input[0] = -1*input[0]. Then next time when we see 1 again, if we find input[0] is negtive,
 *         that means 1 has shown up.
 *
 * Algo 3: move input[i]'s value x to input[x], so that each cell store the same value as its index;
 *         During moving, when we find the input[x] already existed at cell x, the duplicated element is found
 *
 */
public class Q287FindDupNumber {
    public int findDup1(final int[] input) {
        if (input.length <=1) return 0;
        int slowPointer = 0;
        int fastPointer = 0;
        while (true) {
            slowPointer = input[slowPointer];
            fastPointer  = input[input[fastPointer]];
            if (slowPointer == fastPointer) break;
        }
        fastPointer = 0;
        while (true) {
            slowPointer = input[slowPointer];
            fastPointer  = input[fastPointer];
            if (slowPointer == fastPointer) break;
        }
        return slowPointer;
    }

    public int findDup2(int[] input) {
        for (int i=0; i< input.length; i++ ) {
            int index = Math.abs(input[i]);
            if (input[index-1] < 0) {
                return index;
            }
            input[index-1] = -1 * input[index-1];
        }
        return 0;
    }

    public int findDup3(int[] input) {
        for (int i=0; i < input.length; i++) {
            int j = input[i];
            if (j == i) continue;
            if (input[j] == j) return j;
            while (j != input[j]) {
                int tmp = input[j];
                if (tmp == input[tmp]) {
                    return tmp;
                }
                input[j] = j;
                j = tmp;
            }
        }
        return 0;
    }

    static public void main(String[] arg) {
        int input[] = {1,2,3,1};
        Q287FindDupNumber solution = new Q287FindDupNumber();
        int dup = solution.findDup1(input);
        System.out.println(dup);

        int input2[] = {1,2,3,1};
        int dup2 = solution.findDup2(input2);
        System.out.println(dup2);

        int input3[] = {1,2,3,1};
        int dup3 = solution.findDup3(input3);
        System.out.println(dup3);


    }
}
