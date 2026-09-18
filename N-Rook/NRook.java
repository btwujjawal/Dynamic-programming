import java.util.*;

public class NRook {

    static int n;
    static char[][] board;

    static boolean isSafe(int row, int col) {

        // Check the column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'R') {
                return false;
            }
        }

        return true;
    }

    static boolean solve(int row) {

        // All rooks are placed
        if (row == n) {
            return true;
        }

        // Try every column in the current row
        for (int col = 0; col < n; col++) {

            if (isSafe(row, col)) {

                // Place rook
                board[row][col] = 'R';

                // Recursion
                if (solve(row + 1)) {
                    return true;
                }

                // Backtrack
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

        if (n <= 0) {
            System.out.println("N must be greater than 0.");
            sc.close();
            return;
        }

        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        if (solve(0)) {
            System.out.println("Solution:");
            printBoard();
        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}
