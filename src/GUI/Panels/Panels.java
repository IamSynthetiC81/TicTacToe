package GUI.Panels;

import GUI.GameBoard.MainFrameSubPanel;
import GUI.SidePanels.SidePanel;


public interface Panels{

    SidePanel           LEFT_PANEL      = new SidePanel("X");
    SidePanel           RIGHT_PANEL     = new SidePanel("O");

    MainFrameSubPanel   BOARD           = new MainFrameSubPanel();
    MainMenu            MAIN_MENU       = new MainMenu();
    HallOfFame          HALL_OF_FAME    = new HallOfFame();
    MainPanel           MAIN_PANEL      = new MainPanel();

}
