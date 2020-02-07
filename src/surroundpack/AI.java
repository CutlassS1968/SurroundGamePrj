package surroundpack;

import java.util.Random;

public class AI{

  private int AI;

  private Cell[][] board;

  private Surround4Game game;

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
}
