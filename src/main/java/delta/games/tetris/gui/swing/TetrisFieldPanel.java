package delta.games.tetris.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import delta.games.tetris.field.TetrisField;
import delta.games.tetris.pieces.TetrisPiece;

/**
 * Panel that represents a Tetris field graphically.
 * @author DAM
 */
public class TetrisFieldPanel extends JPanel
{
  /**
   * Pixels between each square.
   */
  private static final int SPACE_BETWEEN_SQUARES=1;
  /**
   * Size of a square (pixels).
   */
  private static final int SQUARE_SIZE=20;
  /**
   * Margin around the panel (pixels).
   */
  private static final int MARGIN=1;

  private transient TetrisField _field;
  private Dimension _size;

  /**
   * Constructor.
   * @param field Tetris field.
   */
  public TetrisFieldPanel(TetrisField field)
  {
    super();
    _field=field;
    _size=computeSize();
    setPreferredSize(_size);
    setBackground(Color.RED);
  }

  /**
   * Compute the size of the GUI panel.
   * @return the size of the GUI panel.
   */
  private Dimension computeSize()
  {
    int width=_field.getWidth();
    int gWidth=width*SQUARE_SIZE+(width-1)*SPACE_BETWEEN_SQUARES;
    gWidth+=2*MARGIN;
    int height=_field.getHeight();
    int gHeight=height*SQUARE_SIZE+(height-1)*SPACE_BETWEEN_SQUARES;
    gHeight+=2*MARGIN;
    Dimension ret=new Dimension(gWidth,gHeight);
    return ret;
  }

  /**
   * Paint the panel.
   * @param g Graphics to paint to.
   */
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    int width=_field.getWidth();
    int height=_field.getHeight();
    int x=MARGIN;
    int y=MARGIN;
    Color color;
    for(int j=height-1;j>=0;j--)
    {
      x=MARGIN;
      for(int i=0;i<width;i++)
      {
        TetrisPiece piece=_field.getPieceAt(i,j);
        if (piece!=null)
        {
          color=piece.getColor();
        }
        else
        {
          color=Color.LIGHT_GRAY;
        }
        paintSquare(g,x,y,color);
        x+=SQUARE_SIZE+SPACE_BETWEEN_SQUARES;
      }
      y+=SQUARE_SIZE+SPACE_BETWEEN_SQUARES;
    }
  }

  /**
   * Paint a single square.
   * @param g Graphics to paint to.
   * @param x Graphical horizontal position.
   * @param y Graphical vertical position.
   * @param color Color to use.
   */
  private void paintSquare(Graphics g, int x, int y, Color color)
  {
    g.setColor(color);
    g.fillRect(x,y,SQUARE_SIZE,SQUARE_SIZE);
  }
}
