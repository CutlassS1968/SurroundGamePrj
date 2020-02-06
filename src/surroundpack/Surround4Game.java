package surroundpack;

import javax.swing.*;

public class Surround4Game {
    private Cell[][] board;
    private int player;
    private int bSize;

    public Surround4Game() {
        super();
        board = new Cell[10][10];
        this.player = 1;
    }

    public Surround4Game(int size, int players, int player) {
        board = new Cell[size][size];
        this.player = player;
        bSize = size;
    }

    public void reset() {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                board[r][c] = null;
            }
        }
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public int getCurrentPlayer() {
        return player;
    }

    public int nextPlayer() {
        //		player = player + 1;
        //		if (player == 2)
        //			player = 0;

        player = (player + 1) % 2;
        return player;
    }

    public boolean select(int row, int col) {
        if (board[row][col] == null) {  //|| (cats() && board[row][col].getPlayerNumber() != player)) {
            Cell c = new Cell(player);
            board[row][col] = c;
            return true;
        } else
            return false;
    }

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
                            if (board[0][bSize-1].getPlayerNumber() == board[1][bSize-2].getPlayerNumber())
                                return board[0][bSize-1].getPlayerNumber();

                    // bottom-left corner case (check two sides only)
                    if (row == bSize-1 && col == 0)
                        if (board[bSize-2][0] != null && board[bSize-1][1] != null)
                            if (board[bSize-2][0].getPlayerNumber() == board[bSize-1][1].getPlayerNumber())
                                return board[bSize-2][0].getPlayerNumber();

                    // bottom-right corner case (check two sides only)
                    if (row == bSize-1 && col == bSize-1)
                        if (board[bSize-2][bSize-1] != null && board[bSize-1][bSize-2] != null)
                            if (board[bSize-2][bSize-1].getPlayerNumber() == board[bSize-2][bSize-1].getPlayerNumber())
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



