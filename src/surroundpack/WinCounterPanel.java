package surroundpack;

import javax.swing.*;

public class WinCounterPanel extends JPanel {

  private JLabel [] labels;
  private int [] playerWins;

  public WinCounterPanel(int nPlayers) {
    labels = new JLabel[nPlayers];
    playerWins = new int[nPlayers];
    playerWinsSetup();
    labelsSetup();


  }

  private void playerWinsSetup() {
    for (int i = 0; i < playerWins.length; ++i) {
      playerWins[i] = 0;
    }
  }

  private void labelsSetup() {
    for (int i = 0; i < labels.length; ++i) {
      labels[i] = new JLabel(("Player " + Integer.toString(i)));
      add(labels[i]);
    }
  }

  public void addWinner(int player) {
    ++playerWins[player];
  }
}
