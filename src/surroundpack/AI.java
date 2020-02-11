package surroundpack;

import java.util.Random;

public class AI extends Surround4Game{

  private int AI;

  /* current player */
  private int sPlayer = 0;

  /* user input size for board */
  private int bSize = 0;

  /* user input number of players */
  private int nPlayer = 0;

  /* user input AI activation */
  private int activateAI = 0;

  private Cell[][] board;

  private Surround4Panel panel;

  private Surround4Game game;

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
  }

//  public AI() {
//    super(bSize, nPlayer, sPlayer, activateAI);
//  }

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
        if (board[row][col] != null && board[row][col].getPlayerNumber() != 0) {
          if (board[row - 1][col] == null && board[row + 1][col] == null && board[row][col - 1] == null &&
              board[row][col + 1] == null) {
            switch (r.nextInt(4)) {
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

            // check for 0 in any of the surrounding cells, two and three is ok, one is taken care of by winning
            //
          }
        }
      }
    }
  }
}
