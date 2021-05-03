package view;

import model.Board;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    JButton selectPlayerBtn;

    Board.Cell cell;

    PlayerPanel(int side) {
        this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT - MainWindow.TOP_HEIGHT));
        this.setBackground(new Color(60, 63, 65));
//        this.setBounds(0, 0, 500, 500);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        if(side == MainWindow.LEFT)
            cell = Board.Cell.X;
        else if(side == MainWindow.RIGHT)
            cell = Board.Cell.O;

        /*=====================BUTTON INITIALIZATION=====================*/

        this.selectPlayerBtn = new JButton("Select Player");
 //       this.selectPlayerBtn.setBounds(0, 0 , 80, 100);
        this.selectPlayerBtn.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
        this.selectPlayerBtn.setBackground(new Color(163, 163, 163));
        this.selectPlayerBtn.setFocusable(false);
        this.add(this.selectPlayerBtn);
    }
}
