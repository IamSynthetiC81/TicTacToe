package ΑΙ;

import DynamicMemory.List;
import TicTacToe.Board;
import TicTacToe.Move;

import java.util.Random;

public class MiniMax implements AI_Handler_ {
    public static Board.Cell human = Board.Cell.X;
    public static Board.Cell ai = Board.Cell.O;

    public static Move getBestMove(Board board, Board.Cell player){
        Move bestMove = null;
        int bestScore;
        if(player == ai) {
            bestScore = -100;
        }else{
            bestScore = 100;
        }
        List<Move> moves = AI_Handler_.findAvailableMoves(player,board);
        for(Move move : moves){
            board.Move(move);
            int score = minimax(board, 9 , player == human,-100,100);
            board.clearMove(move);
            if(player == ai) {
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }else{
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
    }

    public static int minimax(Board board, int depth, boolean maxPlayer, int alpha, int beta) {
        Board.Result winner = board.GetResult();
        if (winner != Board.Result.Unknown || depth == 0) {
            if(winner == Board.Result.OWins){
                return depth+1;
            }else if (winner == Board.Result.XWins){
                return -(depth+1);
            }else if (winner == Board.Result.Tie){
                return 0;
            }
        }

        List<Move> moves = AI_Handler_.findAvailableMoves(maxPlayer ? ai : human, board);

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

}
