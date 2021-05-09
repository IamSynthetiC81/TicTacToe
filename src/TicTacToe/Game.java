package TicTacToe;

import DynamicMemory.List;
import GUI.GameBoard.GameBoard;
import GUI.GameBoard.TicTacToeBoard;
import GUI.Panels.Panels;
import Players.Player;
import Players.Players;
import ΑΙ.AI;

import java.time.LocalTime;

public class Game implements Panels {
    private final static long millis = 50;
    private final Player[] players = new Player[2];

    public Board board = new Board();
    public GameRecord gameRecord;

    private final List<Move> GameMoves = new List<>();
    private LocalTime duration;
    private Board.Result winner;

    private boolean XTurn = false;

    public Game(Player player1, Player player2){
        players[0] = player1;
        players[1] = player2 ;

        gameRecord = new GameRecord(player1,player2);

        System.out.println("NAME: "+player1.getName());
        System.out.println("GAMES NUM: "+player1.getGamesNum());
        System.out.println("Score: "+player1.getScore());

        System.out.println("NAME: "+player2.getName());
        System.out.println("GAMES NUM: "+player2.getGamesNum());
        System.out.println("Score: "+player2.getScore());

    }

    public void nextTurn(){
        XTurn = !XTurn;
        if(board.GetResult() == Board.Result.Unknown) {
            if (XTurn && !getPlayerX().isHuman) {
                Move AIMove = players[0].getMove(board, Board.Cell.X);
                GameMoves.add(AIMove);
                board.Move(AIMove);
                BOARD.updateBoard();
                nextTurn();
            }else if (!XTurn && !getPlayerO().isHuman) {
                Move AIMove = players[0].getMove(board, Board.Cell.O);
                GameMoves.add(AIMove);
                board.Move(AIMove);
                BOARD.updateBoard();
                nextTurn();
            }
        }
    }

    public Player getPlayerX(){
        return players[0];
    }

    public Player getPlayerO(){
        return players[1];
    }

    public void parseMove(Move move) throws InterruptedException {
        if(XTurn){
            move.setPlayer(Board.Cell.X);
        }else{
            move.setPlayer(Board.Cell.O);
        }
        GameMoves.add(move);
        board.Move(move);
        if(board.GetResult() == Board.Result.Unknown) {
            nextTurn();
        }
    }

    public void undo(){
        if(GameMoves.size() > 0) {
            Move moveToUndo = GameMoves.get(GameMoves.size() - 1);
            board.board[moveToUndo.x][moveToUndo.y] = Board.Cell.BLANK;
            GameMoves.remove(moveToUndo);
        }
    }
}
