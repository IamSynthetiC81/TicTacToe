import java.util.ArrayList;
import java.util.List;

public class Board{
    Cell[][] board = new Cell[][]{
            {Cell.BLANK, Cell.BLANK, Cell.BLANK},
            {Cell.BLANK, Cell.BLANK, Cell.BLANK},
            {Cell.BLANK, Cell.BLANK, Cell.BLANK}
    };

    public void Move(Move move){
        board[move.x][move.y] = move.inp;
    }

    public void clearMove(Move move){
        board[move.x][move.y] = Cell.BLANK;
    }

    public List<Move> findAvailableMoves(Cell c){
        List<Move> moves = new ArrayList<Move>();

        for(int i = 0 ; i < board.length ; i++ ){
            for (int j = 0 ; j < board[i].length ; j++ ){
                if(board[i][j] == Cell.BLANK){
                    moves.add(new Move(i,j,c));
                }
            }
        }
        return moves;
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
        BLANK
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

}

class Move{
    int x;
    int y;
    Board.Cell inp;

    Move(int x, int y, Board.Cell inp){
        this.x = x;
        this.y = y;
        this.inp = inp;
    }
}