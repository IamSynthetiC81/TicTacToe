package TicTacToe;

public class Player {
    String name;
    int gamesNum;
    int winsNum;
    int lossesNum;
    int tiesNum;
    double score;
    GameRecord[] recentGames;
    GameRecord[] bestGames;

    public Player() {
        gamesNum = winsNum = lossesNum = tiesNum = 0;
        score = 0;
        recentGames = new GameRecord[5];
        bestGames = new GameRecord[5];
    }

    public void playersName(String name) {
        if(name.strip().length() > 20){
            System.out.println("Max 20 characters. Try again.");
            return;
        }

        this.name = name.strip();
    }

    public void scoreCalculator() {
        score = 50 * ((2 * winsNum + tiesNum) / gamesNum);
        return;
    }

    public void insertRecentGame(GameRecord game) {

        for(int i = 0; i < recentGames.length; i++){
            if(recentGames[i] == null){
                recentGames[i] = game;
                return;
            }
        }

        for(int i = 0; i < recentGames.length - 1; i++)
            recentGames[i] = recentGames[i+1];

        recentGames[4] = game;

        return;
    }

    public boolean betterGame(GameRecord betterGame, GameRecord worseGame) {
        if(betterGame.getState().ordinal() > worseGame.getState().ordinal())
            return true;

        else if(betterGame.getState().ordinal() == worseGame.getState().ordinal())
            if(betterGame.getOpponentScore() > worseGame.getOpponentScore())
                return true;

        else if(betterGame.getState().ordinal() == worseGame.getState().ordinal() && betterGame.getOpponentScore() == worseGame.getOpponentScore())
            if(betterGame.getCurrentDateTime().isAfter(worseGame.getCurrentDateTime()))
                return true;

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
                for (int j = i + 1; j < bestGames.length; j++)
                    if (bestGames[i] != null && bestGames[j] != null && betterGame(bestGames[j], bestGames[i])) {
                        tempGameRecord = bestGames[j];
                        bestGames[j] = bestGames[i];
                        bestGames[i] = tempGameRecord;

                    }
            }
        }
        else{
            for(int k = 0; k < bestGames.length; k++) {
                if (betterGame(game, bestGames[k])) {

                    for (int z = bestGames.length - 1; z > k; z++)
                        bestGames[z] = bestGames[z - 1];

                    bestGames[k] = game;

                    break;
                }
            }
        }
    }

    public void addWin() {
        this.gamesNum++;
        this.winsNum++;
    }

    public void addLoss() {
        this.gamesNum++;
        this.lossesNum++;
    }

    public void addTies() {
        this.gamesNum++;
        this.tiesNum++;
    }
}
