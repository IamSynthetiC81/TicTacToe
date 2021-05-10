package GUI.GameBoard;

import GUI.Panels.Panels;

import java.awt.*;

public interface GameBoard extends Panels {

    WinnerPanel     winnerPanel = new WinnerPanel();
    TicTacToeBoard  GameBoard   = new TicTacToeBoard();

//    default void show(String board){
//        MainFrameSubPanelLayout.show(BOARD,board);
//    }

    default void showWinnerPanel(boolean bool){
        winnerPanel.setVisible(bool);
    }


}
