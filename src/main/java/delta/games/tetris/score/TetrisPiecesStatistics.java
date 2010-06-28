package delta.games.tetris.score;

import java.util.HashMap;

import delta.common.utils.misc.IntegerHolder;
import delta.games.tetris.pieces.TetrisPieceModel;

/**
 * Maintains statistics about pieces usage.
 * @author DAM
 */
public class TetrisPiecesStatistics
{
  private HashMap<String,IntegerHolder> _registeredPieces;

  /**
   * Constructor.
   */
  public TetrisPiecesStatistics()
  {
    _registeredPieces=new HashMap<String,IntegerHolder>();
  }

  /**
   * Register a new piece usage.
   * @param model Model of used piece.
   */
  public void addPiece(TetrisPieceModel model)
  {
    String modelName=model.getName();
    IntegerHolder nb=_registeredPieces.get(modelName);
    if (nb==null)
    {
      nb=new IntegerHolder(0);
      _registeredPieces.put(modelName,nb);
    }
    nb.increment();
  }

  /**
   * Get the number of pieces used for the specified <code>model</code>.
   * @param model Targeted piece model.
   * @return A number of pieces.
   */
  public int getNumberOfPieces(TetrisPieceModel model)
  {
    String modelName=model.getName();
    IntegerHolder nb=_registeredPieces.get(modelName);
    int ret=0;
    if (nb!=null)
    {
      ret=nb.getInt();
    }
    return ret;
  }
}
