package TicTacToe;

import DynamicMemory.List;
import GUI.Panels.Panels;
import Players.Player;


import javax.swing.*;


public class Game implements Panels {
    private final static int DELAY = 1000;
    private final Player[] players = new Player[2];

    public Board board = new Board();
    public GameRecord gameRecord;
    Timer timer;

    private final List<Move> GameMoves = new List<>();

    private boolean XTurn = false;

    public Game(Player player1, Player player2){
        players[0] = player1;
        players[1] = player2 ;

        gameRecord = new GameRecord(player1,player2);
    }

    private Timer setTimer(int player, Board.Cell symbol){
        timer = new Timer(DELAY, (event)->{
            Move AIMove = players[player].getMove(board, symbol);
            GameMoves.add(AIMove);
            board.Move(AIMove);
            BOARD.ButtonsSetEnabled(true);
            nextTurn();
        });
        timer.setRepeats(false);
        return timer;
    }

    public void nextTurn(){
        XTurn = !XTurn;

        if(board.GetResult() == Board.Result.Unknown) {
            if (XTurn && !getPlayerX().isHuman) {
                BOARD.ButtonsSetEnabled(false);
                timer = setTimer(0, Board.Cell.X);
                timer.restart();

            }else if (!XTurn && !getPlayerO().isHuman) {
                BOARD.ButtonsSetEnabled(false);
                timer = setTimer(1, Board.Cell.O);
                timer.restart();
            }else{
                BOARD.ButtonsSetEnabled(true);
            }
        }
        BOARD.updateBoard();
    }

    public Player getPlayerX(){
        return players[0];
    }

    public Player getPlayerO(){
        return players[1];
    }

    public void parseMove(Move move){
        if(XTurn){
            move.setPlayer(Board.Cell.X);
        }else{
            move.setPlayer(Board.Cell.O);
        }
        GameMoves.add(move);
        board.Move(move);
        nextTurn();
    }

    public void undo(){
        if(GameMoves.size() > 0) {
            Move moveToUndo = GameMoves.get(GameMoves.size() - 1);
            board.board[moveToUndo.x][moveToUndo.y] = Board.Cell.BLANK;
            GameMoves.remove(moveToUndo);
        }
    }
}
