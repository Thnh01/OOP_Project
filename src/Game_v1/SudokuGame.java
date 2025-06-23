package Game_v1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SudokuGame extends JFrame implements ActionListener {
    private JTextField[][] grid;
    private JButton solveButton;
    private JButton checkButton;
    private JButton resetButton;

    public SudokuGame() {
        setTitle("Sudoku Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(9, 9));
        grid = new JTextField[9][9];
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new JTextField();
                sudokuPanel.add(grid[i][j]);
                // Randomly leave some cells empty in each 3x3 group
                if (random.nextInt(10) < 8) {
                    grid[i][j].setEditable(false);
                    grid[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        // Add thick borders to the 3x3 grid regions
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        grid[k][l].setBorder(BorderFactory.createMatteBorder(
                                k % 3 == 0 ? 2 : 1, l % 3 == 0 ? 2 : 1,
                                (k + 1) % 3 == 0 ? 2 : 1, (l + 1) % 3 == 0 ? 2 : 1, Color.BLACK));
                    }
                }
            }
        }

        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        checkButton = new JButton("Check");
        checkButton.addActionListener(this);
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(solveButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(resetButton);

        add(sudokuPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solveButton) {
            int[][] sudokuGrid = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String text = grid[i][j].getText();
                    if (text.isEmpty()) {
                        sudokuGrid[i][j] = 0;
                    } else {
                        sudokuGrid[i][j] = Integer.parseInt(text);
                    }
                }
            }
            if (solveSudoku(sudokuGrid)) {
                // Update the grid with the solved Sudoku values
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        grid[i][j].setText(Integer.toString(sudokuGrid[i][j]));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No solution exists for the given Sudoku.", "No Solution", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == checkButton) {
            if (isFilledCorrectly()) {
                JOptionPane.showMessageDialog(this, "The Sudoku is filled correctly!", "Correct", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "There are mistakes in the Sudoku.", "Incorrect", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resetButton) {
            resetSudoku();
        }
    }

    private boolean solveSudoku(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValidMove(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solveSudoku(grid)) {
                                return true;
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidMove(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (grid[row][x] == num || grid[x][col] == num || grid[3 * (row / 3) + x / 3][3 * (col / 3) + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isFilledCorrectly() {
        int[][] sudokuGrid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = grid[i][j].getText();
                if (text.isEmpty()) {
                    return false;
                } else {
                    sudokuGrid[i][j] = Integer.parseInt(text);
                }
            }
        }
        return solveSudoku(sudokuGrid);
    }

    private void resetSudoku() {
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Randomly leave some cells empty in each 3x3 group
                if (random.nextInt(10) < 8) {
                    grid[i][j].setText("");
                    grid[i][j].setEditable(false);
                    grid[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    grid[i][j].setText("");
                    grid[i][j].setEditable(true);
                    grid[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuGame game = new SudokuGame();
            game.setVisible(true);
        });
    }
}
