package kgb.operative.sudoku;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SudokuBoard {

    private int[][] board = new int[9][9];
    private static Set<Integer> nums = Collections.unmodifiableSet(new HashSet<Integer>(){
        {
            for (int i = 1; i <= 9; i++) {
                add(i);
            }
        }
    });

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
    }

    public SudokuBoard(int board[][]) {
        this.board = board;
    }

    public SudokuBoard(SudokuBoard other) {
        for (int i = 0; i < 9; i++) {
            board[i] = other.board[i].clone();
        }
    }

    public void setAt(int loc, int num) {
        board[loc / 10][loc % 10] = num;
    }

    public int getZero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return 10 * i + j;
                }
            }
        }
        return -1;
    }

    public Set<Integer> availableNums(int loc) {
        int row = loc / 10;
        int col = loc % 10;
        int boxRow = 3 * (row / 3);
        int boxCol = 3 * (col / 3);

        Set<Integer> availNums = new HashSet<Integer>(nums);

        for (int i = 0; i < 9; i++) {
            availNums.remove(board[row][i]);
            availNums.remove(board[i][col]);
            availNums.remove(board[boxRow + i / 3][boxCol + i % 3]);
        }

        return availNums;
    }

    public boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            Set<Integer> rowNums = new HashSet<Integer>(nums);
            Set<Integer> colNums = new HashSet<Integer>(nums);
            Set<Integer> boxNums = new HashSet<Integer>(nums);

            int boxRow = 3 * (i / 3);
            int boxCol = 3 * (i % 3);

            for (int j = 0; j < 9; j++) {
                rowNums.remove(board[i][j]);
                colNums.remove(board[j][i]);
                boxNums.remove(board[boxRow + j / 3][boxCol + j % 3]);
            }

            if (!rowNums.isEmpty() || !colNums.isEmpty() || !boxNums.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j] + " ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
