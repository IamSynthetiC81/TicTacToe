package GUI.SidePanels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import GUI.Panels.Panels;
import Players.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel implements ActionListener, Dimensions, Panels, DarkColourPallet, Players {
    private final JButton selectPlayerBtn;
    private Statistics Stats;
    private String curPlayer;
    private boolean ready;

    public SidePanel(String symbol) {
        this.setPreferredSize(new Dimension(SIDE_PANEL_WIDTH, SIDE_PANEL_HEIGHT));
        this.setBackground(FRAME);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(null);

        selectPlayerBtn = new JButton("Select Player");
        selectPlayerBtn.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
        selectPlayerBtn.setBackground(new Color(163, 163, 163));
        selectPlayerBtn.setFocusable(false);
        selectPlayerBtn.setBounds(10, 10, SIDE_PANEL_WIDTH - 20, 50);
        selectPlayerBtn.addActionListener(this);

        this.Stats = new Statistics(symbol);
        this.Stats.setBounds(10, 70, 180, 520);

        this.add(selectPlayerBtn);
        this.add(Stats);
    }

    public void enableButton(boolean bool){
        selectPlayerBtn.setEnabled(bool);
    }

    public void updateStats() {
        this.Stats.updateStats();
    }

    public boolean isReady() {
        return ready;
    }

    public String getCurPlayer() {
        return curPlayer;
    }

    private void selectPlayer() {
        this.curPlayer = (String) JOptionPane.showInputDialog(this,
            "Choose player",
            "Player selection",
            JOptionPane.PLAIN_MESSAGE,
            null,
            playerRoster.findPlayersName(),
            "Default"
        );

        if(this.curPlayer == null) {
            ready = false;

        }else {
            ready = true;
            Stats.addPlayer(playerRoster.findByName(curPlayer));
        }
        MAIN_MENU.NewGameButtonSetEnabled(RIGHT_PANEL.isReady() && LEFT_PANEL.isReady() && !RIGHT_PANEL.curPlayer.equals(LEFT_PANEL.getCurPlayer()) );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selectPlayerBtn){
            this.selectPlayer();
            this.Stats.updateStats();
        }
    }
}
