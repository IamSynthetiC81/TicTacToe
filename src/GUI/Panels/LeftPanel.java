package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel implements DarkColourPallet, Dimensions {
    LeftPanel() {
        this.setPreferredSize(new Dimension(LeftPanel_Width,LeftPanel_Height));
        this.setBackground(FRAME);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
