import java.util.*;

public class OneEach {

    static int n;
    static char[][] board;
    static char[] pieces = {'Q', 'K', 'R', 'B'};

    static boolean attacks(char piece, int r1, int c1, int r2, int c2) {

        int rowDiff = Math.abs(r1 - r2);
        int colDiff = Math.abs(c1 - c2);

        if (piece == 'Q') {
            return r1 == r2 ||
                   c1 == c2 ||
                   rowDiff == colDiff;
        }

        if (piece == 'R') {
            return r1 == r2 || c1 == c2;
        }

        if (piece == 'B') {
            return rowDiff == colDiff;
        }

        if (piece == 'K') {
            return (rowDiff == 2 && colDiff == 1) ||
                   (rowDiff == 1 && colDiff == 2);
        }

        return false;
    }

    static boolean isSafe(int row, int col, char piece) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] != '.') {

                    char otherPiece = board[i][j];

                    if (attacks(piece, row, col, i, j) ||
                        attacks(otherPiece, i, j, row, col)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static boolean solve(int pieceIndex) {

        // All four pieces are placed
        if (pieceIndex == pieces.length) {
            return true;
        }

        char piece = pieces[pieceIndex];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (board[row][col] == '.' && isSafe(row, col, piece)) {

                    // Choose
                    board[row][col] = piece;

                    // Recursion
                    if (solve(pieceIndex + 1)) {
                        return true;
                    }

                    // Backtrack
                    board[row][col] = '.';
                }
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

        if (n < 4) {
            System.out.println("No solution found for this board size.");
        } else if (solve(0)) {
            System.out.println("Solution:");
            printBoard();
        } else {
            System.out.println("No solution found.");
        }

        sc.close();
    }
}
