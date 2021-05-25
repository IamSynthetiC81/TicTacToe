package AIHelperFunctions;

import DynamicMemory.List;
import TicTacToe.Board;
import TicTacToe.Move;

public interface SymmetriesHandler{

    int BOARD_SYMMETRIES = 5;

    static Board[] rotateBoard(Board target){

        Board[] _buffer = new Board[BOARD_SYMMETRIES];

        _buffer[0] = new Board();
        _buffer[1] = new Board();
        _buffer[2] = new Board();
        _buffer[3] = new Board();
        _buffer[4] = new Board();

        /*================1 ROTATION================*/
        _buffer[0].board[0][0] = target.board[0][2];
        _buffer[0].board[0][1] = target.board[1][2];
        _buffer[0].board[0][2] = target.board[2][2];

        _buffer[0].board[1][0] = target.board[0][1];
        _buffer[0].board[1][1] = target.board[1][1];
        _buffer[0].board[1][2] = target.board[2][1];

        _buffer[0].board[2][0] = target.board[0][0];
        _buffer[0].board[2][1] = target.board[1][0];
        _buffer[0].board[2][2] = target.board[2][0];
        /*================2 ROTATIONS========*/
        _buffer[1].board[0][0] = target.board[2][2];
        _buffer[1].board[0][1] = target.board[2][1];
        _buffer[1].board[0][2] = target.board[2][0];

        _buffer[1].board[1][0] = target.board[1][2];
        _buffer[1].board[1][1] = target.board[1][1];
        _buffer[1].board[1][2] = target.board[1][0];

        _buffer[1].board[2][0] = target.board[0][2];
        _buffer[1].board[2][1] = target.board[0][1];
        _buffer[1].board[2][2] = target.board[0][0];
        /*================3 ROTATIONS========*/
        _buffer[2].board[0][0] = target.board[2][0];
        _buffer[2].board[0][1] = target.board[1][0];
        _buffer[2].board[0][2] = target.board[0][0];

        _buffer[2].board[1][0] = target.board[2][1];
        _buffer[2].board[1][1] = target.board[1][1];
        _buffer[2].board[1][2] = target.board[0][1];

        _buffer[2].board[2][0] = target.board[2][2];
        _buffer[2].board[2][1] = target.board[1][2];
        _buffer[2].board[2][2] = target.board[0][2];
        /*================FLIPPED HORIZONTALLY================*/
        _buffer[3].board[0][0] = target.board[2][0];
        _buffer[3].board[0][1] = target.board[2][1];
        _buffer[3].board[0][2] = target.board[2][2];

        _buffer[3].board[2][0] = target.board[0][0];
        _buffer[3].board[2][1] = target.board[0][1];
        _buffer[3].board[2][2] = target.board[0][2];

        _buffer[3].board[1][0] = target.board[1][0];
        _buffer[3].board[1][1] = target.board[1][1];
        _buffer[3].board[1][2] = target.board[1][2];
        /*================FLIPPED VERTICALLY===============*/
        _buffer[4].board[0][0] = target.board[0][2];
        _buffer[4].board[1][0] = target.board[1][2];
        _buffer[4].board[2][0] = target.board[2][2];

        _buffer[4].board[0][2] = target.board[0][0];
        _buffer[4].board[1][2] = target.board[1][0];
        _buffer[4].board[2][2] = target.board[2][0];

        _buffer[4].board[0][1] = target.board[0][1];
        _buffer[4].board[1][1] = target.board[1][1];
        _buffer[4].board[2][1] = target.board[2][1];
        return _buffer;
    }

    static List<Move> checkForSymmetries(List<Move> moves, Board.Cell[][] original){
        List<Board> _boards = new List<>();
        List<Move> _movesToDelete = new List<>();

        Board[] rotatedBoards;

        Board buffer;
        for(Move move : moves){
            buffer = new Board();
            buffer.copyBoard(original);
            buffer.move(move);
            _boards.add(buffer);
        }

        for(int movesIndex = 1 ; movesIndex < _boards.size() ; movesIndex++ ){

            rotatedBoards = rotateBoard(_boards.get(movesIndex));

            for(int i = 0 ; i < movesIndex ; i++ ) {
                for (Board rotatedBoard : rotatedBoards) {
                    if (sameBoard(rotatedBoard,_boards.get(i))) {
                        moves.get(movesIndex).symmetries.add(moves.get(i));
                        moves.get(movesIndex).hasSymmetries = true;
                        _movesToDelete.add(moves.get(i));
                        break;
                    }
                }
            }
        }
        for (Move move : _movesToDelete) {
            moves.remove(move);
        }
        return moves;
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
}
