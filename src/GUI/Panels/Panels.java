package GUI.Panels;

import TicTacToe.Game;

public interface Panels {

    Game               game            = new Game();

    BottomPanel         bottomPanel     = new BottomPanel();
    Header              header          = new Header();
    LeftPanel           scoreboard      = new LeftPanel();
    RightPanel          settings        = new RightPanel();
    MainFrameSubPanel   board           = new MainFrameSubPanel();
    MainMenu            MainMenu        = new MainMenu();
    MainPanel           mainPanel       = new MainPanel();
}
