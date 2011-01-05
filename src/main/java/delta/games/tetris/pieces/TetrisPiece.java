package delta.games.tetris.pieces;

import java.awt.Color;

/**
 * A piece of a Tetris game.
 * @author DAM
 */
public class TetrisPiece
{
  private TetrisPieceModel _piece;
  private TetrisPieceRotation _rotation;
  private Color _color;

  /**
   * Constructor.
   * @param piece Model for this piece.
   * @param rotation Rotation for this piece.
   * @param color Color of piece.
   */
  public TetrisPiece(TetrisPieceModel piece, TetrisPieceRotation rotation, Color color)
  {
    _piece=piece;
    _rotation=rotation;
    _color=color;
  }

  /**
   * Rotate this piece.
   * @param clockWise indicates if it should be rotated clockwise or not.
   */
  public void rotate(boolean clockWise)
  {
    if (clockWise)
    {
      _rotation=_rotation.getNext();
    }
    else
    {
      _rotation=_rotation.getPrevious();
    }
  }

  /**
   * Get the model for this piece.
   * @return the model for this piece.
   */
  public TetrisPieceModel getModel()
  {
    return _piece;
  }

  /**
   * Get the current rotation state for this piece.
   * @return the current rotation state for this piece.
   */
  public TetrisPieceRotation getRotation()
  {
    return _rotation;
  }
  
  /**
   * Get the color of this piece.
   * @return a color.
   */
  public Color getColor()
  {
    return _color;
  }
}
