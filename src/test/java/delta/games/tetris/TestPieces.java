package delta.games.tetris;

import junit.framework.Assert;
import junit.framework.TestCase;
import delta.games.tetris.pieces.TetrisPieceModel;
import delta.games.tetris.pieces.TetrisPiecesRegistry;

public class TestPieces extends TestCase
{
  /**
   * Constructor.
   */
  public TestPieces()
  {
    super("Tetris Pieces");
  }

  public void testGetPieces()
  {
    TetrisPiecesRegistry registry=TetrisPiecesRegistry.getInstance();
    Assert.assertNotNull(registry);
    TetrisPieceModel[] pieces=registry.getPieces();
    Assert.assertNotNull(pieces);
  }
}
