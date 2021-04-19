import java.util.ArrayList;
import java.util.List;

public class Board{
    Cell[][] board = new Cell[][]{
            {Cell.BLANK, Cell.BLANK, Cell.BLANK},
            {Cell.BLANK, Cell.BLANK, Cell.BLANK},
            {Cell.BLANK, Cell.BLANK, Cell.BLANK}
    };

    public void Move(Move move){
        if(move != null) {
            board[move.x][move.y] = move.inp;
        }
    }

    public void clearMove(Move move){
        board[move.x][move.y] = Cell.BLANK;
    }

    public List<Move> findAvailableMoves(Cell c,Cell[][] target){
        List<Move> moves = new ArrayList<>();

        for(int i = 0 ; i < board.length ; i++ ){
            for (int j = 0 ; j < board[i].length ; j++ ){
                if(board[i][j] == Cell.BLANK){
                    moves.add(new Move(i,j,c));
                }
            }
        }

        return checkForSymmetries(moves,target);
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

    public List<Move> checkForSymmetries(List<Move> moves, Cell[][] original){
        List<Board> _boards = new ArrayList<>();
        List<Move> movesToDelete = new ArrayList<>();
        for(Move move : moves){
            Board buffer = new Board();
            buffer.copyBoard(original);
            buffer.Move(move);
            _boards.add(buffer);
        }
        for(int i = 0 ; i < _boards.size() ; i++ ){
            Board[] rotatedBoards = new Board[3];

//            System.gc();
            for(int j = 0 ; j < 3 ; j++ ){
                rotatedBoards[j] = new Board();
                rotatedBoards[j].copyBoard(_boards.get(i));
                for(int r = 0 ; r < j+1 ; r++){
                    rotatedBoards[j].rotateBoard();
                }
                for(int k = 0 ; k < i ; k++ ){
                    if(rotatedBoards[j].sameBoard(_boards.get(k))){
                        moves.get(i).symmetries.add(moves.get(k));
                        moves.get(i).hasSymmetries = true;
                        movesToDelete.add(moves.get(k));
                    }
                }
            }

        }
        for (Move move : movesToDelete) {
            moves.remove(move);
        }
        return moves;
    }

    private void rotateBoard(){

        Board buffer = new Board();
        buffer.copyBoard(board);

        board[0][0] = buffer.board[0][2];
        board[0][1] = buffer.board[1][2];
        board[0][2] = buffer.board[2][2];

        board[1][0] = buffer.board[0][1];
        board[1][1] = buffer.board[1][1];
        board[1][2] = buffer.board[2][1];

        board[2][0] = buffer.board[0][0];
        board[2][1] = buffer.board[1][0];
        board[2][2] = buffer.board[2][0];
    }

    private void copyBoard(Board source) {
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(source.board[i], 0, board[i], 0, board[i].length);
        }
    }

    private void copyBoard(Cell[][] source) {
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(source[i], 0, board[i], 0, board[i].length);
        }
    }

    private boolean sameBoard(Board target){
        for(int i = 0 ; i < board.length ; i++ ){
            for(int j = 0 ; j < board[i].length ; j++ ){
                if(board[i][j] != target.board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}

class Move{
    int x;
    int y;
    Board.Cell inp;

    boolean hasSymmetries;
    List<Move> symmetries;

    Move(int x, int y, Board.Cell inp){
        this.x = x;
        this.y = y;
        this.inp = inp;
        symmetries = new ArrayList<>();
    }
}