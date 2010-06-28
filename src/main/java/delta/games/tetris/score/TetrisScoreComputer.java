package delta.games.tetris.score;

import java.util.HashMap;

import delta.common.utils.misc.IntegerHolder;
import delta.games.tetris.pieces.TetrisPieceModel;

/**
 * A simple Tetris score computer.
 * @author DAM
 */
public class TetrisScoreComputer
{
  private static final int DEFAULT_PIECE_VALUE=10;

  private HashMap<String,IntegerHolder> _scoreForPiece;
  private int _score;

  /**
   * Constructor.
   */
  public TetrisScoreComputer()
  {
    _scoreForPiece=new HashMap<String,IntegerHolder>();
  }

  /**
   * Configure the value of a piece.
   * @param model Targeted piece model.
   * @param score Value to set for this piece model.
   */
  public void setScoreForPiece(TetrisPieceModel model, int score)
  {
    String modelName=model.getName();
    IntegerHolder nb=_scoreForPiece.get(modelName);
    if (nb==null)
    {
      nb=new IntegerHolder(0);
      _scoreForPiece.put(modelName,nb);
    }
    nb.setInt(score);
  }

  /**
   * Get the value for a piece model.
   * @param model Targeted piece model.
   * @return The value of this piece model.
   */
  public int getScoreForPiece(TetrisPieceModel model)
  {
    String modelName=model.getName();
    IntegerHolder nb=_scoreForPiece.get(modelName);
    int ret=DEFAULT_PIECE_VALUE;
    if (nb!=null)
    {
      ret=nb.getInt();
    }
    return ret;
  }

  /**
   * Add a piece to the score.
   * @param model Piece model.
   */
  public void addPiece(TetrisPieceModel model)
  {
    int value=getScoreForPiece(model);
    _score+=value;
  }

  /**
   * Get the current score.
   * @return the current score.
   */
  public int getScore()
  {
    return _score;
  }
}
