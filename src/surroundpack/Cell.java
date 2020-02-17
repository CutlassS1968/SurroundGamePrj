package surroundpack;

/**********************************************************************************************
 * This program creates a game called Surround, the objective of the game is to surround an
 * opponent with tiles
 *
 * @author Austin Ackerman
 * @author Evan Johns
 * @version 02/07/2020 00:25:00
 **********************************************************************************************/

public class Cell {
  /**
   * player number for corresponding cell
   */
  private int playerNumber;

  /*********************************************************************************************
   * initialises all cell elements
   * @param playerNumber
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
