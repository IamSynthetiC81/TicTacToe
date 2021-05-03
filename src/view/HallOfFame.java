package view;

import javax.swing.*;
import java.awt.*;

public class HallOfFame extends JPanel {

    public HallOfFame() {
        super();
    }

    public void paint(Graphics graphics){
        int x = this.getWidth() / 2 - 50;
        int y = this.getHeight() /2;
        graphics.drawString("Hall of Fame", x, y);
    }
}
