package GUI.Panels;

import GUI.GameBoard.MainFrameSubPanel;
import GUI.SidePanels.SidePanel;


public interface Panels{


//    LeftPanel           LEFT_PANEL      = new LeftPanel();
//    RightPanel          RIGHT_PANEL     = new RightPanel();

    SidePanel           LEFT_PANEL      = new SidePanel("X");
    SidePanel           RIGHT_PANEL     = new SidePanel("O");

    MainFrameSubPanel   BOARD           = new MainFrameSubPanel();
    MainMenu            MAIN_MENU       = new MainMenu();
    HallOfFame          HALL_OF_FAME    = new HallOfFame();
    MainPanel           MAIN_PANEL      = new MainPanel();

}
