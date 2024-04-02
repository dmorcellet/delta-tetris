package delta.games.tetris.gui.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import delta.games.tetris.TetrisGame;
import delta.games.tetris.field.TetrisField;

/**
 * Controller for the Tetris main frame.
 * @author DAM
 */
public class TetrisMainFrameController implements ActionListener
{
  private static final int TIMER_DELAY=1000;
  private JFrame _frame;
  private TetrisBrain _brain;
  private TetrisGame _tetrisGame;
  private static final String NEW_GAME_CMD="NEW_GAME";
  private static final String QUIT_CMD="QUIT";

  private TetrisFieldPanel _fieldPanel;
  private TetrisSidePanel _sidePanel;
  private Timer _guiTimer;

  /**
   * Constructor.
   */
  public TetrisMainFrameController()
  {
    _tetrisGame=new TetrisGame();
  }

  /**
   * Get the managed frame (build it if necessary).
   * @return A frame.
   */
  public JFrame getFrame()
  {
    if (_frame==null)
    {
      _frame=buildFrame();
    }
    return _frame;
  }

  private JFrame buildFrame()
  {
    JFrame frame=new JFrame("Tetris");
    JMenuBar menus=buildMenus();
    frame.setJMenuBar(menus);
    JPanel mainPanel=buildMainPanel();
    frame.add(mainPanel);
    frame.pack();
    return frame;
  }

  private JMenuBar buildMenus()
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
    return menuBar;
  }

  private JPanel buildMainPanel()
  {
    JPanel mainPanel=new JPanel(new GridBagLayout());
    TetrisField field=_tetrisGame.getField();
    _fieldPanel=new TetrisFieldPanel(field);
    GridBagConstraints c1=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,5,5,5),0,0);
    mainPanel.add(_fieldPanel,c1);
    _sidePanel=new TetrisSidePanel(_tetrisGame);
    GridBagConstraints c2=new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.NORTH,GridBagConstraints.NONE,new Insets(5,5,5,5),0,0);
    mainPanel.add(_sidePanel,c2);
    return mainPanel;
  }

  /**
   * Get the Tetris field panel.
   * @return the Tetris field panel.
   */
  public TetrisFieldPanel getFieldPanel()
  {
    return _fieldPanel;
  }

  /**
   * Show the managed frame.
   */
  public void show()
  {
    JFrame frame=getFrame();
    frame.setVisible(true);
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
    _brain=new TetrisBrain(_tetrisGame,_fieldPanel);
    JFrame frame=getFrame();
    KeyListener keyListener=new KeyListener() {
      public void keyPressed(KeyEvent e)
      {
        keyTyped(e);
      }

      public void keyReleased(KeyEvent e)
      {
        // Nothing!
      }

      public void keyTyped(KeyEvent e)
      {
        int keyCode=e.getKeyCode();
        if (keyCode==KeyEvent.VK_LEFT)
        {
          _brain.handleMoveLeft();
        }
        else if (keyCode==KeyEvent.VK_RIGHT)
        {
          _brain.handleMoveRight();
        }
        else if (keyCode==KeyEvent.VK_SPACE)
        {
          _brain.handleEndOfPiece();
        }
        else if (keyCode==KeyEvent.VK_UP)
        {
          _brain.handleRotateRight();
        }
        else if (keyCode==KeyEvent.VK_DOWN)
        {
          _brain.handleEndOfPiece();
        }
      }
    };
    frame.addKeyListener(keyListener);
    ActionListener action=new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        _brain.doTick();
        _sidePanel.update();
      }
    };
    
    _guiTimer=new Timer(TIMER_DELAY,action);
    _guiTimer.start();
  }

  private void doQuit()
  {
    if (_guiTimer!=null)
    {
      _guiTimer.stop();
      _guiTimer=null;
    }
    System.exit(0);
  }

  /**
   * Main method for the Tetris game.
   * @param args Not used.
   */
  public static void main(String[] args)
  {
    TetrisMainFrameController controller=new TetrisMainFrameController();
    controller.show();
  }
}
