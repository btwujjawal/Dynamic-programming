import java.util.*;

public class NKnight {

    static int n;
    static char[][] board;

    static boolean isSafe(int row, int col) {

        int[] rowMove = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] colMove = {-1, 1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < 8; i++) {

            int newRow = row + rowMove[i];
            int newCol = col + colMove[i];

            if (newRow >= 0 && newRow < n &&
                newCol >= 0 && newCol < n &&
                board[newRow][newCol] == 'K') {

                return false;
            }
        }

        return true;
    }

    static boolean solve(int index, int placed) {

        // Required number of knights are placed
        if (placed == n) {
            return true;
        }

        // No more cells are available
        if (index == n * n) {
            return false;
        }

        // Not enough cells remain
        if (n * n - index < n - placed) {
            return false;
        }

        int row = index / n;
        int col = index % n;

        // Choice 1: Place a knight
        if (isSafe(row, col)) {

            board[row][col] = 'K';

            if (solve(index + 1, placed + 1)) {
                return true;
            }

            // Backtrack
            board[row][col] = '.';
        }

        // Choice 2: Do not place a knight
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
