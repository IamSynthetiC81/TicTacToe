package TicTacToe;

import Players.Player;

import java.io.Serializable;
import java.time.LocalDateTime;

//import java.time.format.DateTimeFormatter;

public class GameRecord implements Serializable {
    private static final int PLAYER_COUNT = 2;

    private final Player[] players;
    private Board.Result result;
    private double currentScoreX;
    private double currentScoreO;
    private State state;
    private double opponentScore;

    private LocalDateTime currentDateTime = LocalDateTime.now();;
//    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//    private String currentDateTimeS = currentDateTime.format(dateTimeFormatter);

    public GameRecord(Player playerX, Player playerO) {
        players = new Player[PLAYER_COUNT];

        players[0] = playerX;
        currentScoreX = playerX.getScore();

        players[1] = playerO;
        currentScoreO = playerO.getScore();

        result = Board.Result.Unknown;

        opponentScore = 0;
    }

    public Player getPlayer(int i) {
        return players[i];
    }

//    public String getCurrentDateTimeS() {
//        return currentDateTimeS;
//    }

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

    public void setState(State state){
        this.state = state;
    }

    public void setOpponentScore(int opponentScore){
        this.opponentScore = opponentScore;
    }

    public void setCurrentDateTime(LocalDateTime dateTime){
        this.currentDateTime = dateTime;
    }

    public void updatePlayers() {
        GameRecord tempGameRecord1 = new GameRecord(players[0], players[1]);
        GameRecord tempGameRecord2 = new GameRecord(players[0], players[1]);

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

    private void copy(GameRecord gameRecordCopy) {
        result = gameRecordCopy.getResult();
        state = gameRecordCopy.getState();
        opponentScore = gameRecordCopy.getOpponentScore();
    }

    public enum State{
        Defeat,
        Tie,
        Win
    }
}
