package surroundpack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**********************************************************************************************
 * This program creates a game called Surround, the objective of the game is to surround an
 * opponent with tiles. It features an optional AI opponent and border wrapping so you can
 * surround your opponent by placing tiles on opposite sides of the board
 *
 * @author Austin Ackerman
 * @author Evan Johns
 * @version 02/18/2020 11:19
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

  /* user input Ai activation */
  private int activateAi;

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
    //panel.setBackground(Color.DARK_GRAY);

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

        // Formatting Button
        board[i][j].setForeground(Color.DARK_GRAY);
        board[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
        board[i][j].setFont(new java.awt.Font("Arial", Font.BOLD, 14));

        panel.add(board[i][j]);
      }
    }
  }

  private void displayBoard() {
    for (int row = 0; row < bSize; row++) {
      for (int col = 0; col < bSize; col++) {
        if (game.getCell(row, col).getPlayerNumber() != -1) {
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
      JOptionPane.showMessageDialog(null, "Invalid entry: Default = 10");
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
      JOptionPane.showMessageDialog(null, "Invalid entry: Default = 2");
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

    // Activate Ai
    String strActivateAi = JOptionPane.showInputDialog(null, "Would you like to play against" +
        " the Ai? (0 = no, 1 = yes)");
    try {
      activateAi = Integer.parseInt(strActivateAi);
      if (activateAi < 0 || activateAi > 1) {
        throw new IllegalArgumentException();
      }
      JOptionPane.showMessageDialog(null, "You chose to activate Ai, starting player will default" +
          " to 1");
      sPlayer = 1;
    } catch (Exception e) {
      activateAi = 0;
      JOptionPane.showMessageDialog(null, "Invalid entry: Ai deactivated");
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
              game.setBoardCell(row, col);
              board[row][col].setText("" + game.getCurrentPlayer());
              game.nextPlayer();
            } else {
              JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");
            }
          }
        }
      }

      displayBoard();

      // Checks to see if it's the Ai's turn
      if (game.getCurrentPlayer() == 0 && activateAi == 1) {
        game.nextAiMove();
        game.nextPlayer();
        displayBoard();
      }

      // Checks for winner
      if (game.isWinner() != -1) {
        JOptionPane.showMessageDialog(null, "Player " + game.isWinner() + " Wins!");
        game = new Surround4Game(bSize, nPlayers, sPlayer);
        displayBoard();
      }
    }
  }

  /*********************************************************************************************
   * Main method which starts the Surround 4 Game
   *********************************************************************************************/
  public static void main(String[] args) {
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem newGameItem;

    JFrame frame = new JFrame("Surround game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    fileMenu = new JMenu("File");
    quitItem = new JMenuItem("quit");
    newGameItem = new JMenuItem("new game");

    Surround4Panel panel = new Surround4Panel(quitItem, newGameItem);

    fileMenu.add(quitItem);
    fileMenu.add(newGameItem);

    menus = new JMenuBar();
    menus.add(fileMenu);

    frame.setJMenuBar(menus);

    frame.add(panel);
    frame.setSize(600, 600);
    frame.setVisible(true);
  }
}

