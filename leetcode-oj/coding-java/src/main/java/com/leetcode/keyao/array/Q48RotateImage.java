package com.leetcode.keyao.array;

/**
 * Created by keyao on 11/10/16.
 * You are given an n x n 2D matrix representing an image.

 Rotate the image by 90 degrees (clockwise).

 Follow up:
 Could you do this in-place?
 */
public class Q48RotateImage {
    static public void flipByMidY(int[][] matrix) {
        int len = matrix.length;
        if (len < 1) return;
        if (len < matrix[0].length) {
            return;
        }

        // Be careful, matrix[len-1-i] instead of matrix[len-i]

        for (int i=0; i<len/2; i++) {
            for (int j=0; j<len; j++) {
                int tmp = matrix[len-1-i][j];
                matrix[len-1-i][j] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        return;
    }

    static public void flipByDiag(int[][] matrix) {
        int len = matrix.length;
        if (len < 1) return;
        if (len < matrix[0].length) {
            return;
        }
        for (int i=0; i<len; i++) {
            for (int j=0; j<len-i; j++) {
                int tmp = matrix[len-1-j][len-1-i];
                matrix[len-1-j][len-1-i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        return;

    }

    static public void rotate(int[][] matrix) {
        flipByDiag(matrix);
        flipByMidY(matrix);
    }

    static public void printMatrix(int[][] matrix) {
        int len = matrix.length;
        if (len < 1) return;
        if (len < matrix[0].length) {
            return;
        }
        for (int i=0; i<len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println("\n");
        }
    }

    static public void main(String[] arg) {
        int[][] matrix = {
                          {1,2,3,4},
                          {5,6,7,8},
                          {9,10,11,12},
                          {13,14,15,16}
                         };

        Q48RotateImage.printMatrix(matrix);
        Q48RotateImage.rotate(matrix);
        Q48RotateImage.printMatrix(matrix);


    }

}
