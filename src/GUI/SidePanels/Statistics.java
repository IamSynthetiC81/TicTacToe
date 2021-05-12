package GUI.SidePanels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import GUI.Panels.Panels;
import Players.Player;
import TicTacToe.GameRecord;

import javax.swing.*;
import java.awt.*;

public class Statistics extends JPanel implements Panels, Dimensions, DarkColourPallet {
    private static final int PanelWidth = 200;
    private static final int PanelHeight = 520;
    private static final int SymbolDimensions = PanelWidth-20;
    private static final int StatPanelWidth = PanelWidth-20;
    private static final int StatPanelHeight = PanelHeight-SymbolDimensions;

    private final PlayerStats    playerStat = new PlayerStats();
    private       Player         player;

    Statistics(String Symbol){
        this.setSize(PanelWidth,PanelHeight);
        this.setBackground(FRAME);
        this.setLayout(null);

        Statistics.Symbol symbol = new Symbol();
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

    private static class Symbol extends JLabel {
        Symbol(){
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setForeground(Color.WHITE);
            this.setFont(new Font("TimesRoman", Font.BOLD, 200));
            this.setHorizontalAlignment(CENTER);
            this.setVerticalAlignment(CENTER);
        }
    }

    private class PlayerStats extends JPanel{
        private final Stat Name             = new Stat();
        private final Stat TotalGames       = new Stat();
        private final Stat WON              = new Stat();
        private final Stat LOST             = new Stat();
        private final Stat TOTAL_SCORE      = new Stat();
        private final Stat RECENT_SCORE     = new Stat();
        private final BestGames BEST_Best_GAMES = new BestGames();

        PlayerStats(){
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setBackground(FRAME);
            this.setFont(new Font("TimesRoman", Font.BOLD, 13));
            this.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

            Name.setBounds(10, 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            TotalGames.setBounds(10, Name.getY() + Name.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            WON.setBounds(10, TotalGames.getY() + TotalGames.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            LOST.setBounds(10, WON.getY() + WON.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            TOTAL_SCORE.setBounds(10, LOST.getY() + LOST.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);
            RECENT_SCORE.setBounds(10, TOTAL_SCORE.getY() + TOTAL_SCORE.dimensions.height + 10, TotalGames.dimensions.width, TotalGames.dimensions.height);


            Name.setPreferredSize(new Dimension(StatPanelWidth-20,25));
            TotalGames.setPreferredSize(new Dimension(StatPanelWidth-20,25));
            WON.setPreferredSize(new Dimension(StatPanelWidth-20,25));
            LOST.setPreferredSize(new Dimension(StatPanelWidth-20,25));
            TOTAL_SCORE.setPreferredSize(new Dimension(StatPanelWidth-20,25));
            RECENT_SCORE.setPreferredSize(new Dimension(StatPanelWidth-20,25));
            BEST_Best_GAMES.setPreferredSize(new Dimension(StatPanelWidth-10,100));

//            Name.setVisible(true);
//            TotalGames.setVisible(true);
//            WON.setVisible(true);
//            LOST.setVisible(true);
//            TOTAL_SCORE.setVisible(true);
//            RECENT_SCORE.setVisible(true);
//            BEST_Best_GAMES.setVisible(true);
            this.setVisible(true);

            this.add(Name);
            this.add(TotalGames);
            this.add(WON);
            this.add(LOST);
            this.add(TOTAL_SCORE);
            this.add(RECENT_SCORE);
            this.add(BEST_Best_GAMES);

        }

        private class Stat extends JLabel{
            public final Dimension dimensions = new Dimension(160,40);
            Stat() {
                this.setBackground(FRAME);
                this.setForeground(Color.WHITE);
                this.setFont(new Font("TimesRoman", Font.BOLD, 13));

            }

            Stat(GameRecord game) {
                String outcome;
                if(game.getState() == GameRecord.State.Defeat){
                    outcome = "Defeat";
                }else if(game.getState() == GameRecord.State.Win){
                    outcome = "Victory";
                }else{
                    outcome = "Tie";
                }

                this.setBackground(FRAME);
                this.setForeground(Color.WHITE);
                this.setFont(new Font("TimesRoman", Font.BOLD, 13));
                this.setText(game.getPlayer(0).getName() + " VS " + game.getPlayer(1).getName() + "    " + outcome);
            }

        }

        private class BestGames extends JPanel{

            JLabel[] games = new JLabel[5];
            JLabel[] Outcome = new JLabel[5];


            BestGames() {
                this.setBackground(FRAME);
                this.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

                for (int i = 0; i < 5; i++) {
                    games[i] = new JLabel();
                    Outcome[i] = new JLabel();

                    games[i].setVisible(false);
                    Outcome[i].setVisible(false);

                    games[i].setFont(new Font("TimesRoman", Font.BOLD, 13));
                    Outcome[i].setFont(new Font("TimesRoman", Font.BOLD, 13));

                    games[i].setPreferredSize(new Dimension(110,15));
                    Outcome[i].setPreferredSize(new Dimension(50,15));

                    games[i].setForeground(Color.WHITE);
                    Outcome[i].setForeground(Color.WHITE);

                    this.add(games[i]);
                    this.add(Outcome[i]);
                }
            }

            public void updateStats(GameRecord[] playerGames){
                for (int i = 0; i < playerGames.length; i++) {
                    if (playerGames[i] != null) {
                        String opponentName;
                        String symbol;
                        if((playerGames[i].getPlayer(0).getName().equals(player.getName()))){
                            opponentName = playerGames[i].getPlayer(1).getName();
                            symbol = " as X";
                        }else{
                            opponentName = playerGames[i].getPlayer(0).getName();
                            symbol = " as O";
                        }

                        games[i].setText("VS " + opponentName + symbol);
                        Outcome[i].setText(playerGames[i].getState().toString());

                        games[i].setVisible(true);
                        Outcome[i].setVisible(true);
                    }else{
                        games[i].setText(" ");
                        Outcome[i].setText(" ");

                        games[i].setVisible(false);
                        Outcome[i].setVisible(false);
                    }
                }
            }
        }

        private void updateStats() {
            if (player != null) {
                int totalGames = player.getGamesNum();
                double Won;
                double Lost;
                if(totalGames == 0){
                    Won = 0;
                    Lost = 0;
                }else {
                    Won = ((double) player.getWinsNum() / totalGames) * 100;
                    Lost = ((double) player.getLossesNum() / totalGames) * 100;
                }

                BEST_Best_GAMES.updateStats(player.getBestGames());

                Name.setText("Name : " + player.getName());
                TotalGames.setText("Total : " + totalGames);
                WON.setText(String.format("Won : %.2f%%", Won));    //Needs percentage
                LOST.setText(String.format("Lost : %.2f%%",Lost));    //Needs percentage

                TOTAL_SCORE.setText(String.format("Total score : %.2f", player.getScore()));
                RECENT_SCORE.setText(String.format("Recent score : %.2f",player.getRecentScore()));

            }
        }
    }

}
