package surroundpack;

import javax.swing.*;

public class AI extends Surround4Game {

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
        if (board[row][col] == null) {

          // checks 2 places
          // upper placement - 2 (place at row, col and two places above and check win)
          if (row != 0) {
            board[row][col] = new Cell(0);
            board[row - 2][col] = new Cell(0);
            if (game.isWinner() == 0) {
              board[row - 2][col] = null;
            } else {
              board[row][col] = null;
              board[row - 2][col] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[bSize - 2][col] = new Cell(0);
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
              board[row][col + 2] = null;
            } else {
              board[row][col] = null;
              board[row][col + 2] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row][1] = new Cell(0);
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
              board[row + 2][col] = null;
            } else {
              board[row][col] = null;
              board[row + 2][col] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[1][col] = new Cell(0);
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
              board[row][col - 2] = null;
            } else {
              board[row][col] = null;
              board[row][col - 2] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[row][bSize - 2] = new Cell(0);
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
              board[row - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col - 1] = null;
            }
          } else if (row == 0 && col != 0) {
            board[row][col] = new Cell(0);
            board[bSize - 1][col - 1] = new Cell(0);
            if (game.isWinner() == 0) {
              board[bSize - 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][col - 1] = null;
            }
          } else if (row != 0) {
            board[row][col] = new Cell(0);
            board[row - 1][bSize - 1] = new Cell(0);
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
              board[row - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row - 1][col + 1] = null;
            }
          } else if (row == 0 && col != bSize) {
            board[row][col] = new Cell(0);
            board[bSize - 1][col + 1] = new Cell(0);
            if (game.isWinner() == 0) {
              board[bSize - 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[bSize - 1][col + 1] = null;
            }
          } else if (row != 0) {
            board[row][col] = new Cell(0);
            board[row - 1][0] = new Cell(0);
            if (game.isWinner() == 0) {
              board[row - 1][0] = null;
            } else {
              board[row][col] = null;
              board[row - 1][0] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[bSize - 1][0] = new Cell(0);
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
              board[row + 1][col - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 1][col - 1] = null;
            }
          } else if (row == bSize && col != 0) {
            board[row][col] = new Cell(0);
            board[0][col - 1] = new Cell(0);
            if (game.isWinner() == 0) {
              board[0][col - 1] = null;
            } else {
              board[row][col] = null;
              board[0][col - 1] = null;
            }
          } else if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row + 1][bSize - 1] = new Cell(0);
            if (game.isWinner() == 0) {
              board[row + 1][bSize - 1] = null;
            } else {
              board[row][col] = null;
              board[row + 1][bSize - 1] = null;
            }
          } else {
            board[row][col] = new Cell(0);
            board[0][bSize - 1] = new Cell(0);
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
              board[row + 1][col + 1] = null;
            } else {
              board[row][col] = null;
              board[row + 1][col + 1] = null;
            }
          } else if (row == bSize && col != bSize) {
            board[row][col] = new Cell(0);
            board[0][col + 1] = new Cell(0);
            if (game.isWinner() == 0) {
              board[0][col + 1] = null;
            } else {
              board[row][col] = null;
              board[0][col + 1] = null;
            }
          } else if (row != bSize) {
            board[row][col] = new Cell(0);
            board[row + 1][0] = new Cell(0);
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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
            if (game.isWinner() == 0) {
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