package delta.games.tetris;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import delta.games.tetris.pieces.TetrisPieceModel;
import delta.games.tetris.pieces.TetrisPiecesRegistry;

/**
 * Test pieces registry.
 * @author DAM
 */
@DisplayName("Tetris Pieces")
class TestPieces
{
  /**
   * Test pieces access.
   *
   */
  @Test
  void testGetPieces()
  {
    TetrisPiecesRegistry registry=TetrisPiecesRegistry.getInstance();
    assertNotNull(registry);
    TetrisPieceModel[] pieces=registry.getPieces();
    assertNotNull(pieces);
  }
}
