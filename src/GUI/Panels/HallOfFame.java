package GUI.Panels;

import DynamicMemory.List;
import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import Players.Players;
import Players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HallOfFame extends JPanel implements ActionListener, Panels, DarkColourPallet, Dimensions {
    private static final int NUMBER_OF_PLAYERS = 10;
    private static final int PANEL_WIDTH = GameBoard_Width;
    private static final int PANEL_HEIGHT = GameBoard_Height;

    JButton Back = new JButton("BACK");
    Scoreboard scoreboard = new Scoreboard();


    HallOfFame() {
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setBackground(FRAME);

        scoreboard.setBounds(10, 10, PANEL_WIDTH - 20, PANEL_HEIGHT);

//        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setLayout(null);
        this.add(Back);
        this.add(scoreboard);

        Back.setBounds(20, GameBoard_Height - 60, 200, 50);
        Back.addActionListener(this);


    }

    public void update(){
        scoreboard.update();
    }

    private static class Scoreboard extends JPanel implements Players {
        private static final int SPACER = 10;
        private static final int ROW_HEIGHT = 40;
        private static final int ROW_WIDTH = GameBoard_Width - 60;
        private static final int SCOREBOARD_HEIGHT = ((ROW_HEIGHT + SPACER) * 10) + 10;
        private static final int SCOREBOARD_WIDTH = GameBoard_Width - 40;

        List<Player> records;
        Row[] Rows          = new Row[NUMBER_OF_PLAYERS];

        Scoreboard() {
            this.setSize(SCOREBOARD_WIDTH, SCOREBOARD_HEIGHT);
            this.setBounds(10, 10, SCOREBOARD_WIDTH, SCOREBOARD_HEIGHT);
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setBackground(FRAME);
            this.setLayout(null);



            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                Rows[i] = new Row();

                if (i == 0) {
                    Rows[i].setBounds(10, 10, ROW_WIDTH, ROW_HEIGHT);
                } else {
                    Rows[i].setBounds(10, Rows[i - 1].getY() + ROW_HEIGHT + SPACER, ROW_WIDTH, ROW_HEIGHT);
                }
                Rows[i].setSize(SCOREBOARD_WIDTH, ROW_HEIGHT);

                this.add(Rows[i]);
            }
            System.out.println("ready");
        }

        private static class Row extends JPanel {
            private static final int ROW_HEIGHT = 40;
            private static final int COL_WIDTH = (SCOREBOARD_WIDTH - 40) / 3;

            private final JLabel Player = new JLabel();
            private final JLabel Score = new JLabel();
            private final JLabel GamesPlayed = new JLabel();

            Row() {
                this.setLayout(new BorderLayout(Spacers, 0));
                this.setSize(SCOREBOARD_WIDTH - 100, ROW_HEIGHT);
                this.setBackground(FRAME);
            }

            public void update(Player player){
                Player.setText(player.getName());
                Score.setText(String.format("%.2f",player.getScore()));
                GamesPlayed.setText(String.format("%5d",player.getGamesNum()));

                Player.setPreferredSize(new Dimension(COL_WIDTH, ROW_HEIGHT));
                Player.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                Player.setForeground(Color.WHITE);

                Score.setPreferredSize(new Dimension(COL_WIDTH, ROW_HEIGHT));
                Score.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                Score.setForeground(Color.WHITE);

                GamesPlayed.setPreferredSize(new Dimension(COL_WIDTH, ROW_HEIGHT));
                GamesPlayed.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                GamesPlayed.setForeground(Color.WHITE);

                Player.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                Score.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
                GamesPlayed.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));

                Player.setHorizontalAlignment(SwingConstants.CENTER);
                Score.setHorizontalAlignment(SwingConstants.CENTER);
                GamesPlayed.setHorizontalAlignment(SwingConstants.CENTER);

//                Player.setBorder(BorderFactory.createRaisedBevelBorder());
//                Score.setBorder(BorderFactory.createRaisedBevelBorder());
//                GamesPlayed.setBorder(BorderFactory.createRaisedBevelBorder());


                this.add(Player, BorderLayout.WEST);
                this.add(Score, BorderLayout.CENTER);
                this.add(GamesPlayed, BorderLayout.EAST);
            }

        }

        private void update(){

            records = playerRoster.findHallOfFame(NUMBER_OF_PLAYERS);

            for(int i = 0 ; i < records.size() ; i++ ) {
                Rows[i].update(records.get(i));
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            MAIN_PANEL.show(MAIN_PANEL, "MainMenu");
        }
    }
}

