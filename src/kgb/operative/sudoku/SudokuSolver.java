package kgb.operative.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {

    private static int visitedNodes = 0;
    private static SudokuBoard solvedBoard = new SudokuBoard();
    private static SudokuBoard initialBoard = new SudokuBoard(new int[][]{
        { 8, 5, 0, 0, 0, 2, 4, 0, 0 },
        { 7, 2, 0, 0, 0, 0, 0, 0, 9 },
        { 0, 0, 4, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 1, 0, 7, 0, 0, 2 },
        { 3, 0, 5, 0, 0, 0, 9, 0, 0 },
        { 0, 4, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 8, 0, 0, 7, 0 },
        { 0, 1, 7, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 3, 6, 0, 4, 0 }
    });


    private static boolean solve(SudokuBoard currentBoard) {
        ++visitedNodes;
        int zero = currentBoard.getZero();

        if (zero >= 0) {
            for (int guess : currentBoard.availableNums(zero)) {
                currentBoard.setAt(zero, guess);

                if (solve(currentBoard)) {
                    return true;
                }

                currentBoard.setAt(zero, 0);
            }
        }

        else if (currentBoard.isSolved()) {
            solvedBoard = new SudokuBoard(currentBoard);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Initial Board:");
        System.out.println(initialBoard.toString());

        if (solve(new SudokuBoard(initialBoard))) {
            System.out.println("Solved Board:");
            System.out.println(solvedBoard.toString());
        }
        else {
            System.out.println("The board was not solvable");
        }
        System.out.println("The solver visited " + visitedNodes + " nodes");
    }
}
