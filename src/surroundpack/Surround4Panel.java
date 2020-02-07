package surroundpack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**********************************************************************************************
 * This program creates a game called Surround, the objective of the game is to surround an
 * opponent with tiles
 *
 * @author      Austin Ackerman
 * @author      Evan Johns
 * @version     02/11/2020
 **********************************************************************************************/

public class Surround4Panel extends JPanel {

    private JButton[][] board;

    private int bSize;
    private int nPlayers;
    private int sPlayer;

    private JPanel panel;
    private ButtonListener listen;
    private JMenuItem quitItem;
    private JMenuItem newGameItem;

    private Surround4Game game;

    /******************************************************************************************
     * Instantiates Surround4Panel's instance variables
     * @param pQuitItem JMenuItem - item to quit out to program
     * @param pNewGameItem JMenuItem - item to start a new game
     ******************************************************************************************/
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

    private class ButtonListener implements ActionListener {

        /**
         * actionPerformed checks all user input actions and executes code accordingly
         *
         * @param e ActionEvent - what type of error is thrown
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitItem)
                System.exit(1);
            if (e.getSource() == newGameItem){

                setLayout(new BorderLayout());
                panel.removeAll();

                getValidNumbers();
                createBoard();
                add(panel, BorderLayout.CENTER);
                game = new Surround4Game(bSize, nPlayers, sPlayer);
                panel.revalidate();
                panel.repaint();
            }

            for (int row = 0; row < board.length; row++)
                for (int col = 0; col < board[0].length; col++)
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) { // If spot int the array is not occupied
                                board[row][col].setText(""+game.getCurrentPlayer());
                            game.nextPlayer();
                        } else
                            JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");

            displayBoard();
            if (game.getWinner() != -1) {
                JOptionPane.showMessageDialog(null, "Player " + game.getWinner() + " Wins!");
                game = new Surround4Game(bSize, nPlayers, sPlayer);
                displayBoard();

            }
        }
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
                if (game.getCell(row, col) != null)
                    board[row][col].setText("" + game.getCell(row, col).getPlayerNumber());
                else
                    board[row][col].setText("");
            }
        }
    }

    /** This method checks for valid board size, number of players, and the starting player */
    private void getValidNumbers () {

        // Board Size
        String strBdSize = JOptionPane.showInputDialog(null,"Enter in the size of the board: ");
        try {
            bSize = Integer.parseInt(strBdSize);
            if (bSize < 3 || bSize > 19)
                throw new IllegalArgumentException();
        }catch (Exception e) {
            bSize = 10;
            JOptionPane.showMessageDialog(null, "Invalid entry");
        }

        // Number of Players
        String strNumPlayers = JOptionPane.showInputDialog(null, "Enter the number of players: ");
        try {
            nPlayers = Integer.parseInt(strNumPlayers);
            if (nPlayers < 2 || nPlayers > 5)
                throw new IllegalArgumentException();
        }catch (Exception e) {
            nPlayers = 2;
            JOptionPane.showMessageDialog(null, "Invalid entry");
        }

        // Starting Player
        String strStartPlayer = JOptionPane.showInputDialog(null, "Who starts first? (default 0)");
        try {
            sPlayer = Integer.parseInt(strStartPlayer);
            if (sPlayer > nPlayers-1 || sPlayer < 0)
                throw new IllegalArgumentException();
        }catch (Exception e) {
            sPlayer = 0;
            JOptionPane.showMessageDialog(null, "Invalid entry");
        }
    }

}

