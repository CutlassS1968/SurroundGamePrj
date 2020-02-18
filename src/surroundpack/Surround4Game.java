package surroundpack;

import java.util.Random;

/**********************************************************************************************
 * Surround4Game contains the core functions of the game
 *
 * @author Austin Ackerman
 * @author Evan Johns
 * @version 02/18/2020 11:19
 **********************************************************************************************/

public class Surround4Game {

  /* structure for game board */
  private Cell[][] board;

  /* current player */
  private int cPlayer;

  /* user input size for board */
  private int bSize;

  /* user input number of players */
  private int nPlayers;

  /* instance of the Random class */
  private Random random;

  /*********************************************************************************************
   * Instantiates Surround4Game's instance variables
   *
   * @param bSize int - Size of the board
   * @param nPlayers int - Number of players
   * @param sPlayer int - Starting player
   *********************************************************************************************/
  public Surround4Game(int bSize, int nPlayers, int sPlayer) {
    random = new Random();
    board = new Cell[bSize][bSize];

    this.cPlayer = sPlayer;
    this.bSize = bSize;
    this.nPlayers = nPlayers;

    createBoard();
  }

  private void createBoard() {
    for (int c = 0; c < board.length; ++c) {
      for (int r = 0; r < board.length; ++r) {
        board[r][c] = new Cell();
      }
    }
  }

  public void nextPlayer() {
    if (cPlayer >= nPlayers - 1) {
      cPlayer = 0;
    } else {
      cPlayer = cPlayer + 1;
    }
  }

