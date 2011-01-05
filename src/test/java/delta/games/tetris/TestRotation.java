package delta.games.tetris;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import delta.games.tetris.field.TetrisField;
import delta.games.tetris.pieces.TetrisPiece;
import delta.games.tetris.pieces.TetrisPieceModel;
import delta.games.tetris.pieces.TetrisPiecePosition;
import delta.games.tetris.pieces.TetrisPieceRotation;
import delta.games.tetris.pieces.TetrisPiecesRegistry;

public class TestRotation extends TestCase
{
  /**
   * Constructor.
   */
  public TestRotation()
  {
    super("Tetris Piece Rotation");
  }

  public void testPiecesRotation()
  {
    TetrisPiecesRegistry registry=TetrisPiecesRegistry.getInstance();
    TetrisPieceModel[] pieces=registry.getPieces();
    for(int i=0;i<pieces.length;i++)
    {
      doPieceRotation(pieces[i]);
    }
  }
  
  private void doPieceRotation(TetrisPieceModel model)
  {
    TetrisField field=new TetrisField(10,10);
    TetrisPiecePosition position=new TetrisPiecePosition(5,5);
    List<TetrisPieceRotation> rotations=new ArrayList<TetrisPieceRotation>();
    {
      TetrisPieceRotation r=TetrisPieceRotation.NONE;
      rotations.add(r);
      do
      {
        r=r.getNext();
        rotations.add(r);
      }
      while (r!=TetrisPieceRotation.NONE);
    }
    for(TetrisPieceRotation rotation : rotations)
    {
      TetrisPiece piece=new TetrisPiece(model,rotation,Color.GREEN);
      boolean canPut=field.canPutPiece(piece,position);
      if (canPut)
      {
        field.putPiece(piece,position);
      }
      field.dump(System.out);
      field.removePiece(piece);
    }
  }
}
