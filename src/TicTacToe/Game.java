package TicTacToe;

import DynamicMemory.List;
import GUI.Panels.Panels;
import Players.Player;


import javax.swing.*;


public class Game implements Panels {
    private int delay = 100;
    private final Player[] players = new Player[2];
    private static Timer timer;

    public Board board = new Board();
    public GameRecord gameRecord;


    private final List<Move> GameMoves = new List<>();

    private boolean XTurn = false;

    public Game(Player player1, Player player2){
        players[0] = player1;
        players[1] = player2 ;

        gameRecord = new GameRecord(player1,player2);
    }

    private Timer setTimer(int player, Board.Cell symbol){
        timer = new Timer(delay, (event)->{
            Move AIMove = players[player].getMove(board, symbol);
            GameMoves.add(AIMove);
            board.move(AIMove);
            BOARD.ButtonsSetEnabled(true);
            nextTurn();
        });
        timer.setRepeats(false);
        return timer;
    }

    /*      Made for tests      */
    public void nextTurnTest(){
        /*
            Due to Timer errors we created a separate method without Timer and unnecessary GUI elements.
         */
        XTurn = !XTurn;

        if(board.GetResult() == Board.Result.Unknown) {
            if (XTurn && !getPlayerX().isHuman) {
                Move AIMove = getPlayerX().getMove(board, Board.Cell.X);
                board.move(AIMove);
                nextTurnTest();
            }else if (!XTurn && !getPlayerO().isHuman) {
                Move AIMove = getPlayerO().getMove(board, Board.Cell.X);
                board.move(AIMove);
                nextTurnTest();
            }
        }
    }

    public void nextTurn(){
        XTurn = !XTurn;

        if(board.GetResult() == Board.Result.Unknown) {
            if (XTurn && !getPlayerX().isHuman) {
                BOARD.ButtonsSetEnabled(false);
                timer = setTimer(0, Board.Cell.X);
                timer.restart();

            }else if (!XTurn && !getPlayerO().isHuman) {
                BOARD.ButtonsSetEnabled(false);
                timer = setTimer(1, Board.Cell.O);
                timer.restart();
            }else{
                BOARD.ButtonsSetEnabled(true);
            }
        }
        BOARD.updateBoard();
    }

    public Player getPlayerX(){
        return players[0];
    }

    public Player getPlayerO(){
        return players[1];
    }

    public void parseMove(Move move){
        if(XTurn){
            move.setPlayer(Board.Cell.X);
        }else{
            move.setPlayer(Board.Cell.O);
        }
        GameMoves.add(move);
        board.move(move);
        nextTurn();
    }

}
