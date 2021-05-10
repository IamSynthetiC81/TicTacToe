package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import Players.Player;
import Players.Players;
import TicTacToe.Game;

import javax.naming.InvalidNameException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener, DarkColourPallet, Dimensions, Panels, Players {
    JButton NewGame = new JButton("NEW GAME");
    JButton NewPlayer = new JButton("ADD PLAYER");
    JButton HallOfFame = new JButton("HallOfFame");
    JButton Quit = new JButton("QUIT");

    MainMenu(){
        this.setBounds(0,0,GameBoard_Width,GameBoard_Height);
        this.setBackground(FRAME_OBJECT_ENABLED);
        this.setLayout(new GroupLayout(this));
        this.add(NewGame);
        this.add(NewPlayer);
        this.add(HallOfFame);
        this.add(Quit);
        this.setVisible(true);
        this.setOpaque(true);

        NewGame.setSize(200,100);
        NewGame.addActionListener(this);
        NewGame.setFocusable(false);
        NewGame.setEnabled(false);
        NewGame.setBounds((GameBoard_Width-NewGame.getWidth())/2,20,200,100);

        NewPlayer.setSize(200,100);
        NewPlayer.addActionListener(this);
        NewPlayer.setFocusable(false);
        NewPlayer.setBounds((GameBoard_Width-NewPlayer.getWidth())/2,NewGame.getY()+150,200,100);

        HallOfFame.setSize(200,100);
        HallOfFame.addActionListener(this);
        HallOfFame.setFocusable(false);
        HallOfFame.setBounds((GameBoard_Width-HallOfFame.getWidth())/2,NewPlayer.getY()+150,200,100);

        Quit.setSize(200,100);
        Quit.addActionListener(this);
        Quit.setFocusable(false);
        Quit.setBounds((GameBoard_Width-HallOfFame.getWidth())/2,HallOfFame.getY()+150,200,100);
    }


    public void setNewGameEnabled(boolean ready) {
        this.NewGame.setEnabled(ready);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == HallOfFame){
            MAIN_PANEL.show(MAIN_PANEL,"HallOfFame");
        }else if(e.getSource() == NewGame){

            BOARD.newGame(playerRoster.findByName(LEFT_PANEL.getCurPlayer()),playerRoster.findByName(RIGHT_PANEL.getCurPlayer()));
            BOARD.nextTurn();

            RIGHT_PANEL.updateStats();
            LEFT_PANEL.updateStats();
            BOARD.updateBoard();
            MAIN_PANEL.show(MAIN_PANEL,"Board");
        }else if(e.getSource() == NewPlayer){
            String name = JOptionPane.showInputDialog("Insert player's name: ");

            if(name == null)
                return;

            else if(name.strip().length() > 20) {
                JOptionPane.showMessageDialog(null, "Insert a valid name!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            else
                playerRoster.addPlayer(new Player(name));
        }
    }
}
