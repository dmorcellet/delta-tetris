package delta.games.tetris.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import delta.games.tetris.field.TetrisField;

/**
 * @author DAM
 */
public class TetrisGUI implements ActionListener,KeyListener
{
  public void keyPressed(KeyEvent e)
  {
    keyTyped(e);
  }

  public void keyReleased(KeyEvent e)
  {
    //keyTyped(e);
  }

  public void keyTyped(KeyEvent e)
  {
    int keyCode=e.getKeyCode();
    if (keyCode==KeyEvent.VK_LEFT)
    {
      // todo
    }
    else if (keyCode==KeyEvent.VK_RIGHT)
    {
      // todo
    }
  }

  private TetrisFieldPanel _gField;
  private TetrisField _field;

  private Timer _guiTimer;

  public TetrisGUI(TetrisField field)
  {
    _field=field;
    buildGUI();
    _guiTimer=new Timer(100,this);
    _guiTimer.start();
  }

  public void actionPerformed(ActionEvent e)
  {
    // Nothing to do !!
  }

  private void buildGUI()
  {
    JFrame f=new JFrame();
    f.addKeyListener(this);
    _gField=new TetrisFieldPanel(_field);
    f.add(_gField);
    f.pack();
    f.setVisible(true);
  }
}
