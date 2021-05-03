package GUI.Panels;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RightPanel extends JPanel implements ActionListener, Dimensions, Panels, DarkColourPallet {
    public static JButton   undo    = new JButton("UNDO");
    public static JButton   hint    = new JButton("HINT");

    RightPanel() {
        this.setPreferredSize(new Dimension(RightPanel_Width, RightPanel_Height));
        this.setBackground(FRAME);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new GridBagLayout());
        this.add(undo);
        this.add(hint);

        undo.addActionListener(this);
        undo.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        undo.setBackground(BUTTON);
        hint.addActionListener(this);
        hint.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        hint.setBackground(BUTTON);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hint) {
            board.Hint(game.giveHint());
        }else{
            game.undo();
            game.undo();
        }
        board.updateBoard();
    }
}
