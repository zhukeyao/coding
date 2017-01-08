package com.leetcode.keyao.array;

/**
 * Created by keyao on 11/9/16.
 There are two sorted arrays nums1 and nums2 of size m and n respectively.
 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 Keyao: Note: This is a tricky problem. The idea is to convert the problem to be the problem of
              finding the k smallest number in the two sorted array. Then we can
              divide k into two part, and check separately in the two
              array.  Let's nums1[pos1], and nums2[pos2], be the two anchor number in two sorted array, where
              pos1 + pos2 = k, if (nums1[pos1] < nums2[pos2]), we know it is safe to drop all number before pos1
              in nums1. After that, Then the problem become an recursive problem
 */
public class Q4Median {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int totalCount = len1 + len2;
        if (totalCount % 2 == 1) {
            // odd number, existing a single median
            return findKthSmallestNumber(nums1, len1, 0, nums2,len2,0, totalCount / 2 + 1);
        }
        else {
            // even number, find the median from the two middle number
            double n1 =  findKthSmallestNumber(nums1,len1,0,nums2,len2,0, totalCount / 2);
            double n2 =  findKthSmallestNumber(nums1,len1,0,nums2,len2,0, totalCount / 2 + 1);
            return  (n1 + n2) / 2.0;
        }
    }

    double findKthSmallestNumber(int[] nums1, int n1, int pos1, int[] nums2, int n2, int pos2, int k) {
        if (n1 == 0) return nums2[pos2+k];
        if (n2 == 0) return nums1[pos1+k];
        if (k == 1) return nums1[pos1] < nums2[pos2] ? nums1[pos1] : nums2[pos2];

        // always ensure nums2 size is larger than nums1 size
        if (n1 > n2) findKthSmallestNumber(nums2, n2, pos2, nums1, n1, pos1, k);

        // try to allocate k/2 number in nums1
        int m1 = Math.min(k/2, n1);
        // leave the remaining number in nums2
        int m2 = k - m1;

        if (nums1[pos1+m1-1] == nums2[pos2+m2-1]) {
            return nums1[pos1+m1-1];
        }

        if (nums1[pos1+m1-1] < nums2[pos2+m2-1]) {
            return findKthSmallestNumber(nums1, n1-m1, pos1+m1, nums2, n2, pos2, k-m1);
        }
        else {
            return findKthSmallestNumber(nums1, n1, pos1, nums2, n2-m2, pos2+m2, k-m2);
        }
    }

    static public void main(String[] arg) {
        int[] nums1 = {2,4,6};
        int[] nums2 = {1,3,5};

        Q4Median solution = new Q4Median();
        double median = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println("median = " + median);

    }

}
