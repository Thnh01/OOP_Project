package Game_v2;

import javax.swing.JTextField;

public class Solve {
    public static void solveBoard(Answer answer, JTextField[][] cells) {
        int[][] result = answer.getAnswer();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                cells[i][j].setText(String.valueOf(result[i][j]));
            }
        }
    }
}


