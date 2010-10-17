package delta.games.tetris.pieces;

import java.util.Collection;
import java.util.HashMap;

/**
 * Registry for TETRIS pieces.
 * @author DAM
 */
public class TetrisPiecesRegistry
{
  private static TetrisPiecesRegistry _instance;

  private HashMap<String,TetrisPieceModel> _piecesMap;

  /**
   * Get the sole instance of this class.
   * @return the sole instance of this class.
   */
  public static TetrisPiecesRegistry getInstance()
  {
    if (_instance==null)
    {
      _instance=new TetrisPiecesRegistry();
    }
    return _instance;
  }

  /**
   * Private constructor.
   */
  private TetrisPiecesRegistry()
  {
    _piecesMap=new HashMap<String,TetrisPieceModel>();
    buildPieces();
  }

  private void buildPieces()
  {
    buildPiece("a",new String[]{"oo "," Ho" });
    buildPiece("b",new String[]{"oo","Ho" });
    buildPiece("c",new String[]{"o","o","H","o" });
    buildPiece("d",new String[]{" oo","oH " });
    buildPiece("e",new String[]{" o ","oHo" });
  }

  private void buildPiece(String name, String[] definition)
  {
    TetrisPieceModel piece=new TetrisPieceModel(name,definition);
    _piecesMap.put(name,piece);
  }

  /**
   * Get a piece model by name.
   * @param name Name of targeted piece model.
   * @return A piece model or <code>null</code> if not found.
   */
  public TetrisPieceModel getPiece(String name)
  {
    return _piecesMap.get(name);
  }

  /**
   * Get all registered piece models.
   * @return An array that contains all registered piece models.
   */
  public TetrisPieceModel[] getPieces()
  {
    Collection<TetrisPieceModel> tmp=_piecesMap.values();
    TetrisPieceModel[] ret=new TetrisPieceModel[tmp.size()];
    return tmp.toArray(ret);
  }
}
