package delta.games.tetris;

import delta.games.tetris.field.TetrisField;
import delta.games.tetris.pieces.TetrisPiecesRegistry;
import delta.games.tetris.score.TetrisPiecesStatistics;
import delta.games.tetris.score.TetrisScoreComputer;

/**
 * Gathers all data about a Tetris game.
 * @author DAM
 */
public class TetrisGame
{
  private TetrisGameCfg _config;
  private TetrisField _field;
  private TetrisPiecesRegistry _pieces;
  private TetrisPiecesStatistics _stats;
  private TetrisScoreComputer _score;
  private int _currentLevel;
  private String _playerName;

  /**
   * Constructor.
   */
  public TetrisGame()
  {
    _config=new TetrisGameCfg();
    int width=_config.getFieldWidth();
    int height=_config.getFieldHeight();
    _field=new TetrisField(width,height);
    _pieces=TetrisPiecesRegistry.getInstance();
    _stats=new TetrisPiecesStatistics();
    _score=new TetrisScoreComputer();
    _currentLevel=1;
    _playerName="";
  }

  /**
   * Get the Tetris field.
   * @return the Tetris field.
   */
  public TetrisField getField()
  {
    return _field;
  }

  /**
   * Get the Tetris pieces registry.
   * @return the Tetris pieces registry.
   */
  public TetrisPiecesRegistry getPiecesRegistry()
  {
    return _pieces;
  }

  /**
   * Get the statistics about used pieces.
   * @return the statistics about used pieces.
   */
  public TetrisPiecesStatistics getStats()
  {
    return _stats;
  }

  /**
   * Get the Tetris score manager.
   * @return the Tetris score manager.
   */
  public TetrisScoreComputer getScoreManager()
  {
    return _score;
  }

  /**
   * Get the current level.
   * @return the current level.
   */
  public int getCurrentLevel()
  {
    return _currentLevel;
  }

  /**
   * Get the name of the current player.
   * @return a player name.
   */
  public String getPlayerName()
  {
    return _playerName;
  }
}
