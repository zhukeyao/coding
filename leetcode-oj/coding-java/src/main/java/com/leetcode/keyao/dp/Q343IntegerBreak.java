package com.leetcode.keyao.dp;

/**
 * Created by keyao on 12/8/16.
 * I saw many solutions were referring to factors of 2 and 3. But why these two magic numbers? Why other factors do not work?
 Let's study the math behind it.

 For convenience, say n is sufficiently large and can be broken into any smaller real positive numbers. We now try to calculate which real number generates the largest product.
 Assume we break n into (n / x) x's, then the product will be xn/x, and we want to maximize it.

 Taking its derivative gives us n * xn/x-2 * (1 - ln(x)).
 The derivative is positive when 0 < x < e, and equal to 0 when x = e, then becomes negative when x > e,
 which indicates that the product increases as x increases, then reaches its maximum when x = e, then starts dropping.

 This reveals the fact that if n is sufficiently large and we are allowed to break n into real numbers,
 the best idea is to break it into nearly all e's.
 On the other hand, if n is sufficiently large and we can only break n into integers, we should choose integers that are closer to e.
 The only potential candidates are 2 and 3 since 2 < e < 3, but we will generally prefer 3 to 2. Why?

 Of course, one can prove it based on the formula above, but there is a more natural way shown as follows.

 6 = 2 + 2 + 2 = 3 + 3. But 2 * 2 * 2 < 3 * 3.
 Therefore, if there are three 2's in the decomposition, we can replace them by two 3's to gain a larger product.

 All the analysis above assumes n is significantly large. When n is small (say n <= 10), it may contain flaws.
 For instance, when n = 4, we have 2 * 2 > 3 * 1.
 To fix it, we keep breaking n into 3's until n gets smaller than 10, then solve the problem by brute-force.

 导数计算 : http://baike.baidu.com/link?url=tSGS5lsmmTkhtPJtVyXJbXeDKrmDdtI0wbh22G2t3k-igSHhT3USMLRPqBV0Xbdk60YahWrzbPd8NwPROcwm-puX_0kFSUen33hGzbSm8d-H5pTYEIw5LT31yGNDvwlQ

 y=u^v ==> y'=v' * u^v * lnu + u' * u^(v-1） * v
 */
public class Q343IntegerBreak {
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        product *= n;
        return product;
    }

    static public void main(String[] arg) {
        Q343IntegerBreak solution = new Q343IntegerBreak();
        int ret = solution.integerBreak(12);
        System.out.println("result = " + ret);
    }
}
