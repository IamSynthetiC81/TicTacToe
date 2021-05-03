package GUI.Panels;

import GUI.Dimensions;
import GUI.Panels.Panels;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JLayeredPane implements Dimensions, Panels {
    public CardLayout MainPanelLayout;
    MainPanel() {
        MainPanelLayout = new CardLayout();
        this.setLayout(MainPanelLayout);
        this.setPreferredSize(new Dimension(GameBoard_Width, GameBoard_Height));
        this.add(board, "Board");
        this.add(MainMenu, "MainMenu");
        MainPanelLayout.first(this);

    }
}
