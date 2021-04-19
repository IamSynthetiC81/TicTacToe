import java.util.List;
import java.util.Random;

public class AI {

    public static Board.Cell human = Board.Cell.X;
    public static Board.Cell ai = Board.Cell.O;


    public static Move getBestMove(Board board){
        Move bestMove = null;
        int bestScore = -100;
        for(Move move : board.findAvailableMoves(ai,board.board)){
            board.Move(move);
            int score = minimax(board);
            board.clearMove(move);
            if(score > bestScore){
                bestScore = score;
                bestMove = move;
            }
        }
        if(bestMove.hasSymmetries){
            Random random = new Random();
            return bestMove.symmetries.get(random.nextInt(bestMove.symmetries.size()));
        }
        return bestMove;
    }

    public static int minimax (Board board) {
        Board.Result winner = board.GetResult();
        if (winner != Board.Result.Unknown) {
            if(winner == Board.Result.OWins){
                return 2;
            }else if (winner == Board.Result.XWins){
                return -2;
            }else if (winner == Board.Result.Tie){
                return 0;
            }
        }

        int aiCount = 0;
        int humanCount = 0;
        for(int i = 0 ; i < board.board.length ; i++ ){
            for ( int j = 0 ; j < board.board[i].length ; j++ ){
                if(board.board[i][j] == ai){
                    aiCount++;
                }else if(board.board[i][j] == human){
                    humanCount++;
                }
            }
        }

        int bestScore;
        if (humanCount > aiCount) {
            bestScore = -1;
        }else{
            bestScore = 1;
        }
        List<Move> moves;
        try {
            moves = board.findAvailableMoves(humanCount > aiCount ? ai : human, board.board);
        }catch (StackOverflowError f){
            return 0;
        }
        for(Move move : moves){
            board.Move(move);
            int currentScore = minimax(board);
            board.clearMove(move);
            if (humanCount > aiCount ? currentScore > bestScore : currentScore < bestScore) {
                bestScore = currentScore;
            }
        }
        return bestScore;
    }
}

