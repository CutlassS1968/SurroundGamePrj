package surroundpack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**********************************************************************************************
 * This program creates a game called Surround, the objective of the game is to surround an
 * opponent with tiles
 *
 * @author Austin Ackerman
 * @author Evan Johns
 * @version 02/07/2020 00:25:00
 **********************************************************************************************/

public class Surround4Panel extends JPanel {

  /* structure for game board */
  private JButton[][] board;

  /* user input size for board*/
  private int bSize;

  /* user input number of players */
  private int nPlayers;

  /* user input starting player */
  private int sPlayer;

  /* elements used in creating user interface */
  private JPanel panel;
  private ButtonListener listen;
  private JMenuItem quitItem;
  private JMenuItem newGameItem;

  /* declaration of the main game */
  private Surround4Game game;

  /*********************************************************************************************
   * Instantiates Surround4Panel's instance variables
   *
   * @param pQuitItem JMenuItem - item to quit out to program
   * @param pNewGameItem JMenuItem - item to start a new game
   *********************************************************************************************/
  public Surround4Panel(JMenuItem pQuitItem, JMenuItem pNewGameItem) {
    quitItem = pQuitItem;
    newGameItem = pNewGameItem;
    listen = new ButtonListener();

    setLayout(new BorderLayout());
    panel = new JPanel();

    getValidNumbers();
    createBoard();
    add(panel, BorderLayout.CENTER);
    game = new Surround4Game(bSize, nPlayers, sPlayer);
    quitItem.addActionListener(listen);
    newGameItem.addActionListener(listen);

  }

  private void createBoard() {
    board = new JButton[bSize][bSize];
    panel.setLayout(new GridLayout(bSize, bSize));

    for (int i = 0; i < bSize; i++) {
      for (int j = 0; j < bSize; j++) {
        board[i][j] = new JButton("");
        board[i][j].addActionListener(listen);
        panel.add(board[i][j]);
      }
    }
  }

  private void displayBoard() {
    for (int row = 0; row < bSize; row++) {
      for (int col = 0; col < bSize; col++) {
        if (game.getCell(row, col) != null) {
          board[row][col].setText("" + game.getCell(row, col).getPlayerNumber());
        } else {
          board[row][col].setText("");
        }
      }
    }
  }

  /**
   * retrieves and checks for valid board size, number of players, and the starting player
   */
  private void getValidNumbers() {

    // Board Size
    String strBdSize = JOptionPane.showInputDialog(null, "Enter in the size of the board: ");
    try {
      bSize = Integer.parseInt(strBdSize);
      if (bSize < 3 || bSize > 19) {
        throw new IllegalArgumentException();
      }
    } catch (Exception e) {
      bSize = 10;
      JOptionPane.showMessageDialog(null, "Invalid entry");
    }

    // Number of Players
    String strNumPlayers = JOptionPane.showInputDialog(null, "Enter the number of players: ");
    try {
      nPlayers = Integer.parseInt(strNumPlayers);
      if (nPlayers < 2 || nPlayers > 5) {
        throw new IllegalArgumentException();
      }
    } catch (Exception e) {
      nPlayers = 2;
      JOptionPane.showMessageDialog(null, "Invalid entry");
    }

    // Starting Player
    String strStartPlayer = JOptionPane.showInputDialog(null, "Who starts first? (default 0)");
    try {
      sPlayer = Integer.parseInt(strStartPlayer);
      if (sPlayer > nPlayers - 1 || sPlayer < 0) {
        throw new IllegalArgumentException();
      }
    } catch (Exception e) {
      sPlayer = 0;
      JOptionPane.showMessageDialog(null, "Invalid entry");
    }
  }

  /*********************************************************************************************
   *  This class is used to interpret user inputs within the window
   *********************************************************************************************/
  private class ButtonListener implements ActionListener {

    /**
     * interprets user inputs within the window and executes code accordingly
     *
     * @param e ActionEvent - what type of error is thrown
     */
    public void actionPerformed(ActionEvent e) {

      // Ends program
      if (e.getSource() == quitItem) {
        System.exit(1);
      }

      // Creates new game
      if (e.getSource() == newGameItem) {
        setLayout(new BorderLayout());
        panel.removeAll();
        getValidNumbers();
        createBoard();
        add(panel, BorderLayout.CENTER);
        game = new Surround4Game(bSize, nPlayers, sPlayer);
        panel.revalidate();
        panel.repaint();
      }

      // Scans board and changes corresponding cell
      for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board[0].length; col++) {
          if (board[row][col] == e.getSource()) {
            if (game.select(row, col)) {
              board[row][col].setText("" + game.getCurrentPlayer());
              game.nextPlayer();
            } else {
              JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");
            }
          }
        }
      }

      displayBoard();

      // Checks for winner
      if (game.isWinner() != -1) {
        JOptionPane.showMessageDialog(null, "Player " + game.isWinner() + " Wins!");
        game = new Surround4Game(bSize, nPlayers, sPlayer);
        displayBoard();
      }
    }
  }
}

