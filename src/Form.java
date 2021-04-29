import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Form {
    private JPanel panel1;
    private JScrollPane ScoreBoard;
    private JPanel SettingsPanel;
    private JPanel Header;
    private JPanel CenterBoard;
    private JButton button_2;
    private JButton button_8;
    private JButton button_5;
    private JButton button_4;
    private JButton button_1;
    private JButton button_7;
    private JButton button_3;
    private JButton button_6;
    private JButton button_9;
    private JList Scoreboard;
    private JButton Undo;
    private JPanel Score;
    private JTextField Score_1;
    private JTextField Score_2;
    private JTextField Dash;
    private JProgressBar progressBar1;

    public Form() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        button_2.addActionListener(listener);
        button_8.addActionListener(listener);
        button_5.addActionListener(listener);
        button_4.addActionListener(listener);
        button_1.addActionListener(listener);
        button_7.addActionListener(listener);
        button_3.addActionListener(listener);
        button_6.addActionListener(listener);
        button_9.addActionListener(listener);
    }
}
