package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel implements Dimensions, Panels, DarkColourPallet {
    Header() {
        this.setPreferredSize(new Dimension(TopPanel_Width,TopPanel_Height));
        this.setBackground(FRAME);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}