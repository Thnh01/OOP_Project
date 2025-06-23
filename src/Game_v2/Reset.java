package Game_v2;

import javax.swing.JTextField;

public class Reset {
    public static void resetBoard(JTextField[][] cells, int[][] initialValues) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (initialValues[i][j] == 0) {
                    cells[i][j].setText("");
                }
            }
        }
    }
}

