package view;

import javax.swing.*;
import java.awt.*;

public class MainAreaPanel extends JPanel {

    CardLayout cards;
    HallOfFame hallOfFame;
    GameBoard gameBoard;

    public MainAreaPanel() {
        cards = new CardLayout();
        this.setLayout(cards);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setPreferredSize(new Dimension(MainWindow.WIDTH - 2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT - MainWindow.TOP_HEIGHT));
        this.setBackground(new Color(60, 63, 65));

        hallOfFame = new HallOfFame();
        gameBoard = new GameBoard();

        this.add("GB", gameBoard);
        this.add("HOL", hallOfFame);


    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
