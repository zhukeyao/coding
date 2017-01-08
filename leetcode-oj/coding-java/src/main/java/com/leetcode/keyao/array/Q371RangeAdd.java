package com.leetcode.keyao.array;

import java.util.List;

/**
 * Created by keyao on 11/18/16.
 * Assume you have an array of length n initialized with all 0's and are given k update operations.

 Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

 Return the modified array after all k operations were executed.

 Example:

 Given:

 length = 5,
 updates = [
 [1,  3,  2],
 [2,  4,  3],
 [0,  2, -2]
 ]

 Output:

 [-2, 0, 3, 5, 3]
 Explanation:

 Initial state:
 [ 0, 0, 0, 0, 0 ]

 After applying operation [1, 3, 2]:
 [ 0, 2, 2, 2, 0 ]

 After applying operation [2, 4, 3]:
 [ 0, 2, 5, 5, 3 ]

 After applying operation [0, 2, -2]:
 [-2, 0, 3, 5, 3 ]
 Hint:

 Thinking of using advanced data structures? You are thinking it too complicated.
 For each update operation, do you really need to update all elements between i and j?
 Update only the first and end element is sufficient.
 The optimal time complexity is O(k + n) and uses O(1) extra spa

 Note:  update the start to be op_value and end to be -1 * op_value. After updating all ops, sum up each element's previous elements value.
        Need special handle first start=end case

 */
public class Q371RangeAdd {
    public void rangeAdd(int[] input, List<List<Integer> > ops) {
        for (List<Integer> op : ops) {
            int start = op.get(0);
            int end = op.get(1);
            int num = op.get(2);
            if (start==end) continue;
            input[start] += num;
            if (end+1 < input.length)
                input[end+1] += -1 * num;
        }

        for (int i=1; i<input.length; i++) {
            input[i] += input[i-1];
        }

        for (List<Integer> op : ops) {
            int start = op.get(0);
            int end = op.get(1);
            int num = op.get(2);
            if (start==end) input[start] +=num;
        }

    }
}
