package delta.games.tetris.pieces;

import org.apache.log4j.Logger;

import delta.games.tetris.utils.TetrisLoggers;

/**
 * A model of a TETRIS piece.
 * @author DAM
 */
public class TetrisPieceModel
{
  private static final Logger _logger=TetrisLoggers.getTetrisPiecesLogger();
  /**
   * Piece model identifier.
   */
  private String _name;
  /**
   * Definition : activated squares.
   */
  private boolean[][] _definition;
  /**
   * X position of hot point.
   */
  private int _xHot;
  /**
   * Y position of hot point.
   */
  private int _yHot;

  /**
   * Constructor.
   * @param name Name of the piece.
   * @param definition Definition of the piece.
   */
  public TetrisPieceModel(String name, String[] definition)
  {
    if (definition==null) throw new IllegalArgumentException();
    int lines=definition.length;
    if (lines==0) throw new IllegalArgumentException();
    int columns=definition[0].length();
    for(int i=0;i<lines;i++)
    {
      if (definition[i].length()!=columns)
      {
        throw new IllegalArgumentException();
      }
    }
    _name=name;
    _definition=new boolean[columns][lines];
    boolean empty=true;
    boolean hotPointFound=false;
    char tmp;
    for(int i=0;i<lines;i++)
    {
      for(int j=0;j<columns;j++)
      {
        tmp=definition[i].charAt(j);
        if (tmp!=' ')
        {
          _definition[j][i]=true;
          if (empty)
          {
            _xHot=j; _yHot=i;
          }
          empty=false;
          if (tmp=='H')
          {
            _xHot=j; _yHot=i;
            hotPointFound=true;
          }
        }
      }
    }
    if (empty)
    {
      throw new IllegalArgumentException("Empty piece !");
    }
    if (!hotPointFound)
    {
      _logger.warn("Hot point not found for piece ["+_name+"] Using default one : "+_xHot+","+_yHot);
    }
  }

  /**
   * Indicates if the specified square is activated or not.
   * @param x horizontal position of targeted square.
   * @param y vertical position of targeted square.
   * @return <code>true</code> if it is, <code>false</code> otherwise.
   */
  public boolean isActivated(int x, int y)
  {
    return _definition[x][y];
  }

  /**
   * Get the name of this model.
   * @return the name of this model.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Get the total height of this piece.
   * @return the total height of this piece.
   */
  public int getHeight()
  {
    return _definition[0].length;
  }

  /**
   * Get the total width of this piece.
   * @return the total width of this piece.
   */
  public int getWidth()
  {
    return _definition.length;
  }

  /**
   * Get the horizontal position of hot point.
   * @return the horizontal position of hot point.
   */
  public int getHotX()
  {
    return _xHot;
  }

  /**
   * Get the vertical position of hot point.
   * @return the vertical position of hot point.
   */
  public int getHotY()
  {
    return _yHot;
  }
}
