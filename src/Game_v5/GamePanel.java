package Game_v5;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int DELAY = 40;

    private Snake snake;
    private Food food;
    private boolean inGame;
    private int score;

    public GamePanel() {
        initGame();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        startGame();
    }

    private void initGame() {
        snake = new Snake();
        food = new Food();
        inGame = true;
        score = 0;
    }

    private void startGame() {
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            snake.draw(g);
            food.draw(g);
        } else {
            gameOver(g);
        }
    }

    private void checkFood() {
        if ((snake.getHeadX() == food.getFoodX()) && (snake.getHeadY() == food.getFoodY())) {
            if (food.getFoodColor() == Color.RED) {
                score += 1;
                snake.grow();
            } else if (food.getFoodColor() == Color.YELLOW) {
                score += 3;
                snake.addDots(3);
            }
            food.locateFood();
        }
    }

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
            if (snake.checkCollision(WIDTH, HEIGHT)) {
                inGame = false;
            }
            snake.move();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && (!snake.isRightDirection())) {
                snake.setLeftDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
            }

            if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && (!snake.isLeftDirection())) {
                snake.setRightDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
            }

            if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && (!snake.isDownDirection())) {
                snake.setUpDirection(true);
                snake.setRightDirection(false);
                snake.setLeftDirection(false);
            }

            if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && (!snake.isUpDirection())) {
                snake.setDownDirection(true);
                snake.setRightDirection(false);
                snake.setLeftDirection(false);
            }

            if (key == KeyEvent.VK_ENTER && !inGame) {
                initGame();
            }
        }
    }
}
