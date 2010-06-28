package delta.games.tetris.pieces;

/**
 * Position of a Tetris piece.
 * Each Tetris piece has an horizontal position (starting at 0, left to right),
 * and a vertical position (starting at 0, bottom to top).
 * @author DAM
 */
public class TetrisPiecePosition
{
  private int _x;
  private int _y;

  /**
   * Default constructor.
   */
  public TetrisPiecePosition()
  {
    this(0,0);
  }

  /**
   * Copy constructor.
   * @param original Position to copy.
   */
  public TetrisPiecePosition(TetrisPiecePosition original)
  {
    this(original._x,original._y);
  }

  /**
   * Full constructor.
   * @param x Horizontal position.
   * @param y Vertical position.
   */
  public TetrisPiecePosition(int x, int y)
  {
    _x=x;
    _y=y;
  }

  /**
   * Get the horizontal position.
   * @return the horizontal position.
   */
  public int getX()
  {
    return _x;
  }

  /**
   * Get the vertical position.
   * @return the vertical position.
   */
  public int getY()
  {
    return _y;
  }
}
