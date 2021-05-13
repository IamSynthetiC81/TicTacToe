//package ΑΙ;
//
//import DynamicMemory.List;
//import TicTacToe.Board;
//import TicTacToe.BoardHandler;
//import TicTacToe.Move;
//
//import java.util.Random;
//
//public class AI implements BoardHandler,SymmetriesHandler {
//    private boolean isHALL = false;
//    Random random = new Random();
//
//    public static Board.Cell opponent = Board.Cell.X;
//    public static Board.Cell ai = Board.Cell.O;
//
//    public AI(Algorithm ai){
//        if(ai == Algorithm.HALL){
//            isHALL = true;
//        }
//    }
//
//    public Move getMove(Board board, Board.Cell player){
//        if(isHALL) {
//            if (player == Board.Cell.X) {
//                ai = player;
//                opponent = Board.Cell.O;
//            } else {
//                ai = player;
//                opponent = Board.Cell.X;
//            }
//            List<Move> bestMove = new List<>();
//            int bestScore;
//            bestScore = -100;
//            List<Move> moves = board.findAvailableMoves(board, player);
//            for (Move move : moves) {
//                board.Move(move);
//                int score = minimax(board, 9, player == opponent, -100, 100);
//                board.clearMove(move);
//                if (player == ai) {
//                    if (score > bestScore) {
//                        bestScore = score;
//                        bestMove.empty();
//                        bestMove.add(move,0);
//                    }
//                } else {
//                    if (score < bestScore) {
//                        bestScore = score;
//                        bestMove.empty();
//                        bestMove.add(move,0);
//                    }
//                    if (score == bestScore){
//                        bestMove.add(move);
//                    }
//                }
//            }
//            if(bestMove.size() > 1){
//                Move move = bestMove.get(random.nextInt(bestMove.size()));
//                if(move.hasSymmetries){
//                    move.symmetries.add(move);
//                    return move.symmetries.get(random.nextInt(move.symmetries.size()));
//                }
//            }else if (bestMove.get(0).hasSymmetries) {
//
//                bestMove.get(0).symmetries.add(bestMove.get(0));
//                return bestMove.get(0).symmetries.get(random.nextInt(bestMove.get(0).symmetries.size()));
//            }
//            return bestMove.get(0);
//        }else{
//            List<Move> AvailableMoves = board.findAvailableMoves(board,player);
//            Move AIMove =  AvailableMoves.get(random.nextInt(AvailableMoves.size()));
//            if(AIMove.hasSymmetries){
//                AIMove.symmetries.add(AIMove);
//                return AIMove.symmetries.get(random.nextInt(AIMove.symmetries.size()));
//            }else{
//                return AIMove;
//            }
//        }
//    }
//
//    public static int minimax (Board board, int depth, boolean maxPlayer, int alpha, int beta) {
//        Board.Result winner = board.GetResult();
//        if (winner != Board.Result.Unknown || depth == 0) {
//            if ((winner == Board.Result.OWins && ai == Board.Cell.O) || (winner == Board.Result.XWins && ai == Board.Cell.X)) {
//                return (depth + 1);
//            } else if ((winner == Board.Result.OWins && ai == Board.Cell.X) || (winner == Board.Result.XWins && ai == Board.Cell.O)) {
//                return -(depth + 1);
//            } else if (winner == Board.Result.Tie) {
//                return 0;
//            }
//        }
//
//        List<Move> moves = board.findAvailableMoves(board,maxPlayer ? ai : opponent);
//
//        int bestScore;
//        if(maxPlayer) {
//            bestScore = -100;
//            for (Move move : moves) {
//                board.Move(move);
//                int currentScore = minimax(board,depth - 1, false, alpha, beta);
//                board.clearMove(move);
//                bestScore = Math.max(bestScore, currentScore);
//                alpha = Math.max(alpha, currentScore);
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//        }else{
//            bestScore = +100;
//            for (Move move : moves) {
//                board.Move(move);
//                int currentScore = minimax(board,depth - 1, true, alpha, beta);
//                board.clearMove(move);
//                bestScore = Math.min(bestScore, currentScore);
//                beta = Math.min(currentScore, beta);
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//        }
//        return bestScore;
//    }
//
//    public enum Algorithm{
//        HALL,
//        MrBean
//    };
//}
//
