package ΑΙ;

import DynamicMemory.List;
import TicTacToe.Board;
import TicTacToe.Move;

public class SymmetriesHandler {
    public static Board[] rotateBoard(Board target){

        Board[] _buffer = new Board[3];

        _buffer[0] = new Board();
        _buffer[0].copyBoard(target);

        _buffer[1] = new Board();
        _buffer[1].copyBoard(target);

        _buffer[2] = new Board();
        _buffer[2].copyBoard(target);

        _buffer[0].board[0][0] = target.board[0][2];
        _buffer[0].board[0][1] = target.board[1][2];
        _buffer[0].board[0][2] = target.board[2][2];

        _buffer[0].board[1][0] = target.board[0][1];
        _buffer[0].board[1][1] = target.board[1][1];
        _buffer[0].board[1][2] = target.board[2][1];

        _buffer[0].board[2][0] = target.board[0][0];
        _buffer[0].board[2][1] = target.board[1][0];
        _buffer[0].board[2][2] = target.board[2][0];

        _buffer[1].board[0][0] = target.board[2][2];
        _buffer[1].board[0][1] = target.board[2][1];
        _buffer[1].board[0][2] = target.board[2][0];

        _buffer[1].board[1][0] = target.board[1][2];
        _buffer[1].board[1][1] = target.board[1][1];
        _buffer[1].board[1][2] = target.board[1][0];

        _buffer[1].board[2][0] = target.board[0][2];
        _buffer[1].board[2][1] = target.board[0][1];
        _buffer[1].board[2][2] = target.board[0][0];


        _buffer[2].board[0][0] = target.board[2][0];
        _buffer[2].board[0][1] = target.board[1][0];
        _buffer[2].board[0][2] = target.board[0][0];

        _buffer[2].board[1][0] = target.board[2][1];
        _buffer[2].board[1][1] = target.board[1][1];
        _buffer[2].board[1][2] = target.board[0][1];

        _buffer[2].board[2][0] = target.board[2][2];
        _buffer[2].board[2][1] = target.board[1][2];
        _buffer[2].board[2][2] = target.board[0][2];

        return _buffer;
    }

    public static List<Move> checkForSymmetries(List<Move> moves, Board.Cell[][] original){
        List<Board> _boards = new List<>();
        List<Move> _movesToDelete = new List<>();

        Board[] rotatedBoards = new Board[3];
        rotatedBoards[0] = new Board();
        rotatedBoards[1] = new Board();
        rotatedBoards[2] = new Board();

        Board buffer;
        for(Move move : moves){
            buffer = new Board();
            buffer.copyBoard(original);
            buffer.Move(move);
            _boards.add(buffer);
        }

        for(int movesIndex = 1 ; movesIndex < _boards.size() ; movesIndex++ ){

            rotatedBoards = rotateBoard(_boards.get(movesIndex));

            for(int i = 0 ; i < movesIndex ; i++ ) {
                for (Board rotatedBoard : rotatedBoards) {
                    if (rotatedBoard.sameBoard(_boards.get(i))) {
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
}
