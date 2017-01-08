package com.leetcode.keyao.design;

/**
 * Created by keyao on 12/29/16.
 */
public class Q348TickTacToe {
    int size = 0;
    int colCount[][];
    int rowCount[][];
    int diag1Count[];
    int diag2Count[];
    Q348TickTacToe(int size) {
        this.size = size;
        rowCount = new int[2][size];
        colCount = new int[2][size];
        diag1Count = new int[2];
        diag2Count = new int[2];
    }
    int move(int posX, int posY, int player) {

        //check row
        rowCount[player-1][posX]++;
        if (rowCount[player-1][posX] == size) {
            return player;
        }
        //check col
        colCount[player-1][posY]++;
        if (colCount[player-1][posY] == size) {
            return player;
        }

        //check diag
        if (posX == posY) {
            diag1Count[player-1]++;
            if (diag1Count[player-1] == size) {
                return player;
            }
        }
        if (posX == posY) {
            diag1Count[player-1]++;
            if (diag1Count[player-1] == size) {
                return player;
            }
        }
        if (posX + posY == size-1) {
            diag2Count[player-1]++;
            if (diag2Count[player-1] == size) {
                return player;
            }
        }
        return 0;
    }
}
