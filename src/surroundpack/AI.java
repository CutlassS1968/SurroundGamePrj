package surroundpack;

import javax.swing.*;
import java.util.Random;

public class AI extends Surround4Game{

  private int AI;

  private Surround4Game game;

  private Cell[][] board;

  private Random r = new Random();
  private Random c = new Random();

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
        if (board[row][col] == null) {
          board[row][col] = new Cell(0);
        }
      }
    }
  }
}