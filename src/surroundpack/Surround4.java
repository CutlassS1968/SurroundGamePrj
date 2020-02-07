//package surroundpack;
//
//import javax.swing.*;
//
///**********************************************************************************************
// * This program creates a game called Surround, the objective of the game is to surround an
// * opponent with tiles
// *
// * @author Austin Ackerman
// * @author Evan Johns
// * @version 02/07/2020 00:25:00
// **********************************************************************************************/
//
//public class Surround4 {
//
//  /*********************************************************************************************
//   * Main starts the main loop of the game
//   *
//   * @param args
//   *********************************************************************************************/
//  public static void main(String[] args) {
//    JMenuBar menus;
//    JMenu fileMenu;
//    JMenuItem quitItem;
//    JMenuItem newGameItem;
//
//    // QUESTION: CAN THIS BE PUT INTO THE Surround4Panel CONSTRUCTOR?
//    JFrame frame = new JFrame("Surround game");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    fileMenu = new JMenu("File");
//    quitItem = new JMenuItem("quit");
//    newGameItem = new JMenuItem("new game");
//
//    fileMenu.add(quitItem);
//    fileMenu.add(newGameItem);
//
//    menus = new JMenuBar();
//    menus.add(fileMenu);
//
//    frame.setJMenuBar(menus);
//
//    Surround4Panel panel = new Surround4Panel(quitItem, newGameItem);
//    frame.add(panel);
//    frame.setSize(600, 600);
//    frame.setVisible(true);
//  }
//    }