package surroundpack;

import java.util.Random;

public class AI{

  private int AI;

  private Cell[][] board;

  private Surround4Game game;

  public AI(int aPlayer) {
    AI = aPlayer;
  }

  public Cell[][] startingMove() {
    Random r = new Random();
    int[] randomRowChoice = new int[game.getbSize()];
    int[] randomColChoice = new int[game.getbSize()];
    for (int row = 0; row < game.getbSize() - 1; row++) {
      for (int col = 0; col < game.getbSize() - 1; col++) {
        randomRowChoice[row] = row;
        randomColChoice[col] = col;
      }
    }
    int randChoice = r.nextInt(game.getbSize());
    return new Cell[randomRowChoice[randChoice]][randomColChoice[randChoice]];
  }

  public Cell[][] calculatedMove() {
    int[] calculatedRowChoice = new int[game.getbSize()];
    int[] calculatedColChoice = new int[game.getbSize()];
    for (int row = 0; row < game.getbSize() - 1; row++) {
      for (int col = 0; col < game.getbSize() - 1; col++) {
        calculatedRowChoice[row] = row;
        calculatedColChoice[col] = col;
      }
    }
    for (int row = 0; row < game.getbSize() - 1; row++) {
      for (int col = 0; col < game.getbSize() - 1; col++) {
        if (game.isWinner() == -1) {
          return new Cell[calculatedRowChoice[row]][calculatedColChoice[col]];
        }
      }
    }
    return null;
  }
}
