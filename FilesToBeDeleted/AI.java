//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class AI {
//
//    public static Board.Cell human = Board.Cell.X;
//    public static Board.Cell ai = Board.Cell.O;
//
//    public static Move getBestMove(Board board){
//        Move bestMove = null;
//        int bestScore = -100;
//        for(Move move : board.findAvailableMoves(ai,board.board)){
//            board.Move(move);
//            int score = minimax(board);
//            board.clearMove(move);
//            if(score > bestScore){
//                bestScore = score;
//                bestMove = move;
//            }
//        }
//        if (bestMove != null && bestMove.hasSymmetries) {
//            Random random = new Random();
//            bestMove.symmetries.add(bestMove);
//            return bestMove.symmetries.get(random.nextInt(bestMove.symmetries.size()));
//        }
//        return bestMove;
//    }
//
//    public static int minimax (Board board) {
//        Board.Result winner = board.GetResult();
//        if (winner != Board.Result.Unknown) {
//            if(winner == Board.Result.OWins){
//                return 2;
//            }else if (winner == Board.Result.XWins){
//                return -2;
//            }else if (winner == Board.Result.Tie){
//                return 0;
//            }
//        }
//
//        int aiCount = 0;
//        int humanCount = 0;
//        for(int i = 0 ; i < board.board.length ; i++ ){
//            for ( int j = 0 ; j < board.board[i].length ; j++ ){
//                if(board.board[i][j] == ai){
//                    aiCount++;
//                }else if(board.board[i][j] == human){
//                    humanCount++;
//                }
//            }
//        }
//
//        int bestScore;
//        if (humanCount > aiCount) {
//            bestScore = -1;
//        }else{
//            bestScore = 1;
//        }
//        List<Move> moves;
//        try {
//            moves = board.findAvailableMoves(humanCount > aiCount ? ai : human, board.board);
//        }catch (StackOverflowError f){
//            return 0;
//        }
//        for(Move move : moves){
//            board.Move(move);
//            int currentScore = minimax(board);
//            board.clearMove(move);
//            if (humanCount > aiCount ? currentScore > bestScore : currentScore < bestScore) {
//                bestScore = currentScore;
//            }
//        }
//        return bestScore;
//    }
//
//    private static Board[] rotateBoard(Board target){
//
//        Board[] _buffer = new Board[3];
//
//        _buffer[0] = new Board();
//        _buffer[0].copyBoard(target);
//
//        _buffer[1] = new Board();
//        _buffer[1].copyBoard(target);
//
//        _buffer[2] = new Board();
//        _buffer[2].copyBoard(target);
//
//        _buffer[0].board[0][0] = target.board[0][2];
//        _buffer[0].board[0][1] = target.board[1][2];
//        _buffer[0].board[0][2] = target.board[2][2];
//
//        _buffer[0].board[1][0] = target.board[0][1];
//        _buffer[0].board[1][1] = target.board[1][1];
//        _buffer[0].board[1][2] = target.board[2][1];
//
//        _buffer[0].board[2][0] = target.board[0][0];
//        _buffer[0].board[2][1] = target.board[1][0];
//        _buffer[0].board[2][2] = target.board[2][0];
//
//        _buffer[1].board[0][0] = target.board[2][2];
//        _buffer[1].board[0][1] = target.board[2][1];
//        _buffer[1].board[0][2] = target.board[2][0];
//
//        _buffer[1].board[1][0] = target.board[1][2];
//        _buffer[1].board[1][1] = target.board[1][1];
//        _buffer[1].board[1][2] = target.board[1][0];
//
//        _buffer[1].board[2][0] = target.board[0][2];
//        _buffer[1].board[2][1] = target.board[0][1];
//        _buffer[1].board[2][2] = target.board[0][0];
//
//
//        _buffer[2].board[0][0] = target.board[2][0];
//        _buffer[2].board[0][1] = target.board[1][0];
//        _buffer[2].board[0][2] = target.board[0][0];
//
//        _buffer[2].board[1][0] = target.board[2][1];
//        _buffer[2].board[1][1] = target.board[1][1];
//        _buffer[2].board[1][2] = target.board[0][1];
//
//        _buffer[2].board[2][0] = target.board[2][2];
//        _buffer[2].board[2][1] = target.board[1][2];
//        _buffer[2].board[2][2] = target.board[0][2];
//
//        return _buffer;
//    }
//
//    public static List<Move> checkForSymmetries(List<Move> moves, Board.Cell[][] original){
//        List<Board> _boards = new ArrayList<>();
//        List<Move> movesToDelete = new ArrayList<>();
//
//        Board[] rotatedBoards = new Board[3];
//        rotatedBoards[0] = new Board();
//        rotatedBoards[1] = new Board();
//        rotatedBoards[2] = new Board();
//
//        Board buffer = new Board();
//        for(Move move : moves){
//            buffer.copyBoard(original);
//            buffer.Move(move);
//            _boards.add(buffer);
//        }
//        buffer = null;
//
//        for(int movesIndex = 1 ; movesIndex < _boards.size() ; movesIndex++ ){
//
//            rotatedBoards = rotateBoard(_boards.get(movesIndex));
//
//            for(int i = 0 ; i < movesIndex ; i++ ) {
//                for (Board rotatedBoard : rotatedBoards) {
//                    if (rotatedBoard.sameBoard(_boards.get(i))) {
//                        moves.get(movesIndex).symmetries.add(moves.get(i));
//                        moves.get(movesIndex).hasSymmetries = true;
//                        movesToDelete.add(moves.get(i));
//                        break;
//                    }
//                }
//            }
//        }
//        for (Move move : movesToDelete) {
//            moves.remove(move);
//        }
//        return moves;
//    }
//
//}
//
