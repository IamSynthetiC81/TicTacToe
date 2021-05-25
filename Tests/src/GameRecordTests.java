
import Players.Player;
import TicTacToe.Board;
import TicTacToe.GameRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameRecordTests {

	Player player1;
	Player player2;
	GameRecord gameRecord;
	
	@BeforeEach
	void setUp() {
		player1 = new Player("HALL", Player.Algorithm.HALL);
		player2 = new Player("Human");
		
		gameRecord = new GameRecord(player1, player2);
	}
	
	@Test
	@DisplayName("When X wins the game (player1), updatePlayers method should give info of the game to the 2 players. Method copy is also checked.")
	void testUpdatePlayers() {
		Board.Result winner = Board.Result.XWins;
		
		gameRecord.setResult(winner);
		gameRecord.updatePlayers();
		
		GameRecord[] player1GR = player1.getRecentGames();
		GameRecord[] player2GR = player2.getRecentGames();
		
		assertAll("Asserting stats of the 2 players",
				()-> assertEquals(player1.getWinsNum(), 1, "player1 should have 1 win."),
				()-> assertEquals(player1.getLossesNum(), 0, "player1 should have 0 losses."),
				()-> assertEquals(player1.getTiesNum(), 0, "player1 should have 0 ties."),
				()-> assertEquals(player1.getScore(), 100, "player1 should have score 100."),
				()-> assertEquals(player1GR[0].getResult(),Board.Result.XWins , "Result should be XWins."),
				()-> assertEquals(player1GR[0].getOpponentScore(), 0, "Opponent's score should be 0."),
				()-> assertEquals(player1GR[0].getState(), GameRecord.State.Win, "State should be win."),
				
				
				()-> assertEquals(player2.getWinsNum(), 0, "player2 should have 0 wins."),
				()-> assertEquals(player2.getLossesNum(), 1, "player2 should have 1 loss."),
				()-> assertEquals(player2.getTiesNum(), 0, "player2 should have 0 ties."),
				()-> assertEquals(player2.getScore(), 0, "player2 should have score 0."),
				()-> assertEquals(player2GR[0].getResult(),Board.Result.XWins , "Result should be XWins."),
				()-> assertEquals(player2GR[0].getOpponentScore(), 0, "Opponent's score should be 0."),
				()-> assertEquals(player2GR[0].getState(), GameRecord.State.Defeat, "State should be defeat.")
				);
	}
	
}
