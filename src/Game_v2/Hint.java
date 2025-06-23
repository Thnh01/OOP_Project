package Game_v2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hint {
    public static void provideHint(Answer answer, JTextField[][] cells) {
        int[][] result = answer.getAnswer();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                cells[i][j].setText(String.valueOf(result[i][j]));
            }
        }

        Timer timer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearHint(cells);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private static void clearHint(JTextField[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getText().isEmpty()) {
                    cells[i][j].setText("");
                }
            }
        }
    }
}



