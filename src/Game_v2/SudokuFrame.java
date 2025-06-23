package Game_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SudokuFrame extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells;
    private int[][] initialValues;

    public SudokuFrame() {
        cells = new JTextField[SIZE][SIZE];

        setTitle("Sudoku Game");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                int cellWidth = width / SIZE;
                int cellHeight = height / SIZE;
                int thickLineWidth = 3;
                int thinLineWidth = 1;

                for (int i = 0; i <= SIZE; i++) {
                    int lineWidth = (i % 3 == 0) ? thickLineWidth : thinLineWidth;
                    g.setColor(Color.BLACK);
                    g.fillRect(i * cellWidth - lineWidth, 0, lineWidth, height);
                    g.fillRect(0, i * cellHeight - lineWidth, width, lineWidth);
                }
            }
        };

        boardPanel.setLayout(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                boardPanel.add(cells[i][j]);
            }
        }

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3));

        JButton easyButton = new JButton("EZ");
        JButton mediumButton = new JButton("Mid");
        JButton hardButton = new JButton("Hard");
        JButton resetButton = new JButton("Reset");
        JButton solveButton = new JButton("Solve");
        JButton hintButton = new JButton("Hint");

        controlPanel.add(easyButton);
        controlPanel.add(mediumButton);
        controlPanel.add(hardButton);
        controlPanel.add(resetButton);
        controlPanel.add(solveButton);
        controlPanel.add(hintButton);

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Add action listeners
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EZ ez = new EZ();
                initializeBoard(ez.getBoard());
            }
        });

        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mid mid = new Mid();
                initializeBoard(mid.getBoard());
            }
        });

        hardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Hard hard = new Hard();
                initializeBoard(hard.getBoard());
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reset.resetBoard(cells, initialValues);
            }
        });

        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Solve.solveBoard(new Answer(initialValues), cells);
            }
        });

        hintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Hint.provideHint(new Answer(initialValues), cells);
            }
        });

        boardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (isBoardCompleted()) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You have solved the Sudoku puzzle.");
                }
            }
        });
    }

    public void initializeBoard(int[][] initialValues) {
        this.initialValues = initialValues;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (initialValues[i][j] != 0) {
                    cells[i][j].setText(String.valueOf(initialValues[i][j]));
                    cells[i][j].setEditable(false);
                    cells[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    cells[i][j].setText("");
                    cells[i][j].setEditable(true);
                    cells[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    private boolean isBoardCompleted() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j].getText().isEmpty() || !isValid(i, j, Integer.parseInt(cells[i][j].getText()))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, int num) {
        return isSafeInRow(row, num) && isSafeInCol(col, num) && isSafeInBox(row - row % 3, col - col % 3, num);
    }
    
    private boolean isSafeInBox(int row, int col, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!cells[row + i][col + j].getText().isEmpty() && Integer.parseInt(cells[row + i][col + j].getText()) == num) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean isSafeInRow(int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (Integer.parseInt(cells[row][col].getText()) == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isSafeInCol(int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (Integer.parseInt(cells[row][col].getText()) == num) {
                return false;
            }
        }
        return true;
    }}

