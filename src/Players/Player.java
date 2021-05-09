package Players;

import TicTacToe.Board;
import TicTacToe.GameRecord;
import TicTacToe.Move;
import ΑΙ.AI;

import javax.naming.InvalidNameException;
import java.util.InvalidPropertiesFormatException;

public class Player{
    private static final int MAX_CHAR_NUMBER = 20;
    private static final int RECENT_GAMES_LENGTH = 5;
    private static final int BEST_GAMES_LENGTH = 5;


    String name;
    private int gamesNum;
    private int winsNum;
    private int lossesNum;
    private int tiesNum;
    private double score;

    public boolean isHuman = true;
    public PlayerAI AI = null;

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

    public Player(String name, ΑΙ.AI.Algorithm ai){
        insertPlayersName(name);

        gamesNum =0;
        winsNum = 0;
        lossesNum =0;
        tiesNum = 0;
        score = 0;
        recentGames = new GameRecord[RECENT_GAMES_LENGTH];
        bestGames = new GameRecord[BEST_GAMES_LENGTH];

        isHuman = false;
        AI = new PlayerAI(ai);
    }

    public void insertPlayersName(String name){
        if(name.strip().length() > 20){
            System.out.println("Invalid name");
//            throw new InvalidNameException("Player name \"" +name+ "\" is too big\nPlease enter a valid name of " +MAX_CHAR_NUMBER+ " characters max");
        }else {
            this.name = name.strip();
        }
    }

    public void scoreCalculator() {
        score = 50 * ((double)(2 * winsNum + tiesNum) / gamesNum);
    }

    public void insertRecentGame(GameRecord game) {
        for(int i = 0; i < recentGames.length; i++){
            if(recentGames[i] == null){
                recentGames[i] = game;
                return;
            }
        }

        for(int i = 0; i < RECENT_GAMES_LENGTH - 1; i++) {
            recentGames[i] = recentGames[i + 1];
        }
        recentGames[4] = game;

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
            for(int k = 1; k < bestGames.length; k++) {
                if (!betterGame(game, bestGames[k])) {
                    for(int z = 0 ; z < k - 1  ; z++){
                        bestGames[z] = bestGames[z+1];
                    }
                    bestGames[k-1] = game;

//                for (int z = k ; z > 0; z--) {
//                    bestGames[z] = bestGames[z - 1];
//                }


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

    public static class PlayerAI extends AI{
        private Board.Cell symbol;
        AI.Algorithm ai;
        public PlayerAI(Algorithm ai) {
            super(ai);
            this.ai = ai;
        }
    }
}
