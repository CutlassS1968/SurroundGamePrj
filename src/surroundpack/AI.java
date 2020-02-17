package surroundpack;

import javax.swing.*;

public class AI extends Surround4Game{

  private Surround4Game game;

  private Cell[][] board;

  /*********************************************************************************************
   * Instantiates Surround4Game's instance variables
   *  @param bSize int - Size of the board
   * @param nPlayers int - Number of players
   * @param sPlayer int - Starting player
   * @param activateAI
   *********************************************************************************************/
  public AI(int bSize, int nPlayers, int sPlayer, int activateAI) {
    super(bSize, nPlayers, sPlayer, activateAI);
    game = new Surround4Game(bSize, nPlayers, sPlayer, activateAI);
    board = new Cell[bSize][bSize];
  }

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
          } else {
            board[row][col] = new Cell(0);
          }
        }
      }
    }
  }

  // fill in move (will put next to solo number, if not chooses random Cell)
  public void checkSolo() {
    for (int row = 0; row < game.getbSize(); row++) {
      for (int col = 0; col < game.getbSize(); col++) {
        if (board[row][col] != null) {

          // TL check
          board[row - 1][col] = new Cell(0);
          board[row][col - 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row][col - 1] = null;
          } else {
            board[row - 1][col] = null;
            board[row][col - 1] = null;
          }

          // TR check
          board[row - 1][col] = new Cell(0);
          board[row][col + 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row][col + 1] = null;
          } else {
            board[row - 1][col] = null;
            board[row][col + 1] = null;
          }

          // TB check
          board[row - 1][col] = new Cell(0);
          board[row + 1][col] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row + 1] = null;
          } else {
            board[row - 1][col] = null;
            board[row + 1][col] = null;
          }

          // LB check
          board[row][col - 1] = new Cell(0);
          board[row + 1][col] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row + 1][col] = null;
          } else {
            board[row][col - 1] = null;
            board[row + 1][col] = null;
          }

          // LR check
          board[row][col - 1] = new Cell(0);
          board[row][col + 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row][col + 1] = null;
          } else {
            board[row][col - 1] = null;
            board[row][col + 1] = null;
          }

          // BR check
          board[row + 1][col] = new Cell(0);
          board[row][col + 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row][col + 1] = null;
          } else {
            board[row + 1][col] = null;
            board[row][col + 1] = null;
          }

          // TBL check
          board[row - 1][col] = new Cell(0);
          board[row + 1][col] = new Cell(0);
          board[row][col - 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row + 1][col] = null;
            board[row][col - 1] = null;
          } else {
            board[row - 1][col] = null;
            board[row + 1][col] = null;
            board[row][col - 1] = null;
          }

          // TBR check
          board[row - 1][col] = new Cell(0);
          board[row + 1][col] = new Cell(0);
          board[row][col + 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row + 1][col] = null;
            board[row][col + 1] = null;
          } else {
            board[row - 1][col] = null;
            board[row + 1][col] = null;
            board[row][col + 1] = null;
          }

          // TLR check
          board[row - 1][col] = new Cell(0);
          board[row][col - 1] = new Cell(0);
          board[row][col + 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row][col - 1] = null;
            board[row][col + 1] = null;
          } else {
            board[row - 1][col] = null;
            board[row][col - 1] = null;
            board[row][col + 1] = null;
          }

          // BLR check
          board[row][col - 1] = new Cell(0);
          board[row + 1][col] = new Cell(0);
          board[row][col + 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row + 1][col] = null;
            board[row][col + 1] = null;
          } else {
            board[row][col - 1] = null;
            board[row + 1][col] = null;
            board[row][col + 1] = null;
          }

          // All four
          board[row - 1][col] = new Cell(0);
          board[row + 1][col] = new Cell(0);
          board[row][col + 1] = new Cell(0);
          board[row][col - 1] = new Cell(0);
          if (game.isWinner() == 0) {
            board[row + 1][col] = null;
            board[row][col + 1] = null;
            board[row][col - 1] = null;
          } else {
            board[row - 1][col] = null;
            board[row + 1][col] = null;
            board[row][col + 1] = null;
            board[row][col - 1] = null;
          }
        }
      }
    }
  }
}