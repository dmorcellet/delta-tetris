package delta.games.tetris.gui.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import delta.games.tetris.TetrisGame;
import delta.games.tetris.score.TetrisScoreComputer;

/**
 * @author DAM
 */
public class TetrisSidePanel extends JPanel
{
  private static final boolean DEBUG=false;
  private transient TetrisGame _game;
  private JLabel _playerName;
  private JLabel _level;
  private JLabel _score;

  public TetrisSidePanel(TetrisGame game)
  {
    super(new GridBagLayout());
    _game=game;
    buildGUI();
  }

  private void buildGUI()
  {
    GridBagConstraints constraints;
    int x=0,y=0;
    // Tetris main label
    {
      JLabel tetrisLabel=new JLabel("Tetris",JLabel.CENTER);
      setLabelSize(tetrisLabel,72f);
      if (DEBUG)
      {
        tetrisLabel.setOpaque(true);
        tetrisLabel.setBackground(Color.RED);
      }
      constraints=new GridBagConstraints(x,y,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,0);
      add(tetrisLabel,constraints);
    }
    y++;
    // Player name
    {
      _playerName=new JLabel("Joe",JLabel.CENTER);
      setLabelSize(_playerName,36f);
      if (DEBUG)
      {
        _playerName.setOpaque(true);
        _playerName.setBackground(Color.GREEN);
      }
      constraints=new GridBagConstraints(x,y,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,0);
      add(_playerName,constraints);
    }
    y++;
    // Level
    {
      _level=new JLabel("Niveau 1",JLabel.CENTER);
      setLabelSize(_level,24f);
      if (DEBUG)
      {
        _level.setOpaque(true);
        _level.setBackground(Color.RED);
      }
      constraints=new GridBagConstraints(x,y,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,0);
      add(_level,constraints);
    }
    y++;
    // Score
    {
      _score=new JLabel("Score : 0",JLabel.CENTER);
      setLabelSize(_score,24f);
      if (DEBUG)
      {
        _score.setOpaque(true);
        _score.setBackground(Color.GREEN);
      }
      constraints=new GridBagConstraints(x,y,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,0);
      add(_score,constraints);
    }
  }

  private static void setLabelSize(JLabel label, float size)
  {
    Font font=label.getFont();
    font=font.deriveFont(size);
    label.setFont(font);
  }

  public void update()
  {
    String playerName=_game.getPlayerName();
    _playerName.setText(playerName);
    int level=_game.getCurrentLevel();
    _level.setText(String.valueOf(level));
    TetrisScoreComputer scoreMgr=_game.getScoreManager();
    int score=scoreMgr.getScore();
    _score.setText(String.valueOf(score));
  }
}
