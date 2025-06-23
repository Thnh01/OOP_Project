package Game_v5;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 3600;
    
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    
    private int dots;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    public Snake() {
        initSnake();
    }

    public void initSnake() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * DOT_SIZE;
            y[i] = 50;
        }
    }

    public void move() {
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

    public void draw(Graphics g) {
        for (int i = 0; i < dots; i++) {
            if (i == 0) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.YELLOW);
            }
            g.fillRect(x[i], y[i], DOT_SIZE, DOT_SIZE);
        }
    }

    public boolean checkCollision(int width, int height) {
        for (int i = dots; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                return true;
            }
        }

        if (y[0] >= height || y[0] < 0 || x[0] >= width || x[0] < 0) {
            return true;
        }
        return false;
    }

    public boolean isLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }

    public boolean isRightDirection() {
        return rightDirection;
    }

    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }

    public boolean isUpDirection() {
        return upDirection;
    }

    public void setUpDirection(boolean upDirection) {
        this.upDirection = upDirection;
    }

    public boolean isDownDirection() {
        return downDirection;
    }

    public void setDownDirection(boolean downDirection) {
        this.downDirection = downDirection;
    }

    public int getHeadX() {
        return x[0];
    }

    public int getHeadY() {
        return y[0];
    }

    public void grow() {
        dots++;
    }
    
    public int getDots() {
        return dots;
    }

    public void addDots(int additionalDots) {
        dots += additionalDots;
    }
}
