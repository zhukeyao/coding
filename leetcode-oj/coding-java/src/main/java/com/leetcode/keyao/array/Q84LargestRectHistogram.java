package com.leetcode.keyao.array;

import java.util.LinkedList;

/**
 * Created by keyao on 11/11/16.
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1,  find the area of largest rectangle in the histogram.
 *
 * Note:
 *   1. key idea is the find the left bound and right bound of a given index "i"
 *   2. Both left bound and right bound should be smaller than "height[i]"
 *   3. Then we use a stack to keep track i and its left bound, new incoming index will be
 *      compared with stack.top() to determine if it's stack'top's right bound
 *   4. when i's left bound and right bound is find, we do not keep i in the stack anymore.
 *   5. new item will be pushed into the stack after all higher element before it has been popped
 *      from stack and the stack top is its left bound
 *   6. if the input is in acending sorted order, that means every element's right bound is input.length
 */
public class Q84LargestRectHistogram {
    static public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int maxArea = 0;
        int i;
        for (i=0; i<heights.length; i++) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                 //stack top element will be i's left bound
                stack.push(i);
                continue;
            }

            // This loop is the key: i is stack.peek's right bound.
            // keep pop up item if i is their's right bound.

            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int j = stack.pop();
                int width;
                // be careful: when stack is empty, it means j is the smallest number in the array,
                //             Then width = i;
                if (!stack.isEmpty()) {
                    width = i - stack.peek() - 1;
                }
                else {
                    width = i;
                }
                int newArea = heights[j] * width;
                if (maxArea < newArea) {
                    maxArea = newArea;
                }
            }
            stack.push(i);
        }


        // all the remaining item will be in acending order in stack,
        // which has right bound = heights.length

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int width;
            if (!stack.isEmpty()) {
                width = i - stack.peek() - 1;
            }
            else {
                width = i;
            }
            int newArea = heights[j] * width;
            if (maxArea < newArea) {
                maxArea = newArea;
            }
        }
        return maxArea;
    }

    static public void main(String[] arg) {
        int[] heights = {2,1,5,6,2,3};
        //int[] heights = {2,1,5};
        System.out.println(Q84LargestRectHistogram.largestRectangleArea(heights));
    }
}
