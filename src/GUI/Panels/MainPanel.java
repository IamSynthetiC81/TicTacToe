package GUI.Panels;

import GUI.Dimensions;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JLayeredPane implements Dimensions, Panels {
    private static final CardLayout MainPanelLayout = new CardLayout();;
    MainPanel() {
        this.setLayout(MainPanelLayout);
        this.setPreferredSize(new Dimension(GameBoard_Width, GameBoard_Height));
        this.add(BOARD, "Board");
        this.add(MAIN_MENU, "MainMenu");
        this.add(HALL_OF_FAME,"HallOfFame");
        MainPanelLayout.show(this,"MainMenu");
    }

    public void show(Container parent,String name){
        MainPanelLayout.show(parent,name);
    }
}
