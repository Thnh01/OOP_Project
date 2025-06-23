package Game_v4;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 3600;
    private final int DELAY = 100;
//Arrays to store the x and y coordinates of each snake segment
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
//Variables for the current number of snake segments and food position
    private int dots;
    private int foodX;
    private int foodY;
//Booleans to track the snake's direction and game state
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private int score;

    public Panel() {
        initGame();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        startGame();
        
    }
//snake body
    private void initGame() {
        dots = 3; // Initial length of the snake
        foodColor = Color.RED; // Initialize food color to red
        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * DOT_SIZE;
            y[i] = 50;
        }
        locateFood();
    }
//Starts a timer to trigger game updates
    private void startGame() {
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }
//draw snake
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            drawSnake(g);
            drawFood(g);
        } else {
            gameOver(g);
        }
    }
//snake color
    private void drawSnake(Graphics g) {
        for (int i = 0; i < dots; i++) {
            if (i == 0) {
                g.setColor(Color.GREEN); // Head of the snake
            } else {
                g.setColor(Color.YELLOW); // Body of the snake
            }
            g.fillRect(x[i], y[i], DOT_SIZE, DOT_SIZE);
        }
    }
//food, 10% yellow
    private Color foodColor;

    private void drawFood(Graphics g) {
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, DOT_SIZE, DOT_SIZE);
    }

    private void checkFood() {
        if ((x[0] == foodX) && (y[0] == foodY)) {
            if (foodColor == Color.RED) {
                score += 1; // Food RED: +1pts
            } else if (foodColor == Color.YELLOW) {
                score += 3; // Food YELLOW: +3pts
                dots += 3; // Food YELLOW: size+3
            }
            dots++;
            locateFood();
        }
    }


    private void locateFood() {
        Random rand = new Random();
        foodX = rand.nextInt(60) * DOT_SIZE;
        foodY = rand.nextInt(60) * DOT_SIZE;
        
        // Generate a random number between 0 and 9
        int chance = rand.nextInt(10);
        if (chance == 0) { // 10% chance
            // Set food color to yellow
            foodColor = Color.YELLOW;
        } else {
            // Set food color to red
            foodColor = Color.RED;
        }
    }
//Moves the snake in the current direction:
    //Shifts each segment to the position of the previous segment.
    //Updates the head position based on the current direction
    private void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }
        if (rightDirection) {
            x[0] += DOT_SIZE;
        }
        if (upDirection) {
            y[0] -= DOT_SIZE;
        }
        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }
//check if snake collides with itself or the walls
    private void checkCollision() {
        for (int i = dots; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }

        if (y[0] >= HEIGHT || y[0] < 0 || x[0] >= WIDTH || x[0] < 0) {
            inGame = false;
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            // Check if Enter key is pressed and the game is over
            if (key == KeyEvent.VK_ENTER && !inGame) {
                resetGame();
            }
        }
    }

    // Method to reset the game
    private void resetGame() {
        score = 0;
        dots = 3;
        inGame = true;
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        initGame();
    }
    
    //game over board
    private void gameOver(Graphics g) {
        Font font = new Font("Arcade", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        
        String msg = "Game Over. Your Score: " + score;
        int msgWidth = g.getFontMetrics().stringWidth(msg);
        int x = (WIDTH - msgWidth) / 2;
        int y = HEIGHT / 2;
        
        g.drawString(msg, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkFood();
            checkCollision();
            move();
        }
        repaint();
    }
}