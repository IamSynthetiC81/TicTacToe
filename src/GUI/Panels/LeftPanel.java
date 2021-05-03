package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel implements DarkColourPallet, Dimensions {
    JButton selectPlayerBtn;

    LeftPanel() {
        this.setPreferredSize(new Dimension(LeftPanel_Width,LeftPanel_Height));
        this.setBackground(FRAME);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.selectPlayerBtn = new JButton("Select Player");
        //       this.selectPlayerBtn.setBounds(0, 0 , 80, 100);
        this.selectPlayerBtn.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
        this.selectPlayerBtn.setBackground(new Color(163, 163, 163));
        this.selectPlayerBtn.setFocusable(false);
        this.add(this.selectPlayerBtn);
    }

}
