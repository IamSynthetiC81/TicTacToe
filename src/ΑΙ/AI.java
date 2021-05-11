package ΑΙ;

import DynamicMemory.List;
import TicTacToe.Board;
import TicTacToe.BoardHandler;
import TicTacToe.Move;

import java.util.Random;

public class AI implements BoardHandler,SymmetriesHandler {
    private Board.Cell maxim;
    private Board.Cell minim;
    private boolean isHALL = false;
    Random random = new Random();

    public static Board.Cell opponent = Board.Cell.X;
    public static Board.Cell ai = Board.Cell.O;

    public AI(Algorithm ai){
        if(ai == Algorithm.HALL){
            isHALL = true;
        }
    }

    public Move getMove(Board board, Board.Cell player){
        if(isHALL) {
            if (player == Board.Cell.X) {
                ai = player;
                opponent = Board.Cell.O;
            } else {
                ai = player;
                opponent = Board.Cell.X;
            }
            Move bestMove = null;
            int bestScore;
            if (player == ai) {
                bestScore = -100;
            } else {
                bestScore = 100;
            }
            List<Move> moves = board.findAvailableMoves(board, player);
            for (Move move : moves) {
                board.Move(move);
                int score = minimax(board, 9, player == opponent, -100, 100);
                board.clearMove(move);
                if (player == ai) {
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = move;
                    }
                } else {
                    if (score < bestScore) {
                        bestScore = score;
                        bestMove = move;
                    }
                }
            }
            if (bestMove != null && bestMove.hasSymmetries) {
                Random random = new Random();
                bestMove.symmetries.add(bestMove);
                return bestMove.symmetries.get(random.nextInt(bestMove.symmetries.size()));
            }
            return bestMove;
        }else{
            List<Move> AvailableMoves = board.findAvailableMoves(board,player);
            Move AIMove =  AvailableMoves.get(random.nextInt(AvailableMoves.size()));
            if(AIMove.hasSymmetries){
                AIMove.symmetries.add(AIMove);
                return AIMove.symmetries.get(random.nextInt(AIMove.symmetries.size()));
            }else{
                return AIMove;
            }
        }
    }

    public static int minimax (Board board, int depth, boolean maxPlayer, int alpha, int beta) {
        Board.Result winner = board.GetResult();
        if (winner != Board.Result.Unknown || depth == 0) {
//            if(winner == Board.Result.OWins){
//                return depth+1;
//            }else if (winner == Board.Result.XWins){
//                return -(depth+1);
//            }else if (winner == Board.Result.Tie){
//                return 0;
//            }
            if ((winner == Board.Result.OWins && ai == Board.Cell.O) || (winner == Board.Result.XWins && ai == Board.Cell.X)) {
                return (depth + 1);
            } else if ((winner == Board.Result.OWins && ai == Board.Cell.X) || (winner == Board.Result.XWins && ai == Board.Cell.O)) {
                return -(depth + 1);
            } else if (winner == Board.Result.Tie) {
                return 0;
            }
        }

        List<Move> moves = board.findAvailableMoves(board,maxPlayer ? ai : opponent);

        int bestScore;
        if(maxPlayer) {
            bestScore = -100;
            for (Move move : moves) {
                board.Move(move);
                int currentScore = minimax(board,depth - 1, false, alpha, beta);
                board.clearMove(move);
                bestScore = Math.max(bestScore, currentScore);
                alpha = Math.max(alpha, currentScore);
                if (beta <= alpha) {
                    break;
                }
            }
        }else{
            bestScore = +100;
            for (Move move : moves) {
                board.Move(move);
                int currentScore = minimax(board,depth - 1, true, alpha, beta);
                board.clearMove(move);
                bestScore = Math.min(bestScore, currentScore);
                beta = Math.min(currentScore, beta);
                if (beta <= alpha) {
                    break;
                }
            }
        }
        return bestScore;
    }

//    public Move getMove(Board board, Board.Cell player) {
//        if (isHALL) {
//            maxim = player;
//            if (player == Board.Cell.O) {
//                minim = Board.Cell.X;
//            } else {
//                minim = Board.Cell.O;
//            }
//
//            Move bestMove = null;
//            int bestScore;
//            bestScore = -100;
//            List<Move> moves = board.findAvailableMoves(board, player);
//            List<Integer> scores = new List<Integer>();
//
//            for (Move move : moves) {
//                board.Move(move);
////                int score = minimax(board, 9, false, -100, 100);
//
//                int score = minimax(board, 9, false);
//                scores.add(score);
//                board.clearMove(move);
//                if (player == maxim) {
//                    if (score > bestScore) {
//                        bestScore = score;
//                        bestMove = move;
//                    }
//                } else {
//                    if (score < bestScore) {
//                        bestScore = score;
//                        bestMove = move;
//                        System.out.println("asdjuasdsd");
//                    }
//                }
//            }
////            if (bestMove != null && bestMove.hasSymmetries) {
////                Random random = new Random();
////                bestMove.symmetries.add(bestMove);
////                return bestMove.symmetries.get(random.nextInt(bestMove.symmetries.size()));
////            }
//            return bestMove;
//        }else{
//            List<Move> AvailableMoves = board.findAvailableMoves(board,player);
//            return AvailableMoves.get(random.nextInt(AvailableMoves.size()));
//        }
//    }
//
//    private int minimax(Board board, int depth, boolean maxPlayer) {
//        Board.Result winner = board.GetResult();
//        if (winner != Board.Result.Unknown || depth == 0) {
//            if((winner == Board.Result.OWins && maxim == Board.Cell.O) || (winner == Board.Result.XWins && maxim == Board.Cell.X)){
//                return (depth+1);
//            }else if ((winner == Board.Result.OWins && maxim == Board.Cell.X) || (winner == Board.Result.XWins && maxim == Board.Cell.O)) {
//                return -(depth + 1);
//            }else if(winner == Board.Result.Tie){
//                return 0;
//            }
//        }
//
//
//        List<Move> moves = board.findAvailableMoves(board,maxPlayer ? minim : maxim);
//        List<Integer> scores = new List<Integer>();
//        int bestScore;
//        if(maxPlayer) {
//            bestScore = -100;
//            for (Move move : moves) {
//                board.Move(move);
//                int currentScore = minimax(board,depth - 1, false);
//                scores.add(currentScore);
//                board.clearMove(move);
//                bestScore = Math.max(bestScore, currentScore);
//
//            }
//        }else{
//            bestScore = +100;
//            for (Move move : moves) {
//                board.Move(move);
//                int currentScore = minimax(board,depth - 1, true);
//                scores.add(currentScore);
//                board.clearMove(move);
//                bestScore = Math.min(bestScore, currentScore);
//            }
//        }
//        return bestScore;
//    }

//    private int minimaxAlphaBeta(Board board, int depth, boolean maxPlayer, int alpha, int beta) {
//        Board.Result winner = board.GetResult();
//        if (winner != Board.Result.Unknown || depth == 0) {
//            if((winner == Board.Result.OWins && maxim == Board.Cell.O) || (winner == Board.Result.XWins && maxim == Board.Cell.X)){
//                return (depth+1);
//            }else if ((winner == Board.Result.OWins && maxim == Board.Cell.X) || (winner == Board.Result.XWins && maxim == Board.Cell.O)) {
//                return -(depth + 1);
//            }else{
//                return 0;
//            }
//        }
//
//        List<Move> moves = board.findAvailableMoves(board,maxPlayer ? minim : maxim);
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

    public enum Algorithm{
        HALL,
        MrBean
    };
}

