package delta.games.tetris.pieces;

/**
 * Symbolic constants for TETRIS pieces rotations.
 * @author DAM
 */
public final class TetrisPieceRotation
{
  /**
   * No rotation.
   */
  public static final TetrisPieceRotation NONE=new TetrisPieceRotation(0,"NONE");

  /**
   * 90° clockwise rotation.
   */
  public static final TetrisPieceRotation CLOCKWISE=new TetrisPieceRotation(1,"90CW");

  /**
   * 180° rotation.
   */
  public static final TetrisPieceRotation HORIZONTAL_MIRROR=new TetrisPieceRotation(2,"HM");

  /**
   * 90° counter-clockwise rotation.
   */
  public static final TetrisPieceRotation COUNTERCLOCKWISE=new TetrisPieceRotation(3,"90CCW");

  /**
   * Array of existing rotations.
   */
  public static final TetrisPieceRotation[] ROTATIONS={NONE, CLOCKWISE, HORIZONTAL_MIRROR, COUNTERCLOCKWISE };

  private int _index;
  private String _name;
  private TetrisPieceRotation _next;
  private TetrisPieceRotation _previous;

  /**
   * Private constructor.
   * The only instances of this class are in-class instanciated public static attributes.
   */
  private TetrisPieceRotation(int index, String name)
  {
    _index=index;
    _name=name;
    _next=null;
  }

  /**
   * Get the next rotation.
   * @return the next rotation.
   */
  public TetrisPieceRotation getNext()
  {
    if (_next==null)
    {
      if (_index==ROTATIONS.length-1)
      {
        _next=ROTATIONS[0];
      }
      else
      {
        _next=ROTATIONS[_index+1];
      }
    }
    return _next;
  }

  /**
   * Get the inverse rotation.
   * @return the inverse rotation.
   */
  public TetrisPieceRotation getInverse()
  {
    if (this==CLOCKWISE) return COUNTERCLOCKWISE;
    if (this==COUNTERCLOCKWISE) return CLOCKWISE;
    if (this==HORIZONTAL_MIRROR) return HORIZONTAL_MIRROR;
    return NONE;
  }

  /**
   * Get the previous rotation.
   * @return the previous rotation.
   */
  public TetrisPieceRotation getPrevious()
  {
    if (_previous==null)
    {
      if (_index==0)
      {
        _previous=ROTATIONS[ROTATIONS.length-1];
      }
      else
      {
        _previous=ROTATIONS[_index-1];
      }
    }
    return _previous;
  }

  /**
   * Rotate some x/y coordinates.
   * @param coordinates x/y coordinates to rotate.
   */
  public void rotate(int[] coordinates)
  {
    if (this==CLOCKWISE)
    {
      int tmp=coordinates[0];
      coordinates[0]=-coordinates[1];
      coordinates[1]=tmp;
    }
    else if (this==HORIZONTAL_MIRROR)
    {
      coordinates[0]=-coordinates[0];
      coordinates[1]=-coordinates[1];
    }
    else if (this==COUNTERCLOCKWISE)
    {
      int tmp=coordinates[0];
      coordinates[0]=coordinates[1];
      coordinates[1]=-tmp;
    }
  }
  
  @Override
  public String toString()
  {
    return _name;
  }
}
