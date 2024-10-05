package delta.games.tetris;

import java.awt.Color;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import delta.games.tetris.field.TetrisField;
import delta.games.tetris.pieces.TetrisPiece;
import delta.games.tetris.pieces.TetrisPieceModel;
import delta.games.tetris.pieces.TetrisPiecePosition;
import delta.games.tetris.pieces.TetrisPieceRotation;
import delta.games.tetris.pieces.TetrisPiecesRegistry;

/**
 * Test for the Tetris field.
 * @author DAM
 */
@DisplayName("Tetris Field")
class TestField
{
  /**
   * Test field filling.
   */
  @Test
  void testFillField()
  {
    TetrisPiecesRegistry registry=TetrisPiecesRegistry.getInstance();
    TetrisPieceModel[] pieces=registry.getPieces();
    TetrisField field=new TetrisField(10,25);
    TetrisPieceModel pieceModel;
    TetrisPiecePosition position;
    TetrisPieceRotation rotation;
    TetrisPiece piece;
    for(int i=0;i<pieces.length;i++)
    {
      pieceModel=pieces[i];
      position=new TetrisPiecePosition(5,(i+1)*5);
      rotation=TetrisPieceRotation.CLOCKWISE;
      piece=new TetrisPiece(pieceModel,rotation,Color.GRAY);
      boolean canPut=field.canPutPiece(piece,position);
      if (canPut)
      {
        field.putPiece(piece,position);
      }
    }
    field.dump(System.out);
  }
}
