package surroundpack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Surround4Panel extends JPanel {

    private JButton[][] board;

    private int bSize;
    private int nPlayers;
    private int sPlayer;
    private JPanel panel1;
    private int player;
    private ButtonListener listen;
    private JMenuItem quitItem;
    private JMenuItem newGameItem;
    private Surround4Game game;

    public Surround4Panel(JMenuItem pQuitItem, JMenuItem pNewGameItem) {
        quitItem = pQuitItem;
        newGameItem = pNewGameItem;
        listen = new ButtonListener();

        setLayout(new BorderLayout());
        panel1 = new JPanel();

        checkValidNumbers();

        createBoard();
        add(panel1, BorderLayout.CENTER);
        game = new Surround4Game(bSize, nPlayers, sPlayer);
        quitItem.addActionListener(listen);
        newGameItem.addActionListener(listen);

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitItem)
                System.exit(1);
            if (e.getSource() == newGameItem){
                checkValidNumbers();
                panel1.revalidate();
                panel1.repaint();
            }

            for (int row = 0; row < board.length; row++)
                for (int col = 0; col < board[0].length; col++)
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) { // If spot int the array is not occupied
                                board[row][col].setText(""+game.getCurrentPlayer());
                            player = game.nextPlayer();
                        } else
                            JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");

            for (int row = 0; row < 10; row++)
                for (int col = 0; col < 10; col++) {
                    Cell c = game.getCell(row, col);
                    if (c != null)
                        board[row][col].setText(""+c.getPlayerNumber());
                    else
                        board[row][col].setText("");
                }

            displayBoard();
            int winner = game.getWinner();
            if (winner != -1) {
                JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                game = new Surround4Game();
                displayBoard();

            }
        }
    }

    private void createBoard() {

        if (bSize > 3 && bSize < 21) {
            board = new JButton[bSize][bSize];
            panel1.setLayout(new GridLayout(bSize, bSize));

            for (int i = 0; i < bSize; i++) // rows
                for (int j = 0; j < bSize; j++) {
                    board[i][j] = new JButton("");
                    board[i][j].addActionListener(listen);
                    panel1.add(board[i][j]);
                }
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid board size");
            board = new JButton[10][10];
            panel1.setLayout(new GridLayout(10, 10));

            for (int i = 0; i < 10; i++) // rows
                for (int j = 0; j < 10; j++) {
                    board[i][j] = new JButton("");
                    board[i][j].addActionListener(listen);
                    panel1.add(board[i][j]);
                }
        }
    }

    private void displayBoard() {
        if (bSize > 3 && bSize < 21) {
            for (int row = 0; row < bSize; row++)
                for (int col = 0; col < bSize; col++) {
                    Cell c = game.getCell(row, col);
                    if (c != null)
                        board[row][col].setText("" + c.getPlayerNumber());
                    else
                        board[row][col].setText("");
                }
        }
        else {
            for (int row = 0; row < 10; row++)
                for (int col = 0; col < 10; col++) {
                    Cell c = game.getCell(row, col);
                    if (c != null)
                        board[row][col].setText("" + c.getPlayerNumber());
                    else
                        board[row][col].setText("");
                }
        }
    }

    private void checkValidNumbers () {

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

