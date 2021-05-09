package GUI.SidePanels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import GUI.Panels.Panels;
import Players.Player;

import javax.swing.*;
import java.awt.*;

public class Statistics extends JPanel implements Panels, Dimensions, DarkColourPallet {
    private static final int PanelWidth = 200;
    private static final int PanelHeight = 520;
    private static final int SymbolDimensions = PanelWidth-20;
    private static final int StatPanelWidth = PanelWidth-20;
    private static final int StatPanelHeight = PanelHeight-SymbolDimensions-80;

    private final Symbol         symbol  = new Symbol();
    private final PlayerStats    playerStat = new PlayerStats();;
    private       Player         player;

    Statistics(String Symbol){
        this.setSize(PanelWidth,PanelHeight);
        this.setBackground(FRAME);
        this.setLayout(null);
        this.setBorder(BorderFactory.createRaisedBevelBorder());

        symbol.setText(Symbol);
        symbol.setSize(SymbolDimensions,SymbolDimensions);

        playerStat.setBounds(0, SymbolDimensions+10,StatPanelWidth,StatPanelHeight);

        this.add(symbol);
        this.add(playerStat);
    }

    public void addPlayer(Player input){
        player = input;
        playerStat.updateStats();
    }

    public void updateStats(){
        playerStat.updateStats();
    }

    private class Symbol extends JLabel {
        Symbol(){
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setForeground(Color.WHITE);
            this.setFont(new Font("TimesRoman", Font.BOLD, 200));
            this.setHorizontalAlignment(CENTER);
            this.setVerticalAlignment(CENTER);
        }
    }

    private class PlayerStats extends JPanel{
        private final Stat TotalGames   = new Stat();
        private final Stat WON          = new Stat();
        private final Stat LOST         = new Stat();
        private final Stat TOTAL_SCORE  = new Stat();
        private final Stat RECENT_SCORE = new Stat();
        private final Stat BEST_GAMES   = new Stat();

        PlayerStats(){
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setBackground(FRAME);
            this.setFont(new Font("TimesRoman", Font.BOLD, 13));
            this.setLayout(null);

            TotalGames.setBounds(10, 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            WON.setBounds(10, TotalGames.getY() + TotalGames.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            LOST.setBounds(10, WON.getY() + TotalGames.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            TOTAL_SCORE.setBounds(10, LOST.getY() + TotalGames.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            RECENT_SCORE.setBounds(10, TOTAL_SCORE.getY() + TotalGames.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);

            TotalGames.setVisible(true);
            WON.setVisible(true);
            LOST.setVisible(true);
            TOTAL_SCORE.setVisible(true);
            RECENT_SCORE.setVisible(true);
            this.setVisible(true);

            this.add(TotalGames);
            this.add(WON);
            this.add(LOST);
            this.add(TOTAL_SCORE);
            this.add(RECENT_SCORE);

        }

        private class Stat extends JLabel{
            public final Dimension dimensions = new Dimension(160,40);
            Stat() {
                this.setBackground(FRAME);
                this.setForeground(Color.WHITE);
                this.setFont(new Font("TimesRoman", Font.BOLD, 13));

            }
        }

        private void updateStats() {
            if (player != null) {
                TotalGames.setText("Total : " + player.getGamesNum());
                WON.setText("Won : " + player.getWinsNum());    //Needs percentage
                LOST.setText("Lost : " + player.getLossesNum());    //Needs percentage

                TOTAL_SCORE.setText("Total score : " + player.getScore());
                RECENT_SCORE.setText("Recent score : " + player.getScore()); //Needs recentScore

                this.setVisible(true);

                TotalGames.setBorder(BorderFactory.createRaisedBevelBorder());
                WON.setBorder(BorderFactory.createRaisedBevelBorder());
                LOST.setBorder(BorderFactory.createRaisedBevelBorder());
                TOTAL_SCORE.setBorder(BorderFactory.createRaisedBevelBorder());
                RECENT_SCORE.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        }
    }

}
