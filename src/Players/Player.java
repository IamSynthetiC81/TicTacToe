package Players;

import DynamicMemory.List;
import TicTacToe.Board;
import TicTacToe.BoardHandler;
import TicTacToe.GameRecord;
import TicTacToe.Move;
import ΑΙ.SymmetriesHandler;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable {
    private static final int MAX_CHAR_NUMBER = 20;
    private static final int RECENT_GAMES_LENGTH = 5;
    private static final int BEST_GAMES_LENGTH = 5;

    String name;
    private int gamesNum;
    private int winsNum;
    private int lossesNum;
    private int tiesNum;
    private double score;
    private double recentScore;

    public boolean isHuman = true;
    public AI AI = null;


    private GameRecord[] recentGames;
    private GameRecord[] bestGames;

    public Player(String name){
        insertPlayersName(name);

        gamesNum =0;
        winsNum = 0;
        lossesNum =0;
        tiesNum = 0;
        score = 0;
        recentGames = new GameRecord[RECENT_GAMES_LENGTH];
        bestGames = new GameRecord[BEST_GAMES_LENGTH];

    }

    public Player(String name, Algorithm ai){
        insertPlayersName(name);

        gamesNum =0;
        winsNum = 0;
        lossesNum =0;
        tiesNum = 0;
        score = 0;
        recentGames = new GameRecord[RECENT_GAMES_LENGTH];
        bestGames = new GameRecord[BEST_GAMES_LENGTH];

        isHuman = false;
        AI = new AI(ai);
    }

    public void insertPlayersName(String name){
        if(name.strip().length() > 20){
            System.out.println("Invalid name");
        }else {
            this.name = name.strip();
        }
    }

    public void scoreCalculator() {
        score = 50 * ((double)(2 * winsNum + tiesNum) / gamesNum);
    }

    public void recentScoreCalculator(GameRecord[] recentGames) {
        int recentWinsNum = 0;
        int recentTiesNum = 0;
        for(GameRecord game : recentGames){
            if(game != null) {
                if (game.getState() == GameRecord.State.Win) {
                    recentWinsNum++;
                } else if (game.getState() == GameRecord.State.Tie) {
                    recentTiesNum++;
                }
            }
        }

        recentScore = 50 * ((double)(2 * recentWinsNum + recentTiesNum) / (gamesNum > 5 ? 5 : gamesNum));
    }

    public double getRecentScore(){
        return recentScore;
    }

    public void insertRecentGame(GameRecord game) {
        for(int i = 0; i < recentGames.length; i++){
            if(recentGames[i] == null){
                recentGames[i] = game;
                recentScoreCalculator(recentGames);
                return;
            }
        }

        for(int i = 0; i < RECENT_GAMES_LENGTH - 1; i++) {
            recentGames[i] = recentGames[i + 1];
        }
        recentGames[4] = game;
        recentScoreCalculator(recentGames);

    }

    public boolean betterGame(GameRecord betterGame, GameRecord worseGame) {
        if(betterGame.getState().ordinal() > worseGame.getState().ordinal()) {
            return true;
        }else if(betterGame.getState().ordinal() == worseGame.getState().ordinal()) {
            if (betterGame.getOpponentScore() > worseGame.getOpponentScore()) {
                return true;
            }
        }else if(betterGame.getState().ordinal() == worseGame.getState().ordinal() && betterGame.getOpponentScore() == worseGame.getOpponentScore()) {
            if (betterGame.getCurrentDateTime().isAfter(worseGame.getCurrentDateTime())) {
                return true;
            }
        }
        return false;
    }

    public void insertBestGame(GameRecord game) {
        GameRecord tempGameRecord;
        boolean full = true;

        for(int i = 0; i < bestGames.length; i++) {
            if (bestGames[i] == null) {
                bestGames[i] = game;
                full = false;
                break;
            }
        }
        if(!full) {
            for (int i = 0; i < bestGames.length - 1; i++) {
                for (int j = i + 1; j < bestGames.length; j++) {
                    if (bestGames[i] != null && bestGames[j] != null && betterGame(bestGames[j], bestGames[i])) {
                        tempGameRecord = bestGames[j];
                        bestGames[j] = bestGames[i];
                        bestGames[i] = tempGameRecord;
                    }
                }
            }
        }
        else{
            for(int k = 0; k < bestGames.length; k++) {
                if (betterGame(game, bestGames[k])) {
                    for(int z = bestGames.length - 1 ; z > k  ; z--){
                        bestGames[z] = bestGames[z-1];
                    }
                    bestGames[k] = game;

                    break;
                }
            }
        }
    }

    public String getName(){
        return name;
    }

    public int getGamesNum() {
        return gamesNum;
    }

    public int getWinsNum() {
        return winsNum;
    }

    public int getLossesNum() {
        return lossesNum;
    }

    public double getScore() {
        return score;
    }

    public GameRecord[] getRecentGames() {
        return recentGames;
    }

    public GameRecord[] getBestGames() {
        return bestGames;
    }

    public void addWin() {
        this.gamesNum++;
        this.winsNum++;
        scoreCalculator();
    }

    public void addLoss() {
        this.gamesNum++;
        this.lossesNum++;
        scoreCalculator();
    }

    public void addTies() {
        this.gamesNum++;
        this.tiesNum++;
        scoreCalculator();
    }

    public Move getMove(Board board, Board.Cell player){
        return AI.getMove(board,player);
    }

    public static class AI implements BoardHandler, SymmetriesHandler, Serializable{
        private boolean isHALL = false;
        Random random = new Random();

        public Board.Cell opponent = Board.Cell.X;
        public Board.Cell ai = Board.Cell.O;

        public AI(Algorithm ai) {
            if (ai == Algorithm.HALL) {
                isHALL = true;
            }
        }

        public Move getMove(Board board, Board.Cell player) {
            if (isHALL) {
                if (player == Board.Cell.X) {
                    ai = player;
                    opponent = Board.Cell.O;
                } else {
                    ai = player;
                    opponent = Board.Cell.X;
                }
                List<Move> bestMove = new List<>();
                int bestScore;
                bestScore = -100;
                List<Move> moves = board.findAvailableMoves(board, player);
                for (Move move : moves) {
                    board.Move(move);
                    int score = minimax(board, 9, player == opponent, -100, 100);
                    board.clearMove(move);
                    if (player == ai) {
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove.empty();
                            bestMove.add(move, 0);
                        }
                    } else {
                        if (score < bestScore) {
                            bestScore = score;
                            bestMove.empty();
                            bestMove.add(move, 0);
                        }
                        if (score == bestScore) {
                            bestMove.add(move);
                        }
                    }
                }
                if (bestMove.size() > 1) {
                    Move move = bestMove.get(random.nextInt(bestMove.size()));
                    if (move.hasSymmetries) {
                        move.symmetries.add(move);
                        return move.symmetries.get(random.nextInt(move.symmetries.size()));
                    }
                } else if (bestMove.get(0).hasSymmetries) {

                    bestMove.get(0).symmetries.add(bestMove.get(0));
                    return bestMove.get(0).symmetries.get(random.nextInt(bestMove.get(0).symmetries.size()));
                }
                return bestMove.get(0);
            } else {
                List<Move> AvailableMoves = board.findAvailableMoves(board, player);
                Move AIMove = AvailableMoves.get(random.nextInt(AvailableMoves.size()));
                if (AIMove.hasSymmetries) {
                    AIMove.symmetries.add(AIMove);
                    return AIMove.symmetries.get(random.nextInt(AIMove.symmetries.size()));
                } else {
                    return AIMove;
                }
            }
        }

        public int minimax(Board board, int depth, boolean maxPlayer, int alpha, int beta) {
            Board.Result winner = board.GetResult();
            if (winner != Board.Result.Unknown || depth == 0) {
                if ((winner == Board.Result.OWins && ai == Board.Cell.O) || (winner == Board.Result.XWins && ai == Board.Cell.X)) {
                    return (depth + 1);
                } else if ((winner == Board.Result.OWins && ai == Board.Cell.X) || (winner == Board.Result.XWins && ai == Board.Cell.O)) {
                    return -(depth + 1);
                } else if (winner == Board.Result.Tie) {
                    return 0;
                }
            }

            List<Move> moves = board.findAvailableMoves(board, maxPlayer ? ai : opponent);

            int bestScore;
            if (maxPlayer) {
                bestScore = -100;
                for (Move move : moves) {
                    board.Move(move);
                    int currentScore = minimax(board, depth - 1, false, alpha, beta);
                    board.clearMove(move);
                    bestScore = Math.max(bestScore, currentScore);
                    alpha = Math.max(alpha, currentScore);
                    if (beta <= alpha) {
                        break;
                    }
                }
            } else {
                bestScore = +100;
                for (Move move : moves) {
                    board.Move(move);
                    int currentScore = minimax(board, depth - 1, true, alpha, beta);
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

    public enum Algorithm {
        HALL,
        MrBean
    }
}
