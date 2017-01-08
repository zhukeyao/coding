package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/29/16.
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class Q9PalindromInteger {
    public boolean isPalindrome(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
            rev = rev*10 + x%10;
            x = x/10;
        }
        return (x==rev || x==rev/10);
    }
}
