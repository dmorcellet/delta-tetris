package delta.games.tetris.gui.swing;

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
  //private TetrisMainFrame _gui;
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

  public TetrisBrain(TetrisGame game, TetrisMainFrame gui)
  {
    //_gui=gui;
    _gField=gui.getFieldPanel();
    _game=game;
    _field=game.getField();
    _piecesRegistry=game.getPiecesRegistry();
    _currentPiece=null;
    _position=null;
    _random=new Random(System.currentTimeMillis());
  }

  public void doTick()
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
      _currentPiece=null;
      _position=null;
    }
    _gField.repaint();
  }

  public void handleEndOfPiece()
  {
    // todo
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
    // todo
  }

  public boolean buildNewPiece()
  {
    TetrisPieceModel[] pieces=_piecesRegistry.getPieces();
    int index=_random.nextInt(pieces.length);
    TetrisPieceModel pieceModel=pieces[index];
    // todo place piece smartlier
    _position=new TetrisPiecePosition(5,20);
    TetrisPieceRotation rotation=TetrisPieceRotation.CLOCKWISE;
    TetrisPiece piece=new TetrisPiece(pieceModel,rotation);
    boolean canPut=_field.canPutPiece(piece,_position);
    if (canPut)
    {
      _field.putPiece(piece,_position);
      _currentPiece=piece;
      _gField.repaint();
      return true;
    }
    return false;
  }

  public void endOfPiece()
  {
    TetrisScoreComputer scoreMgr=_game.getScoreManager();
    scoreMgr.addPiece(_currentPiece.getModel());
  }
}
