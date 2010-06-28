package delta.games.tetris;

/**
 * Configuration of a tetris game.
 * @author DAM
 */
public class TetrisGameCfg
{
  private int _initialLevel;
  private int _fieldWidth;
  private int _fieldHeight;
  private int _debrisHeight;

  /**
   * Constructor.
   */
  public TetrisGameCfg()
  {
    _initialLevel=1;
    _fieldWidth=10;
    _fieldHeight=25;
    _debrisHeight=0;
  }

  /**
   * @return the debrisHeight
   */
  public int getDebrisHeight()
  {
    return _debrisHeight;
  }

  /**
   * @param debrisHeight the debrisHeight to set
   */
  public void setDebrisHeight(int debrisHeight)
  {
    _debrisHeight=debrisHeight;
  }

  /**
   * @return the fieldHeight
   */
  public int getFieldHeight()
  {
    return _fieldHeight;
  }

  /**
   * @param fieldHeight the fieldHeight to set
   */
  public void setFieldHeight(int fieldHeight)
  {
    _fieldHeight=fieldHeight;
  }

  /**
   * @return the fieldWidth
   */
  public int getFieldWidth()
  {
    return _fieldWidth;
  }

  /**
   * @param fieldWidth the fieldWidth to set
   */
  public void setFieldWidth(int fieldWidth)
  {
    _fieldWidth=fieldWidth;
  }

  /**
   * @return the initialLevel
   */
  public int getInitialLevel()
  {
    return _initialLevel;
  }

  /**
   * @param initialLevel the initialLevel to set
   */
  public void setInitialLevel(int initialLevel)
  {
    _initialLevel=initialLevel;
  }
}
