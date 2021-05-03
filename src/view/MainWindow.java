package view;

//import control.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    /*=====================FINAL NUMBERS=====================*/
    public final static int WIDTH = 936;
    public final static int HEIGHT = 770;
    public final static int TOP_HEIGHT = 100;
    public final static int PLAYER_WIDTH = 200;
    public final static int LEFT = 1;
    public final static int RIGHT = 2;


    /*=====================PANELS=====================*/
    TopPanel topPanel;
    PlayerPanel playerPanelL;
    PlayerPanel playerPanelR;
    MainAreaPanel mainAreaPanel;
//    GameController gameController;

    public MainWindow(/*GameController gameController*/) {

//        this.gameController = gameController;

        Container container = this.getContentPane();

        /*=====================FRAME SETTINGS=====================*/
        this.setSize(WIDTH, HEIGHT); //sets the x-dimension and y-dimension
        this.setTitle("Tic Tac Toe");  //Sets title
        this.addWindowListener(new AppListener());   //Exit out of application
        this.setResizable(false);  //prevents frame from being resized
        ImageIcon image = new ImageIcon("logo.png");
        this.setIconImage(image.getImage());
        container.setBackground(new Color(44, 44,44));  //Change colour of background
        this.setLayout(new BorderLayout(10,10));
        this.setVisible(true);

        /*=====================PANELS INITIALIZATION=====================*/
        playerPanelL = new PlayerPanel(MainWindow.LEFT);
        playerPanelR = new PlayerPanel(MainWindow.RIGHT);
        mainAreaPanel = new MainAreaPanel();
        topPanel = new TopPanel();


        /*=====================FRAME ADDITIONS=====================*/
        container.add(topPanel, BorderLayout.NORTH);
        container.add(playerPanelL, BorderLayout.EAST);
        container.add(playerPanelR, BorderLayout.WEST);
        container.add(mainAreaPanel, BorderLayout.CENTER);

        pack();
    }

    private class AppListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent evt){
            System.out.println("bye bye");
            System.exit(0);
        }
    }


}
