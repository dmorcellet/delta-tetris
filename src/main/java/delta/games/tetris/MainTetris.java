package delta.games.tetris;

import delta.games.tetris.field.TetrisField;
import delta.games.tetris.gui.swing.TetrisGUI;

/**
 * @author DAM
 */
public class MainTetris
{
  private TetrisField _field;

  public MainTetris()
  {
    _field=new TetrisField(10,25);
    new TetrisGUI(_field);
  }

  public static void main(String[] args)
  {
    new MainTetris();
  }
}
