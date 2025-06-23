package Game_v4;

import javax.swing.JFrame;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        initUI();
    }

    private void initUI() {
        add(new Panel());
        setResizable(false);
        pack();
        
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.setVisible(true);
    }
}