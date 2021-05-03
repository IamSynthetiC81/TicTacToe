package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener, DarkColourPallet, Dimensions, Panels {
    JButton NewGame = new JButton("NEW GAME");
    JButton NewPlayer = new JButton("ADD PLAYER");
    JButton HallOfFame = new JButton("Scoreboard");

    MainMenu(){
        this.setBounds(0,0,GameBoard_Width,GameBoard_Height);
        this.setBackground(FRAME_OBJECT_ENABLED);
        this.setLayout(new GroupLayout(this));
        this.add(NewGame);
        this.add(NewPlayer);
        this.add(HallOfFame);
        this.setVisible(true);
        this.setOpaque(true);

        NewGame.setSize(200,100);
        NewGame.addActionListener(this);
        NewGame.setBounds((GameBoard_Width-NewGame.getWidth())/2,20,200,100);

        NewPlayer.setSize(200,100);
        NewPlayer.addActionListener(this);
        NewPlayer.setBounds((GameBoard_Width-NewPlayer.getWidth())/2,NewGame.getY()+150,200,100);

        HallOfFame.setSize(200,100);
        HallOfFame.addActionListener(this);
        HallOfFame.setBounds((GameBoard_Width-HallOfFame.getWidth())/2,NewPlayer.getY()+150,200,100);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == NewGame){

        }
    }
}
