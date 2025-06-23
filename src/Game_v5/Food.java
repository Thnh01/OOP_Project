package Game_v5;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {
    private final int DOT_SIZE = 10;
    private int foodX;
    private int foodY;
    private Color foodColor;

    public Food() {
        locateFood();
    }

    public void locateFood() {
        Random rand = new Random();
        foodX = rand.nextInt(60) * DOT_SIZE;
        foodY = rand.nextInt(60) * DOT_SIZE;

        //10% yellow food
        int chance = rand.nextInt(10);
        if (chance == 0) {
            foodColor = Color.YELLOW;
        } else {
            foodColor = Color.RED;
        }
    }

    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, DOT_SIZE, DOT_SIZE);
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public Color getFoodColor() {
        return foodColor;
    }
}
