//package GUI.SidePanels;
//
//import GUI.ColourPallets.DarkColourPallet;
//import GUI.Dimensions;
//import GUI.Panels.Panels;
//import Players.Player;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LeftPanel extends JPanel implements DarkColourPallet, Dimensions, Panels, ActionListener {
//    JButton selectPlayerBtn;
//    private String curPlayer;
//    private boolean ready;
//    private Statistics Stats = new Statistics(null,"X");
//
//    public LeftPanel() {
//        this.setPreferredSize(new Dimension(RightPanel_Width,RightPanel_Height));
//        this.setBackground(FRAME);
//        this.setBorder(BorderFactory.createRaisedBevelBorder());
//        this.setLayout(null);
//
//        selectPlayerBtn = new JButton("Select Player");
//        selectPlayerBtn.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
//        selectPlayerBtn.setBackground(new Color(163, 163, 163));
//        selectPlayerBtn.setFocusable(false);
//        selectPlayerBtn.setBounds(10,10,RightPanel_Width-20,50);
//        selectPlayerBtn.addActionListener(this);
//
//        Stats.setBounds(10, 70, 180, 520);
//
//        this.add(selectPlayerBtn);
//        this.add(Stats);
//    }
//
//    public void pushPlayer(Player player){
//        Stats.addPlayer(player);
//    }
//
//    public void updateStats() {
//        Stats.updateStats();
//    }
//
//    public boolean isReady() {
//        return ready;
//    }
//
//    public String getCurPlayer() {
//        return curPlayer;
//    }
//
//    private void selectPlayer() {
//        this.curPlayer = (String) JOptionPane.showInputDialog(this,
//                "Choose player",
//                "Player selection",
//                JOptionPane.PLAIN_MESSAGE,
//                null,
//                playerRoster.findPlayersName(),
//                "Default"
//        );
//
//        ready = this.curPlayer != null;
//
//        MAIN_MENU.setNewGameEnabled(this.ready && RIGHT_PANEL.isReady() && !this.curPlayer.equals(LEFT_PANEL.getCurPlayer()));
//
//        Stats.addPlayer(playerRoster.findByName(curPlayer));
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == selectPlayerBtn){
//            selectPlayer();
//        }
//    }
//}
