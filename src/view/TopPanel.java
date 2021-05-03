package view;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {

    JButton addPlayerBtn;
    JButton doneBtn;
    JButton quitBtn;
    JButton startGameBtn;

    public TopPanel() {

        this.setPreferredSize(new Dimension(MainWindow.WIDTH, MainWindow.TOP_HEIGHT));
        this.setBackground(new Color(60, 63, 65));
//      this.setBounds(0,0,500,500);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        /*=====================BUTTON INITIALIZATION=====================*/

        this.addPlayerBtn = new JButton("Add Player");
        this.addPlayerBtn.setBounds(0, 0 , 200, 100);
        this.addPlayerBtn.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        this.addPlayerBtn.setBackground(new Color(163, 163, 163));
        this.addPlayerBtn.setFocusable(false);
        this.add(this.addPlayerBtn);

        this.startGameBtn = new JButton("Start Game");
        this.startGameBtn.setBounds(200, 0 , 200, 100);
        this.startGameBtn.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        this.startGameBtn.setBackground(new Color(163, 163, 163));
        this.startGameBtn.setFocusable(false);
        this.add(this.startGameBtn);

        this.quitBtn = new JButton("Quit");
        this.quitBtn.setBounds(200, 0 , 200, 100);
        this.quitBtn.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        this.quitBtn.setBackground(new Color(163, 163, 163));
        this.quitBtn.setFocusable(false);
        this.quitBtn.addActionListener((e) -> {System.exit(0);});
        this.add(this.quitBtn);

    }

}
