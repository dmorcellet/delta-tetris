package delta.games.tetris.field;

import java.io.PrintStream;

import delta.games.tetris.pieces.TetrisPiece;
import delta.games.tetris.pieces.TetrisPieceModel;
import delta.games.tetris.pieces.TetrisPiecePosition;
import delta.games.tetris.pieces.TetrisPieceRotation;

/**
 * A Tetris field.
 * Such a field contains somes Tetris pieces.
 * @author DAM
 */
public class TetrisField
{
  private int _width;
  private int _height;
  private TetrisPiece[][] _squaresOccupation;

  /**
   * Constructor.
   * @param width Width of this field.
   * @param height Height of this field.
   */
  public TetrisField(int width, int height)
  {
    _width=width;
    _height=height;
    _squaresOccupation=new TetrisPiece[width][height];
  }

  /**
   * Get the width of this field.
   * @return the width of this field.
   */
  public int getWidth()
  {
    return _width;
  }

  /**
   * Get the height of this field.
   * @return the height of this field.
   */
  public int getHeight()
  {
    return _height;
  }

  /**
   * Get the piece located at specified position.
   * @param x Horizontal position.
   * @param y Vertical position.
   * @return A piece or <code>null</code>.
   */
  public TetrisPiece getPieceAt(int x, int y)
  {
    return _squaresOccupation[x][y];
  }

  /**
   * Put a piece in the field.
   * @param piece Piece to put.
   * @param position Position where the piece should be put.
   */
  public void putPiece(TetrisPiece piece, TetrisPiecePosition position)
  {
    TetrisPieceModel model=piece.getModel();
    TetrisPieceRotation rotation=piece.getRotation();
    int x0=position.getX();
    int y0=position.getY();
    int height=model.getHeight();
    int width=model.getWidth();
    int hotX=model.getHotX();
    int hotY=model.getHotY();
    int finalX;
    int finalY;
    int[] delta=new int[2];
    for(int i=0;i<width;i++)
    {
      for(int j=0;j<height;j++)
      {
        if (model.isActivated(i,j))
        {
          // Compute delta from hot point
          delta[0]=i-hotX;
          delta[1]=j-hotY;
          // Rotate
          rotation.rotate(delta);
          // Translate to position
          finalX=x0+delta[0];
          finalY=y0+delta[1];
          _squaresOccupation[finalX][finalY]=piece;
        }
      }
    }
  }

  /**
   * Find index of first full line (starting at the bottom of field).
   * @return A valid line position or -1.
   */
  public int findFullLine()
  {
    for(int j=0;j<_height;j++)
    {
      boolean foundIt=true;
      for(int i=0;i<_width;i++)
      {
        if (_squaresOccupation[i][j]==null)
        {
          foundIt=false;
          break;
        }
      }
      if (foundIt)
      {
        return j;
      }
    }
    return -1;
  }

  public int removeAllCompletedLines()
  {
    int nbRemoved=0;
    while(true)
    {
      int index=findCompletedLine();
      if (index!=-1)
      {
        removeLine(index);
        nbRemoved++;
      }
      else
      {
        break;
      }
    }
    return nbRemoved;
  }

  public int findCompletedLine()
  {
    int index=-1;
    for(int j=0;j<_height;j++)
    {
      boolean completed=true;
      for(int i=0;i<_width;i++)
      {
        if (getPieceAt(i,j)==null)
        {
          completed=false;
          break;
        }
      }
      if (completed)
      {
        index=j;
        break;
      }
    }
    return index;
  }

  /**
   * Remove a line.
   * @param y Vertical position of line to remove.
   */
  public void removeLine(int y)
  {
    assert(y>=0);
    assert(y<_height);
    for(int j=y+1;j<_height;j++)
    {
      for(int i=0;i<_width;i++)
      {
        _squaresOccupation[i][j-1]=_squaresOccupation[i][j];
      }
    }
    for(int i=0;i<_width;i++)
    {
      _squaresOccupation[i][_height-1]=null;
    }
  }

  /**
   * Remove a piece from the field.
   * @param piece Piece to remove.
   */
  public void removePiece(TetrisPiece piece)
  {
    TetrisPiece existingPiece;
    for(int i=0;i<_width;i++)
    {
      for(int j=0;j<_height;j++)
      {
        existingPiece=_squaresOccupation[i][j];
        if (existingPiece==piece)
        {
          _squaresOccupation[i][j]=null;
        }
      }
    }
  }

  /**
   * Move a piece to a given position.
   * @param piece Piece to move.
   * @param position Position to move to.
   * @return <code>true</code> if move succeeded, <code>false</code> otherwise.
   */
  public boolean movePiece(TetrisPiece piece, TetrisPiecePosition position)
  {
    if (canPutPiece(piece,position))
    {
      removePiece(piece);
      putPiece(piece,position);
      return true;
    }
    return false;
  }

  /**
   * Indicates if a piece can put put at a specified position.
   * @param piece Piece to test.
   * @param position Position to test.
   * @return <code>true</code> if the piece can be safely put, <code>false</code> otherwise.
   */
  public boolean canPutPiece(TetrisPiece piece, TetrisPiecePosition position)
  {
    TetrisPieceModel model=piece.getModel();
    TetrisPieceRotation rotation=piece.getRotation();
    int x0=position.getX();
    int y0=position.getY();
    int height=model.getHeight();
    int width=model.getWidth();
    int hotX=model.getHotX();
    int hotY=model.getHotY();
    int finalX;
    int finalY;
    int[] delta=new int[2];
    TetrisPiece existingPiece;
    for(int i=0;i<width;i++)
    {
      for(int j=0;j<height;j++)
      {
        if (model.isActivated(i,j))
        {
          // Compute delta from hot point
          delta[0]=i-hotX;
          delta[1]=j-hotY;
          // Rotate
          rotation.rotate(delta);
          // Translate to position
          finalX=x0+delta[0];
          finalY=y0+delta[1];
          // Check inclusion
          if (finalX<0) return false;
          if (finalX>=_width) return false;
          if (finalY<0) return false;
          if (finalY>=_height) return false;
          existingPiece=getPieceAt(finalX,finalY);
          if ((existingPiece!=null) && (existingPiece!=piece))
          {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Dump the contents of this field to the specied stream.
   * @param ps Output stream.
   */
  public void dump(PrintStream ps)
  {
    TetrisPiece piece;
    TetrisPieceModel model;
    String name;
    for(int j=_height-1;j>=0;j--)
    {
      for(int i=0;i<_width;i++)
      {
        piece=_squaresOccupation[i][j];
        if (piece!=null)
        {
          model=piece.getModel();
          name=model.getName();
          ps.print(name);
        }
        else
        {
          ps.print('-');
        }
      }
      ps.println("");
    }
  }
}
