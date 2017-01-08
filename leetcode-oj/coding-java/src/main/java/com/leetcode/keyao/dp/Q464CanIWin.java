package com.leetcode.keyao.dp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by keyao on 11/30/16.
 */
public class Q464CanIWin {

    public Boolean canWin(int maxChoosebleInt, int sum) {


        return true;
    }


    static public void main(String[] arg) {
        ArrayList<Integer> test1 = new ArrayList<Integer>();
        HashMap<ArrayList,Boolean> testMap = new HashMap<ArrayList,Boolean>();
        test1.add(1);
        test1.add(2);
        test1.add(2);

        testMap.put(test1, true);

        ArrayList<Integer> test2 = new ArrayList<Integer>();
        test2.add(1);
        test2.add(2);

        if (testMap.containsKey(test2))
            System.out.println("find");
        else
            System.out.println("not find");
    }
}
