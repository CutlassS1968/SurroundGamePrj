package surroundpack;

public class Surround4Game {
    private Cell[][] board;
    private int player;

    public Surround4Game() {
        //super();
        board = new Cell[10][10];
        this.player = 1;
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
        if (board[row][col] == null) {  //|| (cats() && board[row][col].getPlayeNumber() != player)) {
            Cell c = new Cell(player);
            board[row][col] = c;
            return true;
        } else
            return false;
    }

    public int getWinner() {
        for (int row = 0; row < 10; row++)
            for (int col = 0; col < 10; col++)
                if (board[row][col] != null) {

                    // top-left corner case (check two sides only)
                    if (row == 0 && col == 0)
                        if (board[0][1] != null && board[1][0] != null)
                            if (board[0][1].getPlayeNumber() == board[1][0].getPlayeNumber())
                                return board[0][1].getPlayeNumber();

                    // top-right corner case (check two sides only)
                    if (row == 0 && col == 9)
                        if (board[0][8] != null && board[1][9] != null)
                            if (board[0][8].getPlayeNumber() == board[1][9].getPlayeNumber())
                                return board[0][8].getPlayeNumber();

                    // bottom-left corner case (check two sides only)
                    if (row == 9 && col == 0)
                        if (board[8][0] != null && board[9][1] != null)
                            if (board[8][0].getPlayeNumber() == board[9][1].getPlayeNumber())
                                return board[8][0].getPlayeNumber();

                    // bottom-right corner case (check two sides only)
                    if (row == 9 && col == 9)
                        if (board[8][9] != null && board[9][8] != null)
                            if (board[8][9].getPlayeNumber() == board[8][9].getPlayeNumber())
                                return board[8][9].getPlayeNumber();

                    // left-border case (excluding corners - check 3 sides only)
                    if (row != 0 && row != 9 && col == 0)
                        if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] != null)
                            if (board[row - 1][col].getPlayeNumber() == board[row][col + 1].getPlayeNumber() &&
                                    board[row - 1][col].getPlayeNumber() == board[row + 1][col].getPlayeNumber())
                                return board[row - 1][col].getPlayeNumber();

                    // top-border case (excluding corners - check 3 sides only)
                    if (row == 0 && col != 0 && col != 9)
                        if (board[row][col - 1] != null && board[row + 1][col] != null && board[row][col + 1] != null)
                            if (board[row][col - 1].getPlayeNumber() == board[row + 1][col].getPlayeNumber() &&
                                    board[row][col - 1].getPlayeNumber() == board[row][col + 1].getPlayeNumber())
                                return board[row][col - 1].getPlayeNumber();

                    // right-border case (excluding corners - check 3 sides only)
                    if (row != 0 && row != 9 && col == 9)
                        if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] != null)
                            if (board[row - 1][col].getPlayeNumber() == board[row][col - 1].getPlayeNumber() &&
                                    board[row - 1][col].getPlayeNumber() == board[row + 1][col].getPlayeNumber())
                                return board[row - 1][col].getPlayeNumber();

                    // bottom-border case (excluding corners - check 3 sides only)
                    if (row == 9 && col != 0 && col != 9)
                        if (board[row][col - 1] != null && board[row - 1][col] != null && board[row][col + 1] != null)
                            if (board[row][col - 1].getPlayeNumber() == board[row - 1][col].getPlayeNumber() &&
                                    board[row][col - 1].getPlayeNumber() == board[row][col + 1].getPlayeNumber())
                                return board[row][col - 1].getPlayeNumber();

                    // center case (excluding sides and corners - check 4 sides)
                    if (row != 0 && row != 9 && col != 0 && col != 9)
                        if (board[row-1][col] != null && board[row][col-1] != null && board[row+1][col] != null &&
                        board[row][col+1] != null)
                            if (board[row-1][col].getPlayeNumber() == board[row][col-1].getPlayeNumber() &&
                            board[row-1][col].getPlayeNumber() == board[row+1][col].getPlayeNumber() &&
                            board[row-1][col].getPlayeNumber() == board[row][col+1].getPlayeNumber())
                                return board[row-1][col].getPlayeNumber();
                }
        return -1;
    }
}



