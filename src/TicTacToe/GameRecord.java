package TicTacToe;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public class GameRecord {
    private Player[] players;
    private Board.Result result;
    private double currentScoreX;
    private double currentScoreO;
    private LocalDateTime currentDateTime;
    private String currentDateTimeS;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private State state;
    private double opponentScore;

    public GameRecord(Player playerX, Player playerO) {
        players = new Player[2];

        players[0] = playerX;
        currentScoreX = playerX.score;

        players[1] = playerO;
        currentScoreO = playerO.score;

        result = Board.Result.Unknown;

        currentDateTime = LocalDateTime.now();
        currentDateTimeS = currentDateTime.format(dateTimeFormatter);

        opponentScore = 0;
    }

    public Player getPlayer(int i) {
        return players[i];
    }

    public String getCurrentDateTimeS() {
        return currentDateTimeS;
    }

    public double getOpponentScore() {
        return opponentScore;
    }

    public State getState() {
        return state;
    }

    public Board.Result getResult() {
        return result;
    }

    public double getCurrentScoreX() {
        return currentScoreX;
    }

    public double getCurrentScoreO() {
        return currentScoreO;
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setResult(Board.Result result) {
        this.result = result;
    }

    public void updatePlayers() {
        GameRecord tempGameRecord1 = new GameRecord(this.players[0], this.players[1]);
        GameRecord tempGameRecord2 = new GameRecord(this.players[0], this.players[1]);

        if(result == Board.Result.XWins){
            players[0].addWin();
            players[0].scoreCalculator();
            state = State.Win;
            opponentScore = currentScoreO;
            tempGameRecord1.copy(this);
            players[0].insertBestGame(tempGameRecord1);
            players[0].insertRecentGame(tempGameRecord1);

            players[1].addLoss();
            players[1].scoreCalculator();
            state = State.Defeat;
            opponentScore = currentScoreX;
            tempGameRecord2.copy(this);
            players[1].insertBestGame(tempGameRecord2);
            players[1].insertRecentGame(tempGameRecord2);
        }

        else if(result == Board.Result.OWins){
            players[1].addWin();
            players[1].scoreCalculator();
            state = State.Win;
            opponentScore = currentScoreX;
            tempGameRecord1.copy(this);
            players[1].insertBestGame(tempGameRecord1);
            players[1].insertRecentGame(tempGameRecord1);

            players[0].addLoss();
            players[0].scoreCalculator();
            state = State.Defeat;
            opponentScore = currentScoreO;
            tempGameRecord2.copy(this);
            players[0].insertBestGame(tempGameRecord2);
            players[0].insertRecentGame(tempGameRecord2);
        }

        else if(result == Board.Result.Tie){
            players[0].addTies();
            players[0].scoreCalculator();
            state = State.Tie;
            opponentScore = currentScoreO;
            tempGameRecord1.copy(this);
            players[0].insertBestGame(tempGameRecord1);
            players[0].insertRecentGame(tempGameRecord1);

            players[1].addTies();
            players[1].scoreCalculator();
            state = State.Tie;
            opponentScore = currentScoreX;
            tempGameRecord2.copy(this);
            players[1].insertBestGame(tempGameRecord2);
            players[1].insertRecentGame(tempGameRecord2);
        }
    }

/*    private void copy(GameRecord gameRecordCopy) {
        this.players[0] = gameRecordCopy.players[0];
        this.players[1] = gameRecordCopy.players[1];
        this.result = gameRecordCopy.result;
        this.currentScoreO = gameRecordCopy.currentScoreO;
        this.currentScoreX = gameRecordCopy.currentScoreX;
        this.currentDateTime = gameRecordCopy.currentDateTime;
        this.currentDateTimeS = gameRecordCopy.currentDateTimeS;
        this.state = gameRecordCopy.state;
    }*/

    private void copy(GameRecord gameRecordCopy) {
//        this.players[0] = gameRecordCopy.getPlayer(0);
//        this.players[1] = gameRecordCopy.getPlayer(1);
        this.result = gameRecordCopy.getResult();
//        this.currentScoreO = gameRecordCopy.getCurrentScoreO();
//        this.currentScoreX = gameRecordCopy.getCurrentScoreX();
//        this.currentDateTime = gameRecordCopy.getCurrentDateTime();
//        this.currentDateTimeS = gameRecordCopy.getCurrentDateTimeS();
        this.state = gameRecordCopy.getState();
    }

    public enum State{
        Defeat,
        Tie,
        Win
    }
}
