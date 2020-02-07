package surroundpack;

import java.util.Random;

public class AI{

  private int AI;

  private Cell[][] board;

  private Surround4Game game;

  private Random r = new Random();
  private Random c = new Random();

  // check if won
  public void checkWon() {
    for (int row = 0; row < game.getbSize(); row++) {
      for (int col = 0; col < game.getbSize(); col++) {
        if (board[row][col] == null) {
          board[row][col] = new Cell(0);
          if (!(game.isWinner() == 0)) {
            board[row][col] = null;
          }
        }
      }
    }
  }

  // check if block
  public void checkBlock() {
    for (int row = 0; row < game.getbSize(); row++) {
      for (int col = 0; col < game.getbSize(); col++) {
        if (board[row][col] == null) {
          board[row][col] = new Cell(1);
          if (!(game.isWinner() == 1)) {
            board[row][col] = null;
          }
          else {
            board[row][col] = new Cell(0);
          }
        }
      }
    }
  }

  // fill in move (will put next to solo number, if not chooses random Cell)
  public void checkSolo() {
    for (int row = 0; row < game.getbSize(); row++) {
      for (int col = 0; col <game.getbSize(); col++) {
        if (board[row][col] != null && board[row][col].getPlayerNumber() != 0) {
          if (board[row - 1][col] == null && board[row + 1][col] == null && board[row][col - 1] == null &&
              board[row][col + 1] == null) {
            switch(r.nextInt(4)) {
              case 1:
                board[row - 1][col] = new Cell(0);

              case 2:
                board[row + 1][col] = new Cell(0);

              case 3:
                board[row][col - 1] = new Cell(0);

              case 4:
                board[row][col + 1] = new Cell(0);
            }
          } else {
            do {
              r.nextInt(game.getbSize());
              c.nextInt(game.getbSize());
            } while (board[r.nextInt(game.getbSize())][c.nextInt(game.getbSize())] != null);
            board[r.nextInt()][c.nextInt()] = new Cell(0);
          }
        }
      }
    }
  }
}
