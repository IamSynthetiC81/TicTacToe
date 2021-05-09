package GUI;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Panels.Panels;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Panels,Dimensions, DarkColourPallet {
    public Frame() {
        this.setPreferredSize(new Dimension(Frame_Width,Frame_Height));
        this.setSize(Frame_Width,Frame_Height); //sets the x-dimension and y-dimension
        this.setTitle("Tic Tac Toe");  //Sets title
        this.addWindowListener(new AppListener());   //Exit out of application
        this.setResizable(false);  //prevents frame from being resized
        ImageIcon image = new ImageIcon("logo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(FRAME_BACKGROUND);  //Change colour of background
        this.setLayout(new BorderLayout(Spacers,Spacers));
        /*=====================FRAME ADDITIONS=====================*/
        this.add(RIGHT_PANEL,BorderLayout.EAST);
        this.add(LEFT_PANEL,BorderLayout.WEST);
        this.add(MAIN_PANEL,BorderLayout.CENTER);

        this.setVisible(true); //make frame visible
    }

    private static class AppListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent evt){
            System.out.println("bye bye");
            System.exit(0);
        }
    }
}



