package ΑΙ;

import DynamicMemory.List;
import TicTacToe.Board;
import TicTacToe.BoardHandler;
import TicTacToe.Move;

import java.util.Random;

public class AI implements BoardHandler {
    private Board.Cell maxim;
    private Board.Cell minim;
    private boolean isHALL = false;
    Random random = new Random();

    public AI(Algorithm ai){
        if(ai == Algorithm.HALL){
            isHALL = true;
        }
    }

    public Move getMove(Board board, Board.Cell player) {
        if (isHALL) {
            if (player == Board.Cell.O) {
                maxim = Board.Cell.O;
                minim = Board.Cell.X;
            } else {
                maxim = Board.Cell.X;
                minim = Board.Cell.O;
            }

            Move bestMove = null;
            int bestScore;
            if (player == maxim) {
                bestScore = -100;
            } else {
                bestScore = 100;
            }
            List<Move> moves = board.findAvailableMoves(board, player);
            for (Move move : moves) {
                board.Move(move);
                int score = minimax(board, 9, player == minim, -100, 100);
                board.clearMove(move);
                if (player == maxim) {
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
            return AvailableMoves.get(random.nextInt(AvailableMoves.size()));
        }
    }

    private int minimax(Board board, int depth, boolean maxPlayer, int alpha, int beta) {
        Board.Result winner = board.GetResult();
        if (winner != Board.Result.Unknown || depth == 0) {
            if((winner == Board.Result.OWins && maxim == Board.Cell.O) || (winner == Board.Result.XWins && maxim == Board.Cell.X)){
                return (depth+1);
            }else if ((winner == Board.Result.OWins && maxim == Board.Cell.X) || (winner == Board.Result.XWins && maxim == Board.Cell.O)) {
                return -(depth + 1);
            }else{
                return 0;
            }
        }

        List<Move> moves = board.findAvailableMoves(board,maxPlayer ? minim : maxim);

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

    public enum Algorithm{
        HALL,
        MrBean
    };
}

