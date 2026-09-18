import java.util.*;

public class NBishop {

    static int n;
    static char[][] board;

    static boolean isSafe(int row, int col) {

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'B') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'B') {
                return false;
            }
        }

        // Check lower-left diagonal
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 'B') {
                return false;
            }
        }

        // Check lower-right diagonal
        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++) {
            if (board[i][j] == 'B') {
                return false;
            }
        }

        return true;
    }

    static boolean solve(int index, int placed) {

        // All bishops are placed
        if (placed == n) {
            return true;
        }

        // All cells have been checked
        if (index == n * n) {
            return false;
        }

        // Not enough cells remain
        if (n * n - index < n - placed) {
            return false;
        }

        int row = index / n;
        int col = index % n;

        // Place bishop
        if (isSafe(row, col)) {

            board[row][col] = 'B';

            if (solve(index + 1, placed + 1)) {
                return true;
            }

            // Backtracking
            board[row][col] = '.';
        }

        // Skip current cell
        if (solve(index + 1, placed)) {
            return true;
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

        if (n <= 0) {
            System.out.println("N must be greater than 0.");
            sc.close();
            return;
        }

        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        if (solve(0, 0)) {
            System.out.println("Solution:");
            printBoard();
        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}
