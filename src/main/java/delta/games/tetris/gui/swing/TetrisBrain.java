package delta.games.tetris.gui.swing;

import java.awt.Color;
import java.util.Random;

import delta.games.tetris.TetrisGame;
import delta.games.tetris.field.TetrisField;
import delta.games.tetris.pieces.TetrisPiece;
import delta.games.tetris.pieces.TetrisPieceModel;
import delta.games.tetris.pieces.TetrisPiecePosition;
import delta.games.tetris.pieces.TetrisPieceRotation;
import delta.games.tetris.pieces.TetrisPiecesRegistry;
import delta.games.tetris.score.TetrisScoreComputer;

/**
 * @author DAM
 */
public class TetrisBrain
{
  // GUI related objects
  private TetrisFieldPanel _gField;
  // Not GUI objects
  private TetrisGame _game;
  private TetrisField _field;
  private TetrisPiecesRegistry _piecesRegistry;
  // Current piece info
  private TetrisPiece _currentPiece;
  private TetrisPiecePosition _position;
  // Misc
  private Random _random;

  // Colors
  private static Color[] COLORS={Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK};

  public TetrisBrain(TetrisGame game, TetrisFieldPanel gameField)
  {
    _gField=gameField;
    _game=game;
    _field=game.getField();
    _piecesRegistry=game.getPiecesRegistry();
    _currentPiece=null;
    _position=null;
    _random=new Random(System.currentTimeMillis());
  }

  public void doTick()
  {
    if (_currentPiece==null)
    {
      boolean ok=buildNewPiece();
      if (!ok)
      {
        // todo end of game
      }
    }
    if (_currentPiece!=null)
    {
      doAutoMovePiece();
    }
  }
  
  public void doAutoMovePiece()
  {
    TetrisPiecePosition newPosition=new TetrisPiecePosition(_position.getX(),_position.getY()-1);
    boolean ok=_field.canPutPiece(_currentPiece,newPosition);
    if (ok)
    {
      _position=newPosition;
      _field.movePiece(_currentPiece,_position);
    }
    else
    {
      endOfPiece();
      _currentPiece=null;
      _position=null;
    }
    repaintGameField();
  }

  public void handleEndOfPiece()
  {
    if (_currentPiece!=null)
    {
      int x=_position.getX();
      int y=_position.getY();
      int previousY=y;
      
      while(true)
      {
        if (y>0)
        {
          TetrisPiecePosition newPosition=new TetrisPiecePosition(x,y-1);
          boolean ok=_field.canPutPiece(_currentPiece,newPosition);
          if (!ok)
          {
            break;
          }
          y--;
        }
        else
        {
          break;
        }
      }
      if (y!=previousY)
      {
        _field.movePiece(_currentPiece,new TetrisPiecePosition(x,y));
      }
      endOfPiece();
      _currentPiece=null;
      _position=null;
      repaintGameField();
    }
  }

  public void handleMoveRight()
  {
    if (_currentPiece!=null)
    {
      TetrisPiecePosition newPosition=new TetrisPiecePosition(_position.getX()+1,_position.getY());
      boolean ok=_field.canPutPiece(_currentPiece,newPosition);
      if (ok)
      {
        _position=newPosition;
        _field.movePiece(_currentPiece,_position);
        _gField.repaint();
      }
    }
  }

  public void handleMoveLeft()
  {
    if (_currentPiece!=null)
    {
      TetrisPiecePosition newPosition=new TetrisPiecePosition(_position.getX()-1,_position.getY());
      boolean ok=_field.canPutPiece(_currentPiece,newPosition);
      if (ok)
      {
        _position=newPosition;
        _field.movePiece(_currentPiece,_position);
        _gField.repaint();
      }
    }
  }

  public void handleRotateLeft()
  {
    // todo
  }

  public void handleRotateRight()
  {
    if (_currentPiece!=null)
    {
      _currentPiece.rotate(true);
      TetrisPiecePosition newPosition=new TetrisPiecePosition(_position.getX()-1,_position.getY());
      boolean ok=_field.canPutPiece(_currentPiece,newPosition);
      if (ok)
      {
        _position=newPosition;
        _field.movePiece(_currentPiece,_position);
        _gField.repaint();
      }
    }
  }

  public boolean buildNewPiece()
  {
    TetrisPieceModel[] pieces=_piecesRegistry.getPieces();
    int index=_random.nextInt(pieces.length);
    TetrisPieceModel pieceModel=pieces[index];
    // todo place piece smartlier
    _position=new TetrisPiecePosition(5,20);
    TetrisPieceRotation rotation=TetrisPieceRotation.CLOCKWISE;
    Color color=pickRandomColor();
    TetrisPiece piece=new TetrisPiece(pieceModel,rotation,color);
    boolean canPut=_field.canPutPiece(piece,_position);
    if (canPut)
    {
      _field.putPiece(piece,_position);
      _currentPiece=piece;
      repaintGameField();
      return true;
    }
    return false;
  }

  private Color pickRandomColor()
  {
    int index=_random.nextInt(COLORS.length);
    return COLORS[index];
  }

  private void repaintGameField()
  {
    _gField.repaint();
  }

  public void endOfPiece()
  {
    // Handle score
    TetrisScoreComputer scoreMgr=_game.getScoreManager();
    scoreMgr.addPiece(_currentPiece.getModel());
    // Remove completed lines
    int nbLines=_field.removeAllCompletedLines();
    if (nbLines>0)
    {
      repaintGameField();
    }
  }
}
