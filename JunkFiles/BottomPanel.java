package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel implements Dimensions, Panels, DarkColourPallet {
    BottomPanel() {
        this.setPreferredSize(new Dimension(BottomPanel_Width,BottomPanel_Height));
        this.setBackground(FRAME);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}