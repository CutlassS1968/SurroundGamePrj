package surroundpack;

/**********************************************************************************************
 * This program creates a game called Surround, the objective of the game is to surround an
 * opponent with tiles
 *
 * @author Austin Ackerman
 * @author Evan Johns
 * @version 02/18/2020 11:19
 **********************************************************************************************/

public class Cell {
  /* player number for corresponding cell */
  private int playerNumber;

  /*********************************************************************************************
   * Default constructor for empty cells
   *********************************************************************************************/
  public Cell() {
    playerNumber = -1;
  }

  /*********************************************************************************************
   * Constructor for cells with player number imported
   * @param playerNumber is the cell's player number
   *********************************************************************************************/
  public Cell(int playerNumber) {
    this.playerNumber = playerNumber;
  }

  public int getPlayerNumber() {
    return playerNumber;
  }

  public void setPlayerNumber(int playerNumber) {
    this.playerNumber = playerNumber;
  }
}
