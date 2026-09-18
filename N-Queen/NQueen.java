import java.util.*;

public class NQueen {

    static int n;
    static char[][] board;

    static boolean isSafe(int row, int col) {

        // Check same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    static boolean solve(int row) {

        // All queens have been placed
        if (row == n) {
            return true;
        }

        // Try every column in the current row
        for (int col = 0; col < n; col++) {

            if (isSafe(row, col)) {

                board[row][col] = 'Q';

                if (solve(row + 1)) {
                    return true;
                }

                // Backtracking
                board[row][col] = '.';
            }
        }

        return false;
    }

    static void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        n = sc.nextInt();

        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        if (solve(0)) {
            System.out.println("Solution:");
            printBoard();
        } else {
            System.out.println("No solution exists for N = " + n);
        }

        sc.close();
    }
}
