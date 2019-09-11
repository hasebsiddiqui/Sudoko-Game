//Name: Haseeb Ahmad Siddiqui
//Roll no: FA17-BCS-125
//Section: BCS-B

import java.security.SecureRandom;
import javax.swing.JTextField;

public class Sudoku_Final {

    //Stores solveable sudoku games
    private int[][][] games = {{{5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0},
    {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6},
    {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}}, {{5, 0, 9, 0, 0, 0, 0, 0, 7}, {0, 8, 0, 0, 1, 0, 5, 2, 0}, {0, 0, 3, 0, 8, 4, 0, 0, 1},
    {0, 9, 0, 7, 0, 0, 0, 0, 2}, {4, 0, 0, 0, 5, 0, 3, 9, 0}, {8, 0, 2, 1, 0, 0, 0, 0, 4},
    {0, 0, 0, 3, 0, 2, 0, 0, 5}, {0, 4, 0, 0, 0, 0, 7, 0, 0}, {1, 0, 7, 0, 9, 0, 0, 8, 0}}, {{4, 2, 7, 1, 0, 0, 0, 6, 8}, {0, 0, 5, 0, 0, 6, 3, 0, 0}, {6, 0, 3, 0, 0, 0, 1, 0, 0},
    {2, 0, 0, 0, 1, 0, 4, 0, 0}, {3, 4, 0, 0, 6, 7, 0, 5, 1}, {8, 0, 1, 0, 5, 0, 0, 2, 0},
    {0, 9, 0, 0, 0, 0, 7, 3, 0}, {7, 0, 4, 3, 0, 0, 2, 0, 9}, {0, 3, 2, 0, 9, 4, 6, 0, 0}}};

    //Will store a random solveable sudoku game
    private int[][] puzzle = new int[9][9];

    //Generate a new random game from games array.
    public void generateGame(JTextField[][] textFields) {
        SecureRandom random = new SecureRandom();

        int gameNumber = random.nextInt(games.length);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = games[gameNumber][i][j];

                if (puzzle[i][j] == 0) {
                    textFields[i][j].setText("");
                } else {
                    textFields[i][j].setText("" + puzzle[i][j]);
                }
            }
        }
    }

    //Solve the game. And display its solution on JFrame using JtextFields
    public void printSolution(JTextField[][] textFields) {
        solve();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j].setText("" + puzzle[i][j]);

            }

        }
    }

    //Check if game is solved or not
    private boolean check() {
        for (int i = 0; i < 9; i++) {//rows
            for (int j = 0; j < 9; j++) { //columns

                //If puzzle contains 0, than it is not solved
                if (puzzle[i][j] == 0) {
                    return false;
                }

                for (int k = 0; k < 9; k++) {

                    //Check for columns
                    if (k != j && puzzle[i][j] == puzzle[i][k]) {
                        return false;
                    }

                    //Check for rows
                    if (k != i && puzzle[i][j] == puzzle[k][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Solve the game
    private void solve() {
        
        //Loop until solution is gained
        while (!check()) {

            for (int i = 0; i < 9; i++) { //Rows
                for (int j = 0; j < 9; j++) { //Columns

                    //Now check if 0 is present in current cell
                    if (puzzle[i][j] == 0) {

                        //Flag for checking solution for that particular index
                        int flag = 0;

                        //Put numbers from 1-9, check check if they are applicable or not                        
                        for (int numberToInsert = 1; numberToInsert <= 9; numberToInsert++) {

                            if (checkColumn(i, j, numberToInsert) && checkRow(i, j, numberToInsert) && checkBox(i, j, numberToInsert)) {
                                puzzle[i][j] = numberToInsert;
                                flag++;
                            }
                        }

                        if (flag != 1) {
                            puzzle[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    //Check if a number can be inserted in a box
    //i = rows
    //j = columns
    private boolean checkBox(int i, int j, int num) {

        int row_sum = (i / 3) * 3;
        int column_sum = (j / 3) * 3;

        for (int l = 0; l < 3; l++) { //rows
            for (int m = 0; m < 3; m++) { //columns

                if (num == puzzle[l + row_sum][m + column_sum]) {
                    return false;
                }
            }
        }
        return true;
    }

    //Check if a number can be inserted in a row
    //i = rows
    //j = columns
    private boolean checkRow(int i, int j, int num) {
        //loop to check solution
        for (int l = 0; l < 9; l++) {
            if (num == puzzle[i][l]) {
                return false;
            }
        }
        return true;
    }

    //Check if a number can be inserted in a column
    //i = rows
    //j = columns
    private boolean checkColumn(int i, int j, int num) {
        //loop to check solution
        for (int l = 0; l < 9; l++) {
            if (num == puzzle[l][j]) {
                return false;
            }
        }
        return true;
    }
}
