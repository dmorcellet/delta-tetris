package delta.games.tetris.gui.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import delta.games.tetris.TetrisGame;
import delta.games.tetris.field.TetrisField;

/**
 * @author DAM
 */
public class TetrisMainFrame extends JFrame implements ActionListener
{
  private transient TetrisGame _tetrisGame;
  private static final String NEW_GAME_CMD="NEW_GAME";
  private static final String QUIT_CMD="QUIT";

  private TetrisFieldPanel _fieldPanel;

  public TetrisMainFrame()
  {
    super("Tetris");
    _tetrisGame=new TetrisGame();
    buildMenus();
    buildFrame();
    pack();
  }

  private void buildMenus()
  {
    // Create the menu bar
    JMenuBar menuBar=new JMenuBar();

    // Build the "Game" menu
    JMenu menu=new JMenu("Jeu");
    menu.setMnemonic(KeyEvent.VK_J);
    menuBar.add(menu);

    // New game item
    {
      JMenuItem menuItem=new JMenuItem("Nouvelle partie",KeyEvent.VK_N);
      menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.ALT_MASK));
      menuItem.setActionCommand(NEW_GAME_CMD);
      menuItem.addActionListener(this);
      menu.add(menuItem);
    }
    menu.addSeparator();
    // Best scores item
    {
      JMenuItem menuItem=new JMenuItem("Meilleurs scores",KeyEvent.VK_M);
      menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.ALT_MASK));
      menuItem.addActionListener(this);
      menu.add(menuItem);
    }
    // Preferences item
    {
      JMenuItem menuItem=new JMenuItem("Préferences",KeyEvent.VK_P);
      menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
      menuItem.addActionListener(this);
      menu.add(menuItem);
    }
    menu.addSeparator();
    // Quit item
    {
      JMenuItem menuItem=new JMenuItem("Quitter",KeyEvent.VK_Q);
      menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.ALT_MASK));
      menuItem.setActionCommand(QUIT_CMD);
      menuItem.addActionListener(this);
      menu.add(menuItem);
    }
    menuBar.add(menu);
    setJMenuBar(menuBar);
  }

  private void buildFrame()
  {
    JPanel mainPanel=new JPanel(new GridBagLayout());
    TetrisField field=_tetrisGame.getField();
    _fieldPanel=new TetrisFieldPanel(field);
    GridBagConstraints c1=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,5,5,5),0,0);
    mainPanel.add(_fieldPanel,c1);
    TetrisSidePanel sidePanel=new TetrisSidePanel(_tetrisGame);
    GridBagConstraints c2=new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(5,5,5,5),0,0);
    mainPanel.add(sidePanel,c2);
    add(mainPanel);
  }

  public TetrisFieldPanel getFieldPanel()
  {
    return _fieldPanel;
  }

  public void actionPerformed(ActionEvent e)
  {
    String cmd=e.getActionCommand();
    if (NEW_GAME_CMD.equals(cmd))
    {
      doNewGame();
    }
    else if (QUIT_CMD.equals(cmd))
    {
      doQuit();
    }
  }

  private void doNewGame()
  {
    // todo
  }

  private void doQuit()
  {
    System.exit(0);
  }

  public static void main(String[] args)
  {
    TetrisMainFrame frame=new TetrisMainFrame();
    frame.setVisible(true);
  }
}
