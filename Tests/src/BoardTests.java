import DynamicMemory.List;
import TicTacToe.Board;
import TicTacToe.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BoardTests {	
	@Test
	@DisplayName("Checking initialized board.")
	void testInitBoard() {
		Board board = new Board();
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
					assertEquals(board.board[i][j], Board.Cell.BLANK, "Cell should be blank.");
			
	}
	
	@Test
	@DisplayName("Using X as an input in a specific cell should change the cell from blank to X. The method clearMove should change the same cell from X to blank.")
	void testMoveAndClearMove() {
		Board board = new Board();
		
		Move move = new Move(0, 0, Board.Cell.X);
		
		board.move(move);
		
		assertEquals(board.board[0][0], Board.Cell.X, "Specific cell should be X.");
		
		board.clearMove(move);
		
		assertEquals(board.board[0][0], Board.Cell.BLANK, "Specific cell should be X.");
	}
	
	@Test
	@DisplayName("Checking if getResult method works properly.")
	void testGetResult() {
		Board board = new Board();
		
		Move move1 = new Move(0, 0, Board.Cell.X);
		Move move2 = new Move(0, 1, Board.Cell.X);
		Move move3 = new Move(0, 2, Board.Cell.X);
		Move move4 = new Move(1, 0, Board.Cell.X);
		Move move5 = new Move(1, 1, Board.Cell.X);
		Move move6 = new Move(1, 2, Board.Cell.X);
		Move move7 = new Move(2, 0, Board.Cell.X);
		Move move8 = new Move(2, 1, Board.Cell.X);
		Move move9 = new Move(2, 2, Board.Cell.X);
		
		board.move(move1);
		board.move(move2);
		board.move(move3);
		
		assertEquals(board.GetResult(), Board.Result.XWins, "X should win.");
		
		board.clearMove(move1);
		board.clearMove(move2);
		board.clearMove(move3);
		
		board.move(move4);
		board.move(move5);
		board.move(move6);
		
		assertEquals(board.GetResult(), Board.Result.XWins, "X should win.");
		
		board.clearMove(move4);
		board.clearMove(move5);
		board.clearMove(move6);
		
		board.move(move7);
		board.move(move8);
		board.move(move9);
		
		assertEquals(board.GetResult(), Board.Result.XWins, "X should win.");
		
		board.clearMove(move7);
		board.clearMove(move8);
		board.clearMove(move9);
		
		board.move(move1);
		board.move(move4);
		board.move(move7);
		
		assertEquals(board.GetResult(), Board.Result.XWins, "X should win.");
		
		board.clearMove(move1);
		board.clearMove(move4);
		board.clearMove(move7);
		
		board.move(move2);
		board.move(move5);
		board.move(move8);
		
		assertEquals(board.GetResult(), Board.Result.XWins, "X should win.");
		
		board.clearMove(move2);
		board.clearMove(move5);
		board.clearMove(move8);
		
		board.move(move3);
		board.move(move6);
		board.move(move9);
		
		assertEquals(board.GetResult(), Board.Result.XWins, "X should win.");
		
		board.clearMove(move3);
		board.clearMove(move6);
		board.clearMove(move9);
		
		board.move(move1);
		board.move(move5);
		board.move(move9);
		
		assertEquals(board.GetResult(), Board.Result.XWins, "X should win.");
		
		board.clearMove(move1);
		board.clearMove(move5);
		board.clearMove(move9);
		
		board.move(move3);
		board.move(move5);
		board.move(move7);
		
		assertEquals(board.GetResult(), Board.Result.XWins, "X should win.");
		
		board.clearMove(move3);
		
		board.move(move2);
		
		assertEquals(board.GetResult(), Board.Result.Unknown, "It should return unknown result.");
		
		//new moves with O
		Move move11 = new Move(0, 0, Board.Cell.O);
		Move move33 = new Move(0, 2, Board.Cell.O);
		Move move44 = new Move(1, 0, Board.Cell.O);
		Move move88 = new Move(2, 1, Board.Cell.O);
		
		board.move(move11);
		board.move(move33);
		board.move(move44);
		board.move(move88);
		
		board.move(move6);
		board.move(move9);
		
		assertEquals(board.GetResult(), Board.Result.Tie, "It should return tie.");
		
	}

	@Test
	@DisplayName("It should copy board to testBoard.")
	void testCopyBoard() {
		Board board = new Board();
		Board testBoard = new Board();
		
		Move move = new Move(1, 2, Board.Cell.X);
		
		board.move(move);
		
		testBoard.copyBoard(board);
		
		assertEquals(testBoard.board[1][2], Board.Cell.X, "The specific cell of testBoard should be X.");
	}
	
	@Test
	@DisplayName("Checking if board is the same with testBoard.")
	void testSameBoard() {
		Board board = new Board();
		Board testBoard = new Board();
		
		Move move = new Move(1, 2, Board.Cell.X);
		
		board.move(move);
		
		assertEquals(board.sameBoard(testBoard), false, "Board and testBoard should not be the same.");
		
		testBoard.move(move);
		
		assertEquals(board.sameBoard(testBoard), true, "Board and testBoard should be the same.");
	}
	
	@Test
	@DisplayName("Changing board's cells and resetting it should change all cells to blank.")
	void testResetBoard() {
		
		Board board = new Board();
		
		Move move1 = new Move(0, 0, Board.Cell.X);
		Move move4 = new Move(1, 0, Board.Cell.X);		
		Move move6 = new Move(1, 2, Board.Cell.X);
		
		board.move(move1);
		board.move(move4);
		board.move(move6);
		
		board.resetBoard();
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
					assertEquals(board.board[i][j], Board.Cell.BLANK, "Cell should be blank.");
	}
		
	@Test
	@DisplayName("Changing 8 board's cells should return a list with 1 cell.")
	void testFindAvailableMoves() {
		Board board = new Board();
		
		Move move1 = new Move(0, 0, Board.Cell.X);
		Move move2 = new Move(0, 1, Board.Cell.X);
		Move move3 = new Move(0, 2, Board.Cell.X);
		Move move4 = new Move(1, 0, Board.Cell.X);
		Move move6 = new Move(1, 2, Board.Cell.X);
		Move move7 = new Move(2, 0, Board.Cell.X);
		Move move8 = new Move(2, 1, Board.Cell.X);
		Move move9 = new Move(2, 2, Board.Cell.X);
		
		board.move(move1);
		board.move(move2);
		board.move(move3);
		board.move(move4);
		board.move(move6);
		board.move(move7);
		board.move(move8);
		board.move(move9);
		
		List<Move> availableMoves = board.findAvailableMoves(board, Board.Cell.O);
		
		assertEquals(availableMoves.get(0).x, 1, "x of the available move should be 1.");
		assertEquals(availableMoves.get(0).y, 1, "y of the available move should be 1.");
		assertEquals(availableMoves.size(), 1, "The size of the list sholud be 1.");
	}
}
