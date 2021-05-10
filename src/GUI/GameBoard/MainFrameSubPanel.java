package GUI.GameBoard;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import Players.Player;
import TicTacToe.Game;

import javax.swing.*;
import java.awt.*;


public class MainFrameSubPanel extends JLayeredPane implements Dimensions, DarkColourPallet, GameBoard {


    public MainFrameSubPanel() {
//        this.setBounds(0, 0, GameBoard_Width, GameBoard_Height);
//        this.setSize(GameBoard_Width, GameBoard_Height);
        this.setPreferredSize(new Dimension(GameBoard_Width, GameBoard_Height));


        this.add(GameBoard,1);
        this.add(winnerPanel, 0);
    }

    public void show(Object panel){
        if(panel == GameBoard) {
            this.setLayer(GameBoard, 1);
            this.setLayer(winnerPanel, 0);
        }else{
            this.setLayer(GameBoard, 0);
            this.setLayer(winnerPanel, 1);
        }
    }

    public void newGame( Player playerX , Player playerO) {
        GameBoard.newGame(playerX,playerO);
    }

    public void updateBoard(){
        GameBoard.updateBoard();
    }

    public void nextTurn(){
        GameBoard.nextTurn();
    }
}