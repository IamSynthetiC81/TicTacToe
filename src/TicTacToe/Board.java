package TicTacToe;

import DynamicMemory.List;
import java.util.Arrays;

public class Board implements BoardHandler{
    public Cell[][] board = new Cell[][]{
        {Cell.BLANK, Cell.BLANK, Cell.BLANK},
        {Cell.BLANK, Cell.BLANK, Cell.BLANK},
        {Cell.BLANK, Cell.BLANK, Cell.BLANK}
};

    public void Move(Move move){
        if(move != null) {
            this.board[move.x][move.y] = move.inp;
        }
    }

    public void clearMove(Move move){
        board[move.x][move.y] = Cell.BLANK;
    }

    public Board.Result GetResult(){
        if(board[0][0] != Board.Cell.BLANK) {
            if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
                if (board[0][0] == Board.Cell.X) {
                    return Board.Result.XWins;
                } else {
                    return Board.Result.OWins;
                }
            }
        }
        if(board[0][2] != Board.Cell.BLANK) {
            if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
                if (board[0][2] == Board.Cell.X) {
                    return Board.Result.XWins;
                } else {
                    return Board.Result.OWins;
                }
            }
        }
        if(board[0][0] != Board.Cell.BLANK) {
            if (board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
                if (board[0][0] == Board.Cell.X) {
                    return Board.Result.XWins;
                } else {
                    return Board.Result.OWins;
                }
            }
        }
        if(board[1][0] != Board.Cell.BLANK) {
            if (board[1][0] == board[1][1] && board[1][0] == board[1][2]) {
                if (board[1][0] == Board.Cell.X) {
                    return Board.Result.XWins;
                } else {
                    return Board.Result.OWins;
                }
            }
        }
        if(board[2][0] != Board.Cell.BLANK) {
            if (board[2][0] == board[2][1] && board[2][0] == board[2][2]) {
                if (board[2][0] == Board.Cell.X) {
                    return Board.Result.XWins;
                } else {
                    return Board.Result.OWins;
                }
            }
        }

        if(board[0][0] != Board.Cell.BLANK) {
            if (board[0][0] == board[1][0] && board[0][0] == board[2][0]) {
                if (board[0][0] == Board.Cell.X) {
                    return Board.Result.XWins;
                } else {
                    return Board.Result.OWins;
                }
            }
        }
        if(board[0][1] != Board.Cell.BLANK) {
            if (board[0][1] == board[1][1] && board[0][1] == board[2][1]) {
                if (board[0][1] == Board.Cell.X) {
                    return Board.Result.XWins;
                } else {
                    return Board.Result.OWins;
                }
            }
        }
        if(board[0][2] != Board.Cell.BLANK) {
            if (board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
                if (board[0][2] == Board.Cell.X) {
                    return Board.Result.XWins;
                } else {
                    return Board.Result.OWins;
                }
            }
        }
        for (Board.Cell[] cells : board) {
            for (Board.Cell cell : cells) {
                if (cell == Board.Cell.BLANK) {
                    return Board.Result.Unknown;
                }
            }
        }
        return Board.Result.Tie;
    }

    public enum Result{
        Unknown,
        XWins,
        OWins,
        Tie
    }

    public enum Cell {
        X,
        O,
        BLANK;
        public Cell nextPlayer(){
            if(this.equals(Cell.X)){
                return Cell.O;
            }else{
                return Cell.X;
            }
        }
    }

    public void copyBoard(Board source) {
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(source.board[i], 0, board[i], 0, board[i].length);
        }
    }

    public void copyBoard(Cell[][] source) {
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(source[i], 0, board[i], 0, board[i].length);
        }
    }

    public boolean sameBoard(Board target){
        for(int i = 0 ; i < board.length ; i++ ){
            for(int j = 0 ; j < board[i].length ; j++ ){
                if(board[i][j] != target.board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void resetBoard() {
        for (Cell[] cells : board) {
            Arrays.fill(cells, Cell.BLANK);
        }
    }



}
