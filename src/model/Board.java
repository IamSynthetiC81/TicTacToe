package model;

import java.util.Arrays;

public class Board{
    Cell[][] board = new Cell[][]{
            {Cell.BLANK, Cell.BLANK, Cell.BLANK},
            {Cell.BLANK, Cell.BLANK, Cell.BLANK},
            {Cell.BLANK, Cell.BLANK, Cell.BLANK}
    };

    public void Move(Move move){
        if(move != null) {
            this.board[move.getX()][move.getY()] = move.inp;
        }
    }

    public void clearMove(Move move){
        board[move.getX()][move.getY()] = Cell.BLANK;
    }

    public List<Move> findAvailableMoves(Cell c, Board target){
        List<Move> moves = new List<>();

        for(int i = 0 ; i < board.length ; i++ ){
            for (int j = 0 ; j < board[i].length ; j++ ){
                if(board[i][j] == Cell.BLANK){
                    moves.add(new Move(i,j,c));
                }
            }
        }

        return AI_Optimized.checkForSymmetries(moves,target.board);
    }

    public Result GetResult(){
        if(board[0][0] != Cell.BLANK) {
            if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
                if (board[0][0] == Cell.X) {
                    return Result.XWins;
                } else {
                    return Result.OWins;
                }
            }
        }
        if(board[0][2] != Cell.BLANK) {
            if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
                if (board[0][2] == Cell.X) {
                    return Result.XWins;
                } else {
                    return Result.OWins;
                }
            }
        }
        if(board[0][0] != Cell.BLANK) {
            if (board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
                if (board[0][0] == Cell.X) {
                    return Result.XWins;
                } else {
                    return Result.OWins;
                }
            }
        }
        if(board[1][0] != Cell.BLANK) {
            if (board[1][0] == board[1][1] && board[1][0] == board[1][2]) {
                if (board[1][0] == Cell.X) {
                    return Result.XWins;
                } else {
                    return Result.OWins;
                }
            }
        }
        if(board[2][0] != Cell.BLANK) {
            if (board[2][0] == board[2][1] && board[2][0] == board[2][2]) {
                if (board[2][0] == Cell.X) {
                    return Result.XWins;
                } else {
                    return Result.OWins;
                }
            }
        }

        if(board[0][0] != Cell.BLANK) {
            if (board[0][0] == board[1][0] && board[0][0] == board[2][0]) {
                if (board[0][0] == Cell.X) {
                    return Result.XWins;
                } else {
                    return Result.OWins;
                }
            }
        }
        if(board[0][1] != Cell.BLANK) {
            if (board[0][1] == board[1][1] && board[0][1] == board[2][1]) {
                if (board[0][1] == Cell.X) {
                    return Result.XWins;
                } else {
                    return Result.OWins;
                }
            }
        }
        if(board[0][2] != Cell.BLANK) {
            if (board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
                if (board[0][2] == Cell.X) {
                    return Result.XWins;
                } else {
                    return Result.OWins;
                }
            }
        }
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell == Cell.BLANK) {
                    return Result.Unknown;
                }
            }
        }
        return Result.Tie;
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

/*class Move{
    int x;
    int y;
    Board.Cell inp;

    boolean hasSymmetries;
    List<Move> symmetries;

    public Move(int x, int y, Board.Cell inp){
        this.x = x;
        this.y = y;
        this.inp = inp;
        symmetries = new List<>();
    }
}*/