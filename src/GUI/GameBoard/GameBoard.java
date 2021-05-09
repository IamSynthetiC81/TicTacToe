package GUI.GameBoard;

import GUI.Panels.Panels;

public interface GameBoard extends Panels {
    WinnerPanel     winnerPanel = new WinnerPanel();
    TicTacToeBoard  GameBoard   = new TicTacToeBoard();

    default void showWinnerPanel(Boolean bool){
        winnerPanel.setVisible(bool);
    }

}