  /**
   * select returns a boolean value depending on whether or not cell is occupied
   *
   * @param row int - Number of rows
   * @param col int - Number of Columns
   * @return true when the selected cell is null
   */
  public boolean select(int row, int col) {
    if (board[row][col].getPlayerNumber() == -1) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * isWinner checks to see if a player has won and returns that player or returns -1 if no-one
   * has won
   *
   * @return int - returns the winner
   */
  public int isWinner() {
    for (int row = 0; row < bSize; row++) {
      for (int col = 0; col < bSize; col++) {
        if (board[row][col].getPlayerNumber() != -1) {

          // top-left corner case (check 4 sides)
          if (row == 0 && col == 0) {
            if (board[0][1].getPlayerNumber() != -1 && board[1][0].getPlayerNumber() != -1 &&
                board[0][bSize - 1].getPlayerNumber() != -1 &&
                board[bSize - 1][0].getPlayerNumber() != -1) {
              if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber() &&
                  board[0][1].getPlayerNumber() == board[0][bSize - 1].getPlayerNumber() &&
                  board[0][1].getPlayerNumber() == board[bSize - 1][0].getPlayerNumber() &&
                  board[0][0].getPlayerNumber() != board[0][1].getPlayerNumber()) {
                return board[0][1].getPlayerNumber();
              }
            }
          }

          // top-right corner case (check 4 sides)
          if (row == 0 && col == bSize - 1) {
            if (board[0][bSize - 2].getPlayerNumber() != -1 &&
                board[1][bSize - 1].getPlayerNumber() != -1 &&
                board[0][0].getPlayerNumber() != -1 &&
                board[bSize - 1][bSize - 1].getPlayerNumber() != -1) {
              if (board[0][bSize - 2].getPlayerNumber() == board[1][bSize - 1].getPlayerNumber() &&
                  board[0][bSize - 2].getPlayerNumber() == board[0][0].getPlayerNumber() &&
                  board[0][bSize - 2].getPlayerNumber() ==
                      board[bSize - 1][bSize - 1].getPlayerNumber() &&
                  board[0][bSize - 1].getPlayerNumber() != board[0][bSize - 2].getPlayerNumber()) {
                return board[0][bSize - 2].getPlayerNumber();
              }
            }
          }

          // bottom-left corner case (check 4 sides)
          if (row == bSize - 1 && col == 0) {
            if (board[bSize - 2][0].getPlayerNumber() != -1 &&
                board[bSize - 1][1].getPlayerNumber() != -1 &&
                board[0][0].getPlayerNumber() != -1 &&
                board[bSize - 1][bSize - 1].getPlayerNumber() != -1) {
              if (board[bSize - 2][0].getPlayerNumber() == board[bSize - 1][1].getPlayerNumber() &&
                  board[bSize - 2][0].getPlayerNumber() == board[0][0].getPlayerNumber() &&
                  board[bSize - 2][0].getPlayerNumber() ==
                      board[bSize - 1][bSize - 1].getPlayerNumber() &&
                  board[bSize - 1][0].getPlayerNumber() != board[bSize - 2][0].getPlayerNumber()) {
                return board[bSize - 2][0].getPlayerNumber();
              }
            }
          }

          // bottom-right corner case (check 4 sides)
          if (row == bSize - 1 && col == bSize - 1) {
            if (board[bSize - 2][bSize - 1].getPlayerNumber() != -1 &&
                board[bSize - 1][bSize - 2].getPlayerNumber() != -1 &&
                board[0][bSize - 1].getPlayerNumber() != -1 &&
                board[bSize - 1][0].getPlayerNumber() != -1) {
              if (board[bSize - 2][bSize - 1].getPlayerNumber() ==
                  board[bSize - 1][bSize - 2].getPlayerNumber() &&
                  board[bSize - 2][bSize - 1].getPlayerNumber() ==
                      board[0][bSize - 1].getPlayerNumber() &&
                  board[bSize - 2][bSize - 1].getPlayerNumber() ==
                      board[bSize - 1][0].getPlayerNumber() &&
                  board[bSize - 1][bSize - 1].getPlayerNumber() !=
                      board[bSize - 2][bSize - 1].getPlayerNumber()) {
                return board[bSize - 2][bSize - 1].getPlayerNumber();
              }
            }
          }

          // left-border case (excluding corners - check 4 sides)
          if (row != 0 && row != bSize - 1 && col == 0) {
            if (board[row - 1][col].getPlayerNumber() != -1 &&
                board[row][col + 1].getPlayerNumber() != -1 &&
                board[row + 1][col].getPlayerNumber() != -1 &&
                board[row][bSize - 1].getPlayerNumber() != -1) {
              if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                  board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                  board[row - 1][col].getPlayerNumber() ==
                      board[row][bSize - 1].getPlayerNumber() &&
                  board[row][col].getPlayerNumber() != board[row - 1][col].getPlayerNumber()) {
                return board[row - 1][col].getPlayerNumber();
              }
            }
          }

          // top-border case (excluding corners - check 4 sides)
          if (row == 0 && col != 0 && col != bSize - 1) {
            if (board[row][col - 1].getPlayerNumber() != -1 &&
                board[row + 1][col].getPlayerNumber() != -1 &&
                board[row][col + 1].getPlayerNumber() != -1 &&
                board[bSize - 1][col].getPlayerNumber() != -1) {
              if (board[row][col - 1].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                  board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                  board[row][col - 1].getPlayerNumber() ==
                      board[bSize - 1][col].getPlayerNumber() &&
                  board[row][col].getPlayerNumber() != board[row][col - 1].getPlayerNumber()) {
                return board[row][col - 1].getPlayerNumber();
              }
            }
          }

          // right-border case (excluding corners - check 4 sides)
          if (row != 0 && row != bSize - 1 && col == bSize - 1) {
            if (board[row - 1][col].getPlayerNumber() != -1 &&
                board[row][col - 1].getPlayerNumber() != -1 &&
                board[row + 1][col].getPlayerNumber() != -1 &&
                board[row][0].getPlayerNumber() != -1) {
              if (board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
                  board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                  board[row - 1][col].getPlayerNumber() == board[row][0].getPlayerNumber() &&
                  board[row][col].getPlayerNumber() != board[row - 1][col].getPlayerNumber()) {
                return board[row - 1][col].getPlayerNumber();
              }
            }
          }

          // bottom-border case (excluding corners - check 4 sides)
          if (row == bSize - 1 && col != 0 && col != bSize - 1) {
            if (board[row][col - 1].getPlayerNumber() != -1 &&
                board[row - 1][col].getPlayerNumber() != -1 &&
                board[row][col + 1].getPlayerNumber() != -1 &&
                board[0][col].getPlayerNumber() != -1) {
              if (board[row][col - 1].getPlayerNumber() == board[row - 1][col].getPlayerNumber() &&
                  board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                  board[row][col - 1].getPlayerNumber() == board[0][col].getPlayerNumber() &&
                  board[row][col].getPlayerNumber() != board[row][col - 1].getPlayerNumber()) {
                return board[row][col - 1].getPlayerNumber();
              }
            }
          }

          // center case (excluding sides and corners - check 4 sides)
          if (row != 0 && row != bSize - 1 && col != 0 && col != bSize - 1) {
            if (board[row - 1][col].getPlayerNumber() != -1 &&
                board[row][col - 1].getPlayerNumber() != -1 &&
                board[row + 1][col].getPlayerNumber() != -1 &&
                board[row][col + 1].getPlayerNumber() != -1) {
              if (board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
                  board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                  board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                  board[row][col].getPlayerNumber() != board[row - 1][col].getPlayerNumber()) {
                return board[row - 1][col].getPlayerNumber();
              }
            }
          }
        }
      }
    }
    return -1;
  }

  /**
   * nextAiMove plays the next move for the AI.
   */
  public void nextAiMove() {
    int randomAiInt = random.nextInt(4);
    for (int r = 0; r < bSize; ++r) {
      for (int c = 0; c < bSize; ++c) {

        // check won
        if (board[r][c].getPlayerNumber() == -1) {
          board[r][c].setPlayerNumber(0);
          if (isWinner() == 0) {
            return;
          } else {
            board[r][c].setPlayerNumber(-1);
          }
        }

        // check block
        if (board[r][c].getPlayerNumber() == -1) {
          board[r][c].setPlayerNumber(1);
          if (!(isWinner() == 1)) {
            board[r][c].setPlayerNumber(-1);
          } else {
            board[r][c].setPlayerNumber(0);
            return;
          }
        }

        // if the cell does not equals a default or does not equal 0
        if (board[r][c].getPlayerNumber() != -1 && board[r][c].getPlayerNumber() != 0) {
          switch (randomAiInt) {
            case 0:
              try {
                if (board[r + 1][c].getPlayerNumber() == -1) {
                  board[r + 1][c].setPlayerNumber(0);
                  return;
                }
              } catch (IndexOutOfBoundsException e) {
                ++randomAiInt;
              }

            case 1:
              try {
                if (board[r - 1][c].getPlayerNumber() == -1) {
                  board[r - 1][c].setPlayerNumber(0);
                  return;
                }
              } catch (IndexOutOfBoundsException e) {
                ++randomAiInt;
              }

            case 2:
              try {
                if (board[r][c + 1].getPlayerNumber() == -1) {
                  board[r][c + 1].setPlayerNumber(0);
                  return;
                }
              } catch (IndexOutOfBoundsException e) {
                ++randomAiInt;
              }

            case 3:
              try {
                if (board[r][c - 1].getPlayerNumber() == -1) {
                  board[r][c - 1].setPlayerNumber(0);
                  return;
                }
              } catch (IndexOutOfBoundsException e) {
                randomAiInt = 0;
              }
          }
        }
      }
    }
  }

  public Cell getCell(int row, int col) {
    return board[row][col];
  }

  public int getCurrentPlayer() {
    return cPlayer;
  }

  public void setBoardCell(int row, int col) {
    board[row][col].setPlayerNumber(cPlayer);
  }
}