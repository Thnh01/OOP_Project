package Game_v2;

import java.util.Random;

public class EZ {
    private static final int SIZE = 9;
    private int[][] board;

    public EZ() {
        board = new int[SIZE][SIZE];
        generateEZBoard();
    }

    private void generateEZBoard() {
        fillDiagonal();
        fillRemaining(0, 3);
        removeNumbers();
    }

    private void fillDiagonal() {
        for (int i = 0; i < SIZE; i += 3) {
            fillBox(i, i);
        }
    }

    private void fillBox(int row, int col) {
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num;
                do {
                    num = rand.nextInt(SIZE) + 1;
                } while (!isSafeInBox(row, col, num));
                board[row + i][col + j] = num;
            }
        }
    }

    private boolean isSafeInBox(int row, int col, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[row + i][col + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean fillRemaining(int i, int j) {
        if (j >= SIZE && i < SIZE - 1) {
            i++;
            j = 0;
        }
        if (i >= SIZE && j >= SIZE) {
            return true;
        }
        if (i < 3) {
            if (j < 3) {
                j = 3;
            }
        } else if (i < SIZE - 3) {
            if (j == (i / 3) * 3) {
                j += 3;
            }
        } else {
            if (j == SIZE - 3) {
                i++;
                j = 0;
                if (i >= SIZE) {
                    return true;
                }
            }
        }
        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(i, j, num)) {
                board[i][j] = num;
                if (fillRemaining(i, j + 1)) {
                    return true;
                }
                board[i][j] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int i, int j, int num) {
        return isSafeInRow(i, num) && isSafeInCol(j, num) && isSafeInBox(i - i % 3, j - j % 3, num);
    }

    private boolean isSafeInRow(int i, int num) {
        for (int j = 0; j < SIZE; j++) {
            if (board[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isSafeInCol(int j, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    private void removeNumbers() {
        Random rand = new Random();
        for (int row = 0; row < SIZE; row += 3) {
            for (int col = 0; col < SIZE; col += 3) {
                int emptyCells = rand.nextInt(2) + 1; // Random between 1 and 3
                for (int i = 0; i < emptyCells; i++) {
                    int r, c;
                    do {
                        r = rand.nextInt(3);
                        c = rand.nextInt(3);
                    } while (board[row + r][col + c] == 0);
                    board[row + r][col + c] = 0;
                }
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }
}
