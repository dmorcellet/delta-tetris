package delta.games.tetris;

import delta.games.tetris.field.TetrisField;
import delta.games.tetris.pieces.TetrisPiecesRegistry;
import delta.games.tetris.score.TetrisPiecesStatistics;
import delta.games.tetris.score.TetrisScoreComputer;

/**
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

  public TetrisField getField()
  {
    return _field;
  }

  public TetrisPiecesRegistry getPiecesRegistry()
  {
    return _pieces;
  }

  public TetrisPiecesStatistics getStats()
  {
    return _stats;
  }

  public TetrisScoreComputer getScoreManager()
  {
    return _score;
  }

  public int getCurrentLevel()
  {
    return _currentLevel;
  }

  public String getPlayerName()
  {
    return _playerName;
  }
}
