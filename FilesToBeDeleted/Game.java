//import TicTacToe.Board;
//import TicTacToe.Move;
//import ΑΙ.MiniMax;
//
//import java.util.Arrays;
//
//public class Game {
//
//    Board board = new Board();
//    List<Move> GameMoves = new List<>();
//
//    public void parseMove(Move move){
//        GameMoves.add(move);
//        Move AIMove;
//        board.Move(move);
//        if(board.GetResult() == Board.Result.Unknown) {
//            AIMove = MiniMax.getBestMove(board, Board.Cell.O);
//            GameMoves.add(AIMove);
//            board.Move(AIMove);
//        }
//    }
//
//    public Move giveHint(){
//        return MiniMax.getBestMove(board, Board.Cell.X);
//    }
//
//    public void newGame(){
//        for(int i = 0 ; i < board.board.length ; i++ ){
//            Arrays.fill(board.board[i], Board.Cell.BLANK);
//        }
//        GameMoves.empty();
//    }
//
//    public void undo(){
//        if(GameMoves.size() > 0) {
//            Move moveToUndo = GameMoves.get(GameMoves.size() - 1);
//            board.board[moveToUndo.x][moveToUndo.y] = Board.Cell.BLANK;
//            GameMoves.remove(moveToUndo);
//        }
//    }
//}
