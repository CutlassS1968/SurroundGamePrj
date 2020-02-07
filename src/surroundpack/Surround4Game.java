package surroundpack;

import javax.swing.*;

/**********************************************************************************************
 * Surround4Game contains the core functions of the game
 *
 * @author      Austin Ackerman
 * @author      Evan Johns
 * @version     02/11/2020
 **********************************************************************************************/

public class Surround4Game {

    private Cell[][] board;

    private int cPlayer;    // Current player
    private int bSize;      // Size off board
    private int nPlayers;   // Number of total players

//    QUESTION: DO WE NEED THE DEFAULT CONSTRUCTOR STILL?

//    public Surround4Game() {
//        super();
//        board = new Cell[10][10];
//        this.player = 0;
//    }

    /******************************************************************************************
     * Instantiates Surround4Game's instance variables
     * @param bSize int - Size of the board
     * @param nPlayers int - Number of players
     * @param sPlayer int - Starting player
     ******************************************************************************************/
    public Surround4Game(int bSize, int nPlayers, int sPlayer) {
        board = new Cell[bSize][bSize];
        this.cPlayer = sPlayer;
        this.bSize = bSize;
        this.nPlayers = nPlayers;
    }

//     QUESTION: SHOULD WE BE USING RESET FOR NEW GAME? AND HOW?
//
//    public void reset() {
//        for (int r = 0; r < 10; r++) {
//            for (int c = 0; c < 10; c++) {
//                board[r][c] = null;
//            }
//        }
//    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public int getCurrentPlayer() {
        return cPlayer;
    }

    public void nextPlayer() {
        if (cPlayer >= nPlayers - 1)
            cPlayer = 0;
        else cPlayer = cPlayer + 1;
    }

    /**
     * select returns a boolean value depending on whether or not cell is occupied
     *
     * @param row int - Number of rows
     * @param col int - Number of Columns
     * @return true when the selected cell is null
     */
    public boolean select(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = new Cell(cPlayer);
            return true;
        } else
            return false;
    }

    /**
     * this method checks to see if a player has won and returns that player
     *
     * @return int - returns the winner
     */
    public int getWinner() {
        for (int row = 0; row < bSize; row++)
            for (int col = 0; col < bSize; col++)
                if (board[row][col] != null) {

                    // top-left corner case (check two sides only)
                    if (row == 0 && col == 0)
                        if (board[0][1] != null && board[1][0] != null)
                            if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber())
                                return board[0][1].getPlayerNumber();

                    // top-right corner case (check two sides only)
                    if (row == 0 && col == bSize-1)
                        if (board[0][bSize-2] != null && board[1][bSize-1] != null)
                            if (board[0][bSize-2].getPlayerNumber() == board[1][bSize-1].getPlayerNumber())
                                return board[0][bSize-2].getPlayerNumber();

                    // bottom-left corner case (check two sides only)
                    if (row == bSize-1 && col == 0)
                        if (board[bSize-2][0] != null && board[bSize-1][1] != null)
                            if (board[bSize-2][0].getPlayerNumber() == board[bSize-1][1].getPlayerNumber())
                                return board[bSize-2][0].getPlayerNumber();

                    // bottom-right corner case (check two sides only)
                    if (row == bSize-1 && col == bSize-1)
                        if (board[bSize-2][bSize-1] != null && board[bSize-1][bSize-2] != null)
                            if (board[bSize-2][bSize-1].getPlayerNumber() == board[bSize-1][bSize-2].getPlayerNumber())
                                return board[bSize-2][bSize-1].getPlayerNumber();

                    // left-border case (excluding corners - check 3 sides only)
                    if (row != 0 && row != bSize-1 && col == 0)
                        if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] != null)
                            if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                                    board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
                                return board[row - 1][col].getPlayerNumber();

                    // top-border case (excluding corners - check 3 sides only)
                    if (row == 0 && col != 0 && col != bSize-1)
                        if (board[row][col - 1] != null && board[row + 1][col] != null && board[row][col + 1] != null)
                            if (board[row][col - 1].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                                    board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber())
                                return board[row][col - 1].getPlayerNumber();

                    // right-border case (excluding corners - check 3 sides only)
                    if (row != 0 && row != bSize-1 && col == bSize-1)
                        if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] != null)
                            if (board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
                                    board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
                                return board[row - 1][col].getPlayerNumber();

                    // bottom-border case (excluding corners - check 3 sides only)
                    if (row == bSize-1 && col != 0 && col != bSize-1)
                        if (board[row][col - 1] != null && board[row - 1][col] != null && board[row][col + 1] != null)
                            if (board[row][col - 1].getPlayerNumber() == board[row - 1][col].getPlayerNumber() &&
                                    board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber())
                                return board[row][col - 1].getPlayerNumber();

                    // center case (excluding sides and corners - check 4 sides)
                    if (row != 0 && row != bSize-1 && col != 0 && col != bSize-1)
                        if (board[row-1][col] != null && board[row][col-1] != null && board[row+1][col] != null &&
                        board[row][col+1] != null)
                            if (board[row-1][col].getPlayerNumber() == board[row][col-1].getPlayerNumber() &&
                            board[row-1][col].getPlayerNumber() == board[row+1][col].getPlayerNumber() &&
                            board[row-1][col].getPlayerNumber() == board[row][col+1].getPlayerNumber())
                                return board[row-1][col].getPlayerNumber();
                }
        return -1;
    }

}



