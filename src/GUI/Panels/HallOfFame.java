package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import TicTacToe.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HallOfFame extends JPanel implements ActionListener,Panels, DarkColourPallet, Dimensions {
    private static final int NUMBER_OF_PLAYERS = 10;
    private static final int PANEL_WIDTH = GameBoard_Width;
    private static final int PANEL_HEIGHT = GameBoard_Height;

    JButton     Back         = new JButton("BACK");
    Scoreboard  scoreboard   = new Scoreboard();

    HallOfFame(){
        this.setSize(PANEL_WIDTH,PANEL_HEIGHT);
        this.setBackground(FRAME);

        scoreboard.setBounds(10,10,PANEL_WIDTH-20,PANEL_HEIGHT);

        this.setLayout(null);
        this.add(Back);
        this.add(scoreboard);

        Back.setBounds(20,GameBoard_Height-60,200,50);
        Back.addActionListener(this);

    }

    private static class Scoreboard extends JPanel{
        private static final int SPACER             = 10;
        private static final int ROW_HEIGHT         = 40;
        private static final int ROW_WIDTH          = GameBoard_Width-60;
        private static final int SCOREBOARD_HEIGHT  = ((ROW_HEIGHT+SPACER)*10)+10;
        private static final int SCOREBOARD_WIDTH   = GameBoard_Width-40;

        String[]    PlayerNames    = new String[NUMBER_OF_PLAYERS];
        Integer[]   PlayerScores   = new Integer[NUMBER_OF_PLAYERS];
        Integer[]   GamesPlayed    = new Integer[NUMBER_OF_PLAYERS];
        Row[]       Rows           = new Row[NUMBER_OF_PLAYERS];

        Scoreboard(){
            this.setSize(SCOREBOARD_WIDTH,SCOREBOARD_HEIGHT);
            this.setBounds(10,10,SCOREBOARD_WIDTH,SCOREBOARD_HEIGHT);
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setBackground(FRAME);
            this.setLayout(null);

            setPlayerNames();
            setPlayerScores();
            setPlayersHistory();

            for(int i = 0; i < NUMBER_OF_PLAYERS ; i++){
                Rows[i] = new Row(PlayerNames[i],PlayerScores[i],GamesPlayed[i]);

                if(i == 0) {
                    Rows[i].setBounds(10,10,ROW_WIDTH,ROW_HEIGHT);
                }else{
                    Rows[i].setBounds(10, Rows[i-1].getY()+ROW_HEIGHT+SPACER, ROW_WIDTH, ROW_HEIGHT);
                }
                Rows[i].setSize(SCOREBOARD_WIDTH,ROW_HEIGHT);

                this.add(Rows[i]);
            }
            System.out.println("ready");
        }

        private static class Row extends JPanel{
            private static final int       ROW_HEIGHT     = 40;
            private static final int       COL_WIDTH      = (SCOREBOARD_WIDTH-40)/3;

            private final        JLabel    Player         = new JLabel();
            private final        JLabel    Score          = new JLabel();
            private final        JLabel    GamesPlayed    = new JLabel();

            Row(String playerName, Integer score, Integer gamesPlayed){
                this.setLayout(new BorderLayout(Spacers,0));
                this.setSize(SCOREBOARD_WIDTH-100,ROW_HEIGHT);
                this.setBackground(FRAME);

                Player.setText(playerName);
                Score.setText(score.toString());
                GamesPlayed.setText(gamesPlayed.toString());

                Player.setPreferredSize(new Dimension(COL_WIDTH,ROW_HEIGHT));
                Player.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                Player.setForeground(Color.WHITE);

                Score.setPreferredSize(new Dimension(COL_WIDTH,ROW_HEIGHT));
                Score.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                Score.setForeground(Color.WHITE);

                GamesPlayed.setPreferredSize(new Dimension(COL_WIDTH,ROW_HEIGHT));
                GamesPlayed.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                GamesPlayed.setForeground(Color.WHITE);

                Player.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                Score.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                GamesPlayed.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));

                Player.setHorizontalAlignment(SwingConstants.CENTER);
                Score.setHorizontalAlignment(SwingConstants.CENTER);
                GamesPlayed.setHorizontalAlignment(SwingConstants.CENTER);

                Player.setBorder(BorderFactory.createRaisedBevelBorder());
                Score.setBorder(BorderFactory.createRaisedBevelBorder());
                GamesPlayed.setBorder(BorderFactory.createRaisedBevelBorder());


                this.add(Player,BorderLayout.WEST);
                this.add(Score,BorderLayout.CENTER);
                this.add(GamesPlayed,BorderLayout.EAST);
            }


        }

        private void setPlayerNames() {
            PlayerNames[0] = "MAKIS";
            PlayerNames[1] = "TAKIS";
            PlayerNames[2] = "AKIS";
            PlayerNames[3] = "SAKIS";
            PlayerNames[4] = "FAKIS";
            PlayerNames[5] = "DIMITRAKIS";
            PlayerNames[6] = "FASOLAKIS";
            PlayerNames[7] = "BASILAKIS";
            PlayerNames[8] = "KWSTAKIS";
            PlayerNames[9] = "GIORIKAS";
        }

        private void setPlayerScores() {
            PlayerScores[0] = 0;
            PlayerScores[1] = 1;
            PlayerScores[2] = 2;
            PlayerScores[3] = 3;
            PlayerScores[4] = 4;
            PlayerScores[5] = 5;
            PlayerScores[6] = 6;
            PlayerScores[7] = 7;
            PlayerScores[8] = 8;
            PlayerScores[9] = 9;
        }

        private void setPlayersHistory(){
            for(int i = 0 ; i < NUMBER_OF_PLAYERS ; i++ ){
                GamesPlayed[i] = i;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Back){
            mainPanel.MainPanelLayout.show(mainPanel,"MainMenu");
        }
    }
}
