package GUI.GameBoard;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinnerPanel extends JPanel implements Dimensions, DarkColourPallet,GameBoard {
    private final WinnerPanel.ButtonPanel buttonPanel = new ButtonPanel();
    private final WinnerPanel.WinnerLabel winnerLabel = new WinnerLabel();

    WinnerPanel() {
        this.setBounds(0, 0, GameBoard_Width, GameBoard_Height);
        this.setBackground(TINT);
        this.setOpaque(true);
        this.setLayout(new BorderLayout(10, 10));
        this.setVisible(true);

        this.add(winnerLabel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
    }

    public void Label(String string) {
        this.winnerLabel.setText(string);
    }

    private static class WinnerLabel extends JLabel {
        WinnerLabel() {
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setVerticalAlignment(SwingConstants.CENTER);
            this.setVerticalTextPosition(SwingConstants.CENTER);
            this.setHorizontalTextPosition(SwingConstants.CENTER);
            this.setFont(new Font("DialogInput", Font.BOLD, 82));
            this.setForeground(Color.WHITE);
        }
    }

    private static class ButtonPanel extends JPanel implements ActionListener {
        public JButton replay = new JButton("REPLAY");
        public JButton back = new JButton("BACK");

        ButtonPanel() {
            this.setSize(GameBoard_Width, 200);
            this.setOpaque(false);
            this.setFocusable(false);
            this.setLayout(new FlowLayout(FlowLayout.CENTER));

            replay.setBounds(5, 250, 200, 100);
            replay.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
            replay.setBackground(BUTTON);
            replay.addActionListener(this);
            replay.setFocusable(false);

            back.setBounds(350, 250, 200, 100);
            back.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
            back.setBackground(BUTTON);
            back.addActionListener(this);
            back.setFocusable(false);

            this.add(back);
            this.add(replay);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == replay) {
                GameBoard.newGame(TicTacToeBoard.game.getPlayerX() , TicTacToeBoard.game.getPlayerO()  );

                GameBoard.nextTurn();
                GameBoard.updateBoard();

            } else if (e.getSource() == back) {
                MAIN_PANEL.show(MAIN_PANEL,"MainMenu");
                LEFT_PANEL.enableButton(true);
                RIGHT_PANEL.enableButton(true);
            }
        }

    }
}
