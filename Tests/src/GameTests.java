import GUI.GameBoard.GameBoard;
import Players.Player;
import TicTacToe.Board;
import TicTacToe.Game;
import TicTacToe.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTests {

	Game game;
	Player player1;
	Player player2;
	Player player3;
	Player player4;
	boolean XTurn = false;


	@BeforeEach
	void setUp() {
		player1 = new Player("Human1");
		player2 = new Player("Human2");
		player3 = new Player("HALL", Player.Algorithm.HALL);

		game = new Game(player1, player2);

		GameBoard.GameBoard.newGame(player3, player1);
	}





	@Test
	@DisplayName("Checking method nextTurn.")
	void testNextTurn() {
		Game gameTest = GameBoard.GameBoard.getGame();
		gameTest.nextTurnTest();

		assertAll("Asserting moves",
				()-> assertEquals(gameTest.board.board[0][0], Board.Cell.X, "Specific cell should be X."),
				()-> assertEquals(gameTest.board.board[0][1], Board.Cell.BLANK, "Specific cell should be X."),
				()-> assertEquals(gameTest.board.board[0][2], Board.Cell.BLANK, "Specific cell should be X."),
				()-> assertEquals(gameTest.board.board[1][0], Board.Cell.BLANK, "Specific cell should be X."),
				()-> assertEquals(gameTest.board.board[1][1], Board.Cell.BLANK, "Specific cell should be X."),
				()-> assertEquals(gameTest.board.board[1][2], Board.Cell.BLANK, "Specific cell should be X."),
				()-> assertEquals(gameTest.board.board[2][0], Board.Cell.BLANK, "Specific cell should be X."),
				()-> assertEquals(gameTest.board.board[2][1], Board.Cell.BLANK, "Specific cell should be X."),
				()-> assertEquals(gameTest.board.board[2][2], Board.Cell.BLANK, "Specific cell should be X.")
				);
	}

	@Test
	@DisplayName("Checking method parseMove.")
	void testParseMove() {
		//first move
		Move move = new Move(0,0);

		game.parseMove(move);

		assertEquals(game.board.board[0][0], Board.Cell.O, "Specific cell should be O.");

		assertEquals(game.board.board[0][1], Board.Cell.BLANK, "Specific cell should be blank.");
		assertEquals(game.board.board[0][2], Board.Cell.BLANK, "Specific cell should be blank.");

		for(int i = 1; i < 3; i++)
			for(int j = 0; j < 3; j++)
					assertEquals(game.board.board[i][j], Board.Cell.BLANK, "Cell should be blank.");

		//second move
		Move move1 = new Move(1,1);

		game.parseMove(move1);

		assertEquals(game.board.board[1][1], Board.Cell.X, "Specific cell should be X.");

	}

}
