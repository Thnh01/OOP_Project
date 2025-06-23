package Game_v5;

import javax.swing.JFrame;

public class Game extends JFrame {
    public Game() {
        initUI();
    }

    private void initUI() {
        add(new GamePanel());
        setResizable(false);
        pack();
        
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }
}
