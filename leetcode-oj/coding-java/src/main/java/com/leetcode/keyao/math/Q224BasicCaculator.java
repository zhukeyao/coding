package com.leetcode.keyao.math;

import java.util.LinkedList;

/**
 * Created by keyao on 12/30/16.
 */
public class Q224BasicCaculator {
    LinkedList<Character> symbolStack = new LinkedList<Character>();
    LinkedList<Integer> opStack = new LinkedList<Integer>();

     public int calculate2(String s) {
        int op = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                op = op * 10 + c - '0';
                continue;
            }
            if (op != 0) {
                opStack.push(op);
                op = 0;
            }
            if (c == ' ') continue;
            if (symbolStack.size() == 0) {
                symbolStack.push(c);
                continue;
            }
            switch (c) {
                case '+':
                case '-': {
                    char topOp = symbolStack.peek();
                    if (opStack.size() < 2 || topOp == '(') {
                        symbolStack.push(c);
                        continue;
                    }
                    topOp = symbolStack.pop();
                    int op1 = opStack.pop();
                    int op2 = opStack.pop();
                    if (topOp == '+') {
                        opStack.push(op1 + op2);
                    } else if (topOp == '-') {
                        opStack.push(op2 - op1);
                    }
                    symbolStack.push(c);
                    break;
                }
                case '(':
                    symbolStack.push(c);
                    break;
                case ')': {
                    char topOp = symbolStack.pop();
                    if (topOp == '(') break;
                    int op1 = opStack.pop();
                    int op2 = opStack.pop();
                    if (topOp == '+') {
                        opStack.push(op1 + op2);
                    } else if (topOp == '-') {
                        opStack.push(op2 - op1);
                    }
                    i--;
                    break;
                }
            }
        }
        if (op != 0) opStack.push(op);
        if (symbolStack.size() > 0) {
            char topOp = symbolStack.pop();
            int op1 = opStack.pop();
            int op2 = opStack.pop();
            if (topOp == '+') {
                opStack.push(op1 + op2);
            } else if (topOp == '-') {
                opStack.push(op2 - op1);
            }
        }
        int result = opStack.pop();
        return result;
    }



    public int calculate(String s) {
        int op = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                op = op * 10 + c - '0';
                continue;
            }
            if (op != 0) {
                opStack.push(op);
                op = 0;
            }
            if (c == ' ') continue;
            if (symbolStack.size() == 0) {
                symbolStack.push(c);
                continue;
            }
            switch (c) {
                case '*': {
                    if (opStack.size() < 2) {
                        symbolStack.push(c);
                        continue;
                    }
                    char topOp = symbolStack.peek();
                    if (topOp == '*') {
                        int op1 = opStack.pop();
                        int op2 = opStack.pop();
                        opStack.push(op1 * op2);
                        symbolStack.pop();
                        break;
                    }
                    symbolStack.push(c);
                    break;
                }
                case '+':
                case '-': {
                    char topOp = symbolStack.peek();
                    if (opStack.size() < 2 || topOp == '(') {
                        symbolStack.push(c);
                        continue;
                    }
                    topOp = symbolStack.pop();
                    int op1 = opStack.pop();
                    int op2 = opStack.pop();
                    if (topOp == '*') {
                        opStack.push(op1 * op2);
                    } else if (topOp == '+') {
                        opStack.push(op1 + op2);
                    } else if (topOp == '-') {
                        opStack.push(op2 - op1);
                    }
                    symbolStack.push(c);
                    break;
                }
                case '(':
                    symbolStack.push(c);
                    break;
                case ')': {
                    char topOp = symbolStack.pop();
                    if (topOp == '(') break;
                    int op1 = opStack.pop();
                    int op2 = opStack.pop();
                    if (topOp == '*') {
                        opStack.push(op1 * op2);
                    } else if (topOp == '+') {
                        opStack.push(op1 + op2);
                    } else if (topOp == '-') {
                        opStack.push(op2 - op1);
                    }
                    i--;
                    break;
                }
            }
        }
        if (op != 0) opStack.push(op);
        while (symbolStack.size() > 0) {
            char topOp = symbolStack.pop();
            int op1 = opStack.pop();
            int op2 = opStack.pop();
            if (topOp == '*') {
                opStack.push(op1 * op2);
            } else if (topOp == '+') {
                opStack.push(op1 + op2);
            } else if (topOp == '-') {
                opStack.push(op2 - op1);
            }
        }
        int result = opStack.pop();
        return result;
    }

    static public void main(String arg[]) {
        Q224BasicCaculator solution = new Q224BasicCaculator();
        int result = solution.calculate2("3-(5-6)");

        //int result = solution.calculate("12+ 3* (4+2) + 4*5");
        //int result = solution.calculate("(4+2)");

        //int result = solution.calculate("3*(4+2)");
        System.out.println(result);

        long t1 = System.currentTimeMillis() / 1000;
        int t2 = (int)(System.currentTimeMillis() / 1000);
        System.out.println("t1 = " + t1);
        System.out.println("t2 = " + t2);
    }
}
