package model;

import java.util.Arrays;

public class Game {

    private Board board = new Board();
    List<Move> GameMoves = new List<>();

    public Board getBoard() {
        return board;
    }

    public Board.Cell getBoardCell(int i, int j) {
        return board.board[i][j];
    }

    public void parseMove(Move move){
        GameMoves.add(move);
        Move AIMove;
        board.Move(move);
        if(board.GetResult() == Board.Result.Unknown) {
            AIMove = AI_Optimized.getBestMove(board, Board.Cell.O);
            GameMoves.add(AIMove);
            board.Move(AIMove);
        }
    }

    public Move giveHint(){
        return AI_Optimized.getBestMove(board, Board.Cell.X);
    }

    public void newGame(){
        for(int i = 0 ; i < board.board.length ; i++ ){
            Arrays.fill(board.board[i], Board.Cell.BLANK);
        }
        GameMoves.empty();
    }

    public void undo(){
        if(GameMoves.size() > 0) {
            Move moveToUndo = GameMoves.get(GameMoves.size() - 1);
            board.board[moveToUndo.getX()][moveToUndo.getY()] = Board.Cell.BLANK;
            GameMoves.remove(moveToUndo);
        }
    }

    static class GUI{
        public void printBoard(Board board){
            for(int i = 0 ; i < board.board.length ; i++ ){
                for(int j = 0 ; j < board.board[i].length ; j++){
                    if(board.board[i][j] == Board.Cell.BLANK){
                        System.out.print("-");
                    }else if(board.board[i][j] == Board.Cell.X){
                        System.out.print('X');
                    }else{
                        System.out.print('O');
                    }
                }
                System.out.println();
            }
        }
    }



}
