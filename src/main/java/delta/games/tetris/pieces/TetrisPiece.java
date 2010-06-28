package delta.games.tetris.pieces;

/**
 * A piece of a Tetris game.
 * @author DAM
 */
public class TetrisPiece
{
  private TetrisPieceModel _piece;
  private TetrisPieceRotation _rotation;

  /**
   * Constructor.
   * @param piece Model for this piece.
   * @param rotation Rotation for this piece.
   */
  public TetrisPiece(TetrisPieceModel piece, TetrisPieceRotation rotation)
  {
    _piece=piece;
    _rotation=rotation;
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
}
