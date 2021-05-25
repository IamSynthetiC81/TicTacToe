import AIHelperFunctions.SymmetriesHandler;
import DynamicMemory.List;
import TicTacToe.Board;
import TicTacToe.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymmetriesTest {
    Board.Cell[][] board;

    @BeforeEach
    void BeforeAll(){
        board = new Board.Cell[][]{
                {Board.Cell.BLANK, Board.Cell.BLANK, Board.Cell.BLANK},
                {Board.Cell.BLANK, Board.Cell.BLANK, Board.Cell.BLANK},
                {Board.Cell.BLANK, Board.Cell.BLANK, Board.Cell.BLANK}
        };
    }

    @Test
    @DisplayName("Test compression of empty board")
    void EmptyBoardMovesTest(){
        List<Move> moves = new List<Move>();

        for(int i = 0 ; i < 3 ; i++ ){
            for(int j = 0 ; j < 3 ; j++){
                if(board[i][j].equals(Board.Cell.BLANK)) moves.add(new Move(i,j, Board.Cell.X));
            }
        }

        int priorSize = moves.size();

        SymmetriesHandler.checkForSymmetries(moves,board);

        assertAll(()->{
            assertTrue(priorSize >= moves.size());
            assertEquals(3, moves.size());
        });


    }


}
