package com.leetcode.keyao.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by keyao on 11/18/16.
 *
 * In a party of N people, only one person is known to everyone. Such a person may be present in the party,
 * if yes, (s)he doesn’t know anyone in the party. We can only ask questions like “does A know B? “.
 * Find the stranger (celebrity) in minimum number of questions.

   We can describe the problem input as an array of numbers/characters representing persons in the party.
   We also have a hypothetical function HaveAcquaintance(A, B) which returns true if A knows B, false otherwise.
   How can we solve the problem.

 * Algorithm 1:
 *      For a give item A, find all elements B who A know but B does not know A.  Use those B to be the candidate list and repeat
 *      until there is only one element or the selected element know everbody in the candidate list.
 *
 * Algorithm 2:
 *      This is a better algorithm, give any pair of element A, B. When can always discard one element by comparing them.
 *      if A know B, then A cannot be celebrity, drop A, otherwise drop B, keep doing until there is only on celebrity.
 *      Then we go through entire list and check if the remaining one is the celebrity
 */
public class Q227FindCelebrity {

    private int data[][] = {{0,0,1}, {0, 1, 1}, {0, 0, 0}};
    private Boolean know(int a, int b) {
        return data[a][b] > 0;
    }
    public int findCelebrity() {
        int index = 0;
        List<Integer> candidate = new ArrayList<Integer>();
        for (int i=0; i< data.length; i++) {
            candidate.add(i);
        }
        List<Integer> tmp = new ArrayList<>();
        while (candidate.size() > 0) {
            int target = candidate.get(0);
            int count = 0;
            for (int i=1; i<candidate.size(); i++) {
                if (know(target, i) && !know(i,target))  {
                    tmp.add(i);
                    continue;
                }
                if (!know(target, i) && know(i,target)) {
                    count++;
                }
            }
            if (count==candidate.size()-1) {
                return target;
            }
            if (tmp.size() == 1) {
                return tmp.get(0);
            }
            candidate.clear();
            candidate.addAll(tmp);
            tmp.clear();
        }
        return -1;
    }

    public int findCelebrity2() {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i=0; i<data.length; i++) {
            queue.add(i);
        }

        while (queue.size() >= 2) {
            int a = queue.removeFirst();
            int b = queue.removeFirst();
            if (know(a,b)) {
                queue.add(b);
            }
            else {
                queue.add(a);
            }
        }
        int target = queue.getFirst();
        int count = 0;
        for (int i=0; i<data.length; i++) {
            if (i==target) continue;
            if (!know(target, i) && know(i, target)) {
                count++;
            }
        }
        return count == data.length - 1 ? target : -1;
    }

    static public void main(String input[]) {
        Q227FindCelebrity solution = new Q227FindCelebrity();
        int b = solution.findCelebrity();
        System.out.println(b);

        int b2 = solution.findCelebrity2();
        System.out.println(b2);
    }
}
