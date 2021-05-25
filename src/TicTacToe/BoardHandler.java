package TicTacToe;

import java.util.Arrays;

public interface BoardHandler {

    static void copyBoardToBoard(Board.Cell[][] Source, Board.Cell[][] Target) {
        if(Source.length == Target.length) {
            for (int i = 0; i < Source.length; i++) {
                System.arraycopy(Source[i], 0, Target[i], 0, Source[i].length);
            }
        }else{
            throw new ArrayIndexOutOfBoundsException("Incompatible sizes");
        }
    }

    static boolean sameBoard(Board Source, Board Target) {
        if (Source.board.length == Target.board.length) {
            for (int i = 0; i < Source.board.length; i++) {
                for (int j = 0; j < Source.board[i].length; j++) {
                    if (Source.board[i][j] != Target.board[i][j]) {
                        return false;
                    }
                }
            }

        }else{
            throw new ArrayIndexOutOfBoundsException("Incompatible boards");
        }
        return true;
    }

    default void resetBoard(Board.Cell[][] board) {
        for (Board.Cell[] cells : board) {
            Arrays.fill(cells, Board.Cell.BLANK);
        }
    }



    default void ParseGameData(Game game){



    }

}
