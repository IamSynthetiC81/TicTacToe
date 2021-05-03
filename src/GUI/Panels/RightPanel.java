package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import TicTacToe.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RightPanel extends JPanel implements ActionListener, Dimensions, Panels, DarkColourPallet {
    public static JButton   undo    = new JButton("UNDO");
    public static JButton   hint    = new JButton("HINT");
    JButton selectPlayerBtn;

    RightPanel() {

        this.setPreferredSize(new Dimension(LeftPanel_Width,LeftPanel_Height));
        this.setBackground(FRAME);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.selectPlayerBtn = new JButton("Select Player");
        //       this.selectPlayerBtn.setBounds(0, 0 , 80, 100);
        this.selectPlayerBtn.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 13));
        this.selectPlayerBtn.setBackground(new Color(163, 163, 163));
        this.selectPlayerBtn.setFocusable(false);
        this.add(this.selectPlayerBtn);

//        this.setPreferredSize(new Dimension(RightPanel_Width, RightPanel_Height));
//        this.setBackground(FRAME);
//        this.setBorder(BorderFactory.createRaisedBevelBorder());
//        this.setLayout(new GridBagLayout());
//        this.add(undo);
//        this.add(hint);
//
//        undo.addActionListener(this);
//        undo.setBorder(BorderFactory.createRaisedSoftBevelBorder());
//        undo.setBackground(BUTTON);
//        hint.addActionListener(this);
//        hint.setBorder(BorderFactory.createRaisedSoftBevelBorder());
//        hint.setBackground(BUTTON);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hint) {
            board.Hint(game.giveHint());
        }else{
            if(game.board.GetResult() == Board.Result.Unknown) {
                game.undo();
                game.undo();
            }
        }
        board.updateBoard();
    }
}
