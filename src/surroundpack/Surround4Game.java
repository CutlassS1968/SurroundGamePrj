package surroundpack;

/**********************************************************************************************
 * Surround4Game contains the core functions of the game
 *
 * @author Austin Ackerman
 * @author Evan Johns
 * @version 02/07/2020 00:25:00
 **********************************************************************************************/

public class Surround4Game {

  /* structure for game board */
  protected Cell[][] board;

  /* current player */
  protected int cPlayer;

  /* user input size for board */
  protected int bSize;

  /* user input number of players */
  protected int nPlayers;

  /* user input AI activation */
  protected int activateAI;

  /*********************************************************************************************
   * Instantiates Surround4Game's instance variables
   *
   * @param bSize int - Size of the board
   * @param nPlayers int - Number of players
   * @param sPlayer int - Starting player
   *********************************************************************************************/
  public Surround4Game(int bSize, int nPlayers, int sPlayer, int activateAI) {
    board = new Cell[bSize][bSize];
    this.cPlayer = sPlayer;
    this.bSize = bSize;
    this.nPlayers = nPlayers;
    this.activateAI = activateAI;
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
    if (board[row][col] == null) {
      board[row][col] = new Cell(cPlayer);
      return true;
    } else {
      return false;
    }
  }

  /**
   * getWinner checks to see if a player has won and returns that player
   *
   * @return int - returns the winner
   */
  public int isWinner() {
    for (int row = 0; row < bSize; row++) {
      for (int col = 0; col < bSize; col++) {
        if (board[row][col] != null) {

          // top-left corner case (check 4 sides)
          if (row == 0 && col == 0) {
            if (board[0][1] != null && board[1][0] != null && board[0][bSize - 1] != null &&
                board[bSize - 1][0] != null) {
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
            if (board[0][bSize - 2] != null && board[1][bSize - 1] != null && board[0][0] != null &&
                board[bSize - 1][bSize - 1] != null) {
              if (board[0][bSize - 2].getPlayerNumber() == board[1][bSize - 1].getPlayerNumber() &&
                  board[0][bSize - 2].getPlayerNumber() == board[0][0].getPlayerNumber() &&
                  board[0][bSize - 2].getPlayerNumber() == board[bSize - 1][bSize - 1].getPlayerNumber() &&
                  board[0][bSize - 1].getPlayerNumber() != board[0][bSize - 2].getPlayerNumber()) {
                return board[0][bSize - 2].getPlayerNumber();
              }
            }
          }

          // bottom-left corner case (check 4 sides)
          if (row == bSize - 1 && col == 0) {
            if (board[bSize - 2][0] != null && board[bSize - 1][1] != null && board[0][0] != null &&
                board[bSize - 1][bSize - 1] != null) {
              if (board[bSize - 2][0].getPlayerNumber() == board[bSize - 1][1].getPlayerNumber() &&
                  board[bSize - 2][0].getPlayerNumber() == board[0][0].getPlayerNumber() &&
                  board[bSize - 2][0].getPlayerNumber() == board[bSize - 1][bSize - 1].getPlayerNumber() &&
                  board[bSize - 1][0].getPlayerNumber() != board[bSize - 2][0].getPlayerNumber()) {
                return board[bSize - 2][0].getPlayerNumber();
              }
            }
          }

          // bottom-right corner case (check 4 sides)
          if (row == bSize - 1 && col == bSize - 1) {
            if (board[bSize - 2][bSize - 1] != null && board[bSize - 1][bSize - 2] != null &&
                board[0][bSize - 1] != null && board[bSize - 1][0] != null) {
              if (board[bSize - 2][bSize - 1].getPlayerNumber() == board[bSize - 1][bSize - 2].getPlayerNumber() &&
                  board[bSize - 2][bSize - 1].getPlayerNumber() == board[0][bSize - 1].getPlayerNumber() &&
                  board[bSize - 2][bSize - 1].getPlayerNumber() == board[bSize - 1][0].getPlayerNumber() &&
                  board[bSize - 1][bSize - 1].getPlayerNumber() != board[bSize - 2][bSize - 1].getPlayerNumber()) {
                return board[bSize - 2][bSize - 1].getPlayerNumber();
              }
            }
          }

          // left-border case (excluding corners - check 4 sides)
          if (row != 0 && row != bSize - 1 && col == 0) {
            if (board[row - 1][col] != null && board[row][col + 1] != null &&
                board[row + 1][col] != null && board[row][bSize - 1] != null) {
              if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                  board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                  board[row - 1][col].getPlayerNumber() == board[row][bSize - 1].getPlayerNumber() &&
                  board[row][col].getPlayerNumber() != board[row - 1][col].getPlayerNumber()) {
                return board[row - 1][col].getPlayerNumber();
              }
            }
          }

          // top-border case (excluding corners - check 4 sides)
          if (row == 0 && col != 0 && col != bSize - 1) {
            if (board[row][col - 1] != null && board[row + 1][col] != null &&
                board[row][col + 1] != null && board[bSize - 1][col] != null) {
              if (board[row][col - 1].getPlayerNumber() == board[row + 1][col].getPlayerNumber() &&
                  board[row][col - 1].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
                  board[row][col - 1].getPlayerNumber() == board[bSize - 1][col].getPlayerNumber() &&
                  board[row][col].getPlayerNumber() != board[row][col - 1].getPlayerNumber()) {
                return board[row][col - 1].getPlayerNumber();
              }
            }
          }

          // right-border case (excluding corners - check 4 sides)
          if (row != 0 && row != bSize - 1 && col == bSize - 1) {
            if (board[row - 1][col] != null && board[row][col - 1] != null &&
                board[row + 1][col] != null && board[row][0] != null) {
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
            if (board[row][col - 1] != null && board[row - 1][col] != null &&
                board[row][col + 1] != null && board[0][col] != null) {
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
            if (board[row - 1][col] != null && board[row][col - 1] != null &&
                board[row + 1][col] != null &&
                board[row][col + 1] != null) {
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

  // Double Star recursive to yourself and then check surround and make sure not to recursive backwards

  public Cell getCell(int row, int col) {
    return board[row][col];
  }

  public Cell setCell(int row, int col, Cell cell) {
    return board[row][col] = cell;
  }

  public int getCurrentPlayer() {
    return cPlayer;
  }

  public void setCurrentPlayer(int cPlayer) {
    this.cPlayer = cPlayer;
  }

  public int getNumberPlayers() {
    return nPlayers;
  }

  public void setNumberPlayers(int nPlayers) {
    this.nPlayers = nPlayers;
  }

  public int getbSize() {
    return bSize;
  }

  public void setbSize(int bSize) {
    this.bSize = bSize;
  }

  public int getActivateAI() {
    return activateAI;
  }

  public void setActivateAI(int activateAI) {
    this.activateAI = activateAI;
  }

  // Makes the move for AI
  public void nextAIMove() {
    for (int row = 0; row < bSize; row++) {
      for (int col = 0; col < bSize; col++) {
        if (board[row][col] == null) {

          // check won
          board[row][col] = new Cell(0);
          if (!(isWinner() == 0)) {
            board[row][col] = null;
          }

          // check block
          board[row][col] = new Cell(1);
          if (!(isWinner() == 1)) {
            board[row][col] = null;
          } else {
            board[row][col] = new Cell(0);
          }

          // check solo
          // checks 2 places
          // upper placement - 2 (place at row, col and two places above and check win)
          if (row != 0) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[bSize - 2][col] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 2][col] = null;
            } else {
              board[row][col] = null;
              board[bSize - 2][col] = null;
            }
          }

          // right placement - 2 (place at row, col and two places to the right and check win)
          if (col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row][1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][1] = null;
            } else {
              board[row][col] = null;
              board[row][1] = null;
            }
          }

          // bottom placement - 2 (place at row, col and two places below and check win)
          if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[1][col] = new Cell(0);
            if (isWinner() == 0) {
              board[1][col] = null;
            } else {
              board[row][col] = null;
              board[1][col] = null;
            }
          }

          // left placement - 2 (place at row, col and two places to the left and check win)
          if (col != 0) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row][bSize - 2] = new Cell(0);
            if (isWinner() == 0) {
              board[row][bSize - 2] = null;
            } else {
              board[row][col] = null;
              board[row][bSize - 2] = null;
            }
          }

          // diagonal upper left - 2 (place at row, col and one place up and to the left and checks win)
          if (row != 0 && col != 0) {
            board[row][col] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col - 1] = null;
            }
          } else if (row == 0 && col != 0) {
            board[row][col] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else if (row != 0) {
            board[row][col] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][bSize - 1] = null;
            }
          }

          // diagonal upper right - 2 (place at row, col and one place up and to the right and checks win)
          if (row != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (row == 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[bSize - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][col + 1] = null;
            }
          } else if (row != 0) {
            board[row][col] = new Cell(0);
            board[row - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][0] = null;
            } else {
              board[row][col] = null;
              board[row - 1][0] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[bSize - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 1][0] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][0] = null;
            }
          }

          // diagonal lower left - 2 (place at row, col and one place down and to the left and checks win)
          if (row != bSize && col != 0) {
            board[row][col] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row == bSize && col != 0) {
            board[row][col] = new Cell(0);
            board[0][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[0][col - 1] = null;
            } else {
              board[row][col] = null;
              board[0][col - 1] = null;
            }
          } else if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row + 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 1][bSize - 1] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[0][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[0][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[0][bSize - 1] = null;
            }
          }

          // diagonal lower right - 2 (place at row, col and one place down and to the right and checks win)
          if (row != bSize && col != bSize) {
            board[row][col] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row + 1][col + 1] = null;
            }
          } else if (row == bSize && col != bSize) {
            board[row][col] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[0][col + 1] = null;
            } else {
              board[row][col] = null;
              board[0][col + 1] = null;
            }
          } else if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 1][0] = null;
            } else {
              board[row][col] = null;
              board[row + 1][0] = null;
            }
          }

          // checks 3 places
          // upper left - 3 (checks up and to the left and two places up)
          if (row != 0 && col != 0) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][col - 1] = null;
            }
          } else if (row == 0 && col != 0) {
            board[row][col] = new Cell(0);
            board[bSize - 2][col] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 2][col] = null;
              board[bSize - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 2][col] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else if (row != 0) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][bSize - 1] = null;
            }
          }

          // upper right - 3 (checks up and to the right and two places up)
          if (row != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (row == 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[bSize - 2][col] = new Cell(0);
            board[bSize - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 2][col] = null;
              board[bSize - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 2][col] = null;
              board[bSize - 1][col + 1] = null;
            }
          } else if (row != 0) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][0] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][0] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[bSize - 2][col] = new Cell(0);
            board[bSize - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 2][col] = null;
              board[bSize - 1][0] = null;
            } else {
              board[row][col] = null;
              board[bSize - 2][col] = null;
              board[bSize - 1][0] = null;
            }
          }

          // upper right and left - 3 (checks up and to the right and up and to the left)
          if (row != 0 && col != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][col - 1] = null;
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col - 1] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (row == 0 && col == bSize) {
            board[row][col] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            board[bSize - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 1][col - 1] = null;
              board[bSize - 1][0] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][col - 1] = null;
              board[bSize - 1][0] = null;
            }
          } else if (row != 0 && col == 0 && col != bSize){
            board[row][col] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][bSize -  1] = null;
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][bSize - 1] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (row != 0 && col != 0) {
            board[row][col] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            board[row - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][col - 1] = null;
              board[row - 1][0] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col - 1] = null;
              board[row - 1][0] = null;
            }
          } else if (row == 0) {
            board[row][col] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            board[bSize - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 1][col - 1] = null;
              board[bSize - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][col - 1] = null;
              board[bSize - 1][col + 1] = null;
            }
          }

          // lower left - 3 (checks down and to the left and two places down)
          if (row != bSize && col != 0) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row == bSize && col != 0) {
            board[row][col] = new Cell(0);
            board[1][col] = new Cell(0);
            board[0][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[1][col] = null;
              board[0][col - 1] = null;
            } else {
              board[row][col] = null;
              board[1][col] = null;
              board[0][col - 1] = null;
            }
          } else if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][bSize - 1] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[1][col] = new Cell(0);
            board[0][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[1][col] = null;
              board[0][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[1][col] = null;
              board[0][bSize - 1] = null;
            }
          }

          // lower right - 3 (checks down and to the right and two places down)
          if (row != bSize && col != bSize) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][col + 1] = null;
            }
          } else if (row == bSize && col != bSize) {
            board[row][col] = new Cell(0);
            board[bSize + 2][col] = new Cell(0);
            board[bSize + 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize + 2][col] = null;
              board[bSize + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[bSize + 2][col] = null;
              board[bSize + 1][col + 1] = null;
            }
          } else if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][0] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][0] = null;
            }
          }

          // lower right and left - 3 (checks down and to the right and down and to the left)
          if (row != bSize && col != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 1][col - 1] = null;
              board[row + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row + 1][col - 1] = null;
              board[row + 1][col + 1] = null;
            }
          } else if (row == bSize && col == 0) {
            board[row][col] = new Cell(0);
            board[0][bSize - 1] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[0][bSize - 1] = null;
              board[0][col + 1] = null;
            } else {
              board[row][col] = null;
              board[0][bSize - 1] = null;
              board[0][col + 1] = null;
            }
          } else if (row != bSize && col == 0 && col != bSize){
            board[row][col] = new Cell(0);
            board[row + 1][bSize - 1] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 1][bSize -  1] = null;
              board[row + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row + 1][bSize - 1] = null;
              board[row + 1][col + 1] = null;
            }
          } else if (row != bSize && col != 0) {
            board[row][col] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 1][col - 1] = null;
              board[row + 1][0] = null;
            } else {
              board[row][col] = null;
              board[row + 1][col - 1] = null;
              board[row + 1][0] = null;
            }
          } else if (row == bSize) {
            board[row][col] = new Cell(0);
            board[0][col - 1] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[0][col - 1] = null;
              board[0][col + 1] = null;
            } else {
              board[row][col] = null;
              board[0][col - 1] = null;
              board[0][col + 1] = null;
            }
          }

          // left most down - 3 (checks down and to the left and two places left)
          if (row != bSize && col != 0) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row == bSize && col != 0) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[0][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[0][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[0][col - 1] = null;
            }
          } else if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row][bSize  - 2] = new Cell(0);
            board[row + 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][bSize - 2] = null;
              board[row + 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row][bSize - 2] = null;
              board[row + 1][bSize - 1] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row][bSize  - 2] = new Cell(0);
            board[0][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][bSize - 2] = null;
              board[0][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row][bSize - 2] = null;
              board[0][bSize - 1] = null;
            }
          }

          // left most up - 3 (checks up and to the left and two places left)
          if (row != 0 && col != 0) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[row - 1][col - 1] = null;
            }
          } else if (row == 0) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[bSize - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row][bSize  - 2] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][bSize - 2] = null;
              board[row - 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row][bSize - 2] = null;
              board[row - 1][bSize - 1] = null;
            }
          }

          // left most up and down - 3 (checks up and to the left and down and to the left)
          if (row != 0 && col != 0 && row != bSize) {
            board[row][col] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][col - 1] = null;
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col - 1] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row == bSize && col == 0) {
            board[row][col] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            board[0][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][bSize - 1] = null;
              board[0][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][bSize - 1] = null;
              board[0][bSize - 1] = null;
            }
          } else if (row != bSize && col != 0){
            board[row][col] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 1][col - 1] = null;
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][col - 1] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row == bSize) {
            board[row][col] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            board[0][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][col - 1] = null;
              board[0][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col - 1] = null;
              board[0][col - 1] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            board[row + 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][bSize - 1] = null;
              board[row + 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][bSize - 1] = null;
              board[row + 1][bSize - 1] = null;
            }
          }

          // right most down - 3 (checks down and to the right and two places right)
          if (row != bSize && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[row + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
              board[row + 1][col + 1] = null;
            }
          } else if (row == bSize && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[0][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
              board[0][col + 1] = null;
            }
          } else if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row][1] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row][1] = null;
              board[row + 1][0] = null;
            } else {
              board[row][col] = null;
              board[row][1] = null;
              board[row + 1][0] = null;
            }
          }

          // right most up - 3 (checks up and to the right and two places right)
          if (row != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (row == 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[bSize - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[bSize - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else if (row != 0) {
            board[row][col] = new Cell(0);
            board[row][1] = new Cell(0);
            board[row - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row][1] = null;
              board[row - 1][0] = null;
            } else {
              board[row][col] = null;
              board[row][1] = null;
              board[row - 1][0] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row][1] = new Cell(0);
            board[bSize - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row][1] = null;
              board[bSize - 1][0] = null;
            } else {
              board[row][col] = null;
              board[row][1] = null;
              board[bSize - 1][0] = null;
            }
          }

          // right most up and down - 3 (checks up and to the right and down and to the right)
          if (row != 0 && col != bSize && row != bSize) {
            board[row][col] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][col + 1] = null;
              board[row + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col + 1] = null;
              board[row + 1][col + 1] = null;
            }
          } else if (row != bSize && col != bSize){
            board[row][col] = new Cell(0);
            board[bSize - 1][col + 1] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 1][col + 1] = null;
              board[row + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][col + 1] = null;
              board[row + 1][col + 1] = null;
            }
          } else if (row == bSize) {
            board[row][col] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][col + 1] = null;
              board[0][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col + 1] = null;
              board[0][col + 1] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row - 1][0] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 1][0] = null;
              board[row + 1][0] = null;
            } else {
              board[row][col] = null;
              board[row - 1][0] = null;
              board[row + 1][0] = null;
            }
          }

          // checks all four
          // checks upper 4
          if (row != 0 && row != bSize && col != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
              board[row - 1][col - 1] = null;
            }
          } else if (row == 0 && col == bSize) {
            board[row][col] = new Cell(0);
            board[bSize - 2][col] = new Cell(0);
            board[bSize - 1][0] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 2][col] = null;
              board[bSize - 1][0] = null;
              board[bSize - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 2][col] = null;
              board[bSize - 1][0] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else if (row != 0 && row == bSize && col == 0) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
              board[row - 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
              board[row - 1][bSize - 1] = null;
            }
          } else if (row == 0) {
            board[row][col] = new Cell(0);
            board[bSize - 2][col] = new Cell(0);
            board[bSize - 1][col + 1] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[bSize - 2][col] = null;
              board[bSize - 1][col + 1] = null;
              board[bSize - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 2][col] = null;
              board[bSize - 1][col + 1] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else if (row == bSize) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
              board[row - 1][col - 1] = null;
            }
          } else if (col == 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
              board[row - 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][col + 1] = null;
              board[row - 1][bSize - 1] = null;
            }
          } else if (col != 0 && col == bSize) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            board[row - 1][0] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row - 2][col] = null;
              board[row - 1][0] = null;
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
              board[row - 1][0] = null;
              board[row - 1][col - 1] = null;
            }
          }

          // checks lower 4
          if (row != 0 && row != bSize && col != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][col + 1] = null;
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][col + 1] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row == 0 && col == bSize) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][0] = null;
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][0] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row != 0 && row == bSize && col == 0) {
            board[row][col] = new Cell(0);
            board[1][col] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            board[0][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[1][col] = null;
              board[0][col + 1] = null;
              board[0][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[1][col] = null;
              board[0][col + 1] = null;
              board[0][bSize - 1] = null;
            }
          } else if (row == 0) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][col + 1] = null;
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][col + 1] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row == bSize) {
            board[row][col] = new Cell(0);
            board[1][col] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            board[0][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[1][col] = null;
              board[0][col + 1] = null;
              board[0][col - 1] = null;
            } else {
              board[row][col] = null;
              board[1][col] = null;
              board[0][col + 1] = null;
              board[0][col - 1] = null;
            }
          } else if (col == 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            board[row + 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][col + 1] = null;
              board[row + 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][col + 1] = null;
              board[row + 1][bSize - 1] = null;
            }
          } else if (col != 0 && col == bSize) {
            board[row][col] = new Cell(0);
            board[row + 2][col] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row + 2][col] = null;
              board[row + 1][0] = null;
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
              board[row + 1][0] = null;
              board[row + 1][col - 1] = null;
            }
          }

          // checks left most 4
          if (row != 0 && row != bSize && col != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
              board[row - 1][col - 1] = null;
            }
          } else if (row == 0 && col == bSize) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
              board[bSize - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else if (row != 0 && row == bSize && col == 0) {
            board[row][col] = new Cell(0);
            board[row][bSize - 2] = new Cell(0);
            board[0][bSize - 1] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][bSize - 2] = null;
              board[0][bSize - 1] = null;
              board[row - 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row][bSize - 2] = null;
              board[0][bSize - 1] = null;
              board[row - 1][bSize - 1] = null;
            }
          } else if (row == 0 && row != bSize) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
              board[bSize - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else if (row == bSize && row != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[0][col - 1] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[0][col - 1] = null;
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[0][col - 1] = null;
              board[row - 1][col - 1] = null;
            }
          } else if (col == 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][bSize - 2] = new Cell(0);
            board[row + 1][bSize - 1] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][bSize - 2] = null;
              board[row + 1][bSize - 1] = null;
              board[row - 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row][bSize - 2] = null;
              board[row + 1][bSize - 1] = null;
              board[row - 1][bSize - 1] = null;
            }
          } else if (col != 0) {
            board[row][col] = new Cell(0);
            board[row][col - 2] = new Cell(0);
            board[row + 1][col - 1] = new Cell(0);
            board[row - 1][col - 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
              board[row + 1][col - 1] = null;
              board[row - 1][col - 1] = null;
            }
          }

          // checks right most 4
          if (row != 0 && row != bSize && col != 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[row + 1][col + 1] = null;
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
              board[row + 1][col + 1] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (row == 0 && col == bSize) {
            board[row][col] = new Cell(0);
            board[row][1] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            board[bSize - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row][1] = null;
              board[row + 1][0] = null;
              board[bSize - 1][0] = null;
            } else {
              board[row][col] = null;
              board[row][1] = null;
              board[row + 1][0] = null;
              board[bSize - 1][0] = null;
            }
          } else if (row != 0 && row == bSize && col == 0) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[0][col + 1] = null;
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
              board[0][col + 1] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (row == 0) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            board[bSize - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[row + 1][col + 1] = null;
              board[bSize - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
              board[row + 1][col + 1] = null;
              board[bSize - 1][col + 1] = null;
            }
          } else if (row == bSize && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[0][col + 1] = null;
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
              board[0][col + 1] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (col == 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[row][col + 2] = new Cell(0);
            board[row + 1][col + 1] = new Cell(0);
            board[row - 1][col + 1] = new Cell(0);
            if (isWinner() == 0) {
              board[row][col + 2] = null;
              board[row + 1][col + 1] = null;
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
              board[row + 1][col + 1] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (col != 0) {
            board[row][col] = new Cell(0);
            board[row][1] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            board[row - 1][0] = new Cell(0);
            if (isWinner() == 0) {
              board[row][1] = null;
              board[row + 1][0] = null;
              board[row - 1][0] = null;
            } else {
              board[row][col] = null;
              board[row][1] = null;
              board[row + 1][0] = null;
              board[row - 1][0] = null;
            }
          }
        }
      }
    }
  }
}