package TicTacToe;

import DynamicMemory.List;
import ΑΙ.HALL;
import java.time.LocalTime;
import java.util.Arrays;

public class Game implements HALL {
    public Board board = new Board();
    private List<Move> GameMoves = new List<>();
    private LocalTime duration;
    private Board.Result winner;

    public GameRecord gameRecord;

    Player playerX = new Player();
    Player playerO = new Player();



    public void parseMove(Move move){
        GameMoves.add(move);
        Move AIMove;
        board.Move(move);
        if(board.GetResult() == Board.Result.Unknown) {
            AIMove = getMove(board, Board.Cell.O);
            GameMoves.add(AIMove);
            board.Move(AIMove);
        }

    }

    public Move giveHint(){
        return getMove(board, Board.Cell.X);
    }

    public void newGame(){
        for(int i = 0 ; i < board.board.length ; i++ ){
            Arrays.fill(board.board[i], Board.Cell.BLANK);
        }
        GameMoves.empty();
        gameRecord = new GameRecord(playerX,playerO);
    }

    public void undo(){
        if(GameMoves.size() > 0) {
            Move moveToUndo = GameMoves.get(GameMoves.size() - 1);
            board.board[moveToUndo.x][moveToUndo.y] = Board.Cell.BLANK;
            GameMoves.remove(moveToUndo);
        }
    }
}
