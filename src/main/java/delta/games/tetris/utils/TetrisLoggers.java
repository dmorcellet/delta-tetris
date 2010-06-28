package delta.games.tetris.utils;

import org.apache.log4j.Logger;

import delta.common.utils.traces.LoggersRegistry;
import delta.common.utils.traces.LoggingConstants;

/**
 * Management class for all Tetris loggers.
 * @author DAM
 */
public abstract class TetrisLoggers
{
  /**
   * Name of the "TETRIS" logger.
   */
  public static final String TETRIS="GAMES.TETRIS";

  /**
   * Name of the pieces related Tetris logger.
   */
  public static final String TETRIS_PIECES=TETRIS+LoggingConstants.SEPARATOR+"PIECES";

  private static final Logger _tetrisLogger=LoggersRegistry.getLogger(TETRIS);
  private static final Logger _tetrisPiecesLogger=LoggersRegistry.getLogger(TETRIS_PIECES);

  /**
   * Get the logger used for Tetris (GAMES.TETRIS).
   * @return the logger used for Tetris.
   */
  public static Logger getTetrisLogger()
  {
    return _tetrisLogger;
  }

  /**
   * Get the logger used for Tetris pieces.
   * @return the logger used for Tetris pieces.
   */
  public static Logger getTetrisPiecesLogger()
  {
    return _tetrisPiecesLogger;
  }
}
