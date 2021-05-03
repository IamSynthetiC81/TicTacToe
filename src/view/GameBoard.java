package view;

import model.Board;
import model.Game;
import model.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel implements ActionListener {

    /*=====================GAME=====================*/
    public Game game = new Game();
    Board.Result winner = Board.Result.Unknown;

    /*=====================BUTTONS=====================*/
    JButton[] buttons = new JButton[9];
    JButton undo;
    JButton hint;
    JButton done;

    /*=====================PANELS AND LABELS=====================*/
    JPanel board;

        /*=====================WINNER PANEL=====================*/
        JPanel winnerPanel;
        JPanel buttonPanel;
        JLabel winnerLabel;




    public GameBoard() {

        /*=====================BUTTON INITIALIZATION=====================*/
        for (int i = 0; i < buttons.length; i++) {
            buttons[i]       = new JButton(" ");
            buttons[i].addActionListener(this);
        }

        undo = new JButton("UNDO");
        hint = new JButton("HINT");

        undo.addActionListener(this);
        undo.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        undo.setBackground(new Color(160,160,160));

        hint.addActionListener(this);
        hint.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        hint.setBackground(new Color(160,160,160));

        /*=====================BOARD INITIALIZATION=====================*/
        board = new JPanel();
        board.setPreferredSize(new Dimension(500, 500));
        board.setMinimumSize(new Dimension(100, 100));
        board.setBackground(new Color(43, 43, 43));
        board.setBounds(0, 100, 500, 500);
        board.setName("Board");
        board.setLayout(new GridLayout(3, 3, 10, 10));

        /*=====================BUTTON INITIALIZATION=====================*/
        for (JButton button : buttons) {
            button.setBackground(new Color(80, 80, 80));
            button.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 40));
            button.setFocusable(false);
            board.add(button);
        }

//        /*=====================WINNER LABEL=====================*/
//        winnerLabel = new JLabel();
//        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        winnerLabel.setVerticalAlignment(SwingConstants.CENTER);
//        winnerLabel.setVerticalTextPosition(SwingConstants.CENTER);
//        winnerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
//        winnerLabel.setFont(new Font("DialogInput",Font.BOLD,82));
//        winnerLabel.setForeground(Color.WHITE);
//
//        /*=====================WINNER PANEL=====================*/
//        winnerPanel = new JPanel();
//        winnerPanel.setBounds(0,0,500,500);
//        winnerPanel.setBackground(new Color(0, 0, 0, 20));
//        winnerPanel.setOpaque(true);
//        winnerPanel.setFocusable(false);
//        winnerPanel.add(winnerLabel);
//        winnerPanel.setVisible(false);

///////////////////////////////////////////////////////////////////////////////
        /*=====================WINNER PANEL=====================*/
        winnerPanel = new JPanel();
        winnerPanel.setBounds(0,0,500,500);
        winnerPanel.setBackground(new Color(0, 0, 0, 20));
        winnerPanel.setOpaque(true);
        winnerPanel.setFocusable(false);
        winnerPanel.setVisible(false);
        winnerPanel.setLayout(new BorderLayout(10,10));

        /*=====================WINNER LABEL=====================*/
        winnerLabel = new JLabel();
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setVerticalAlignment(SwingConstants.CENTER);
        winnerLabel.setVerticalTextPosition(SwingConstants.CENTER);
        winnerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        winnerLabel.setFont(new Font("DialogInput",Font.BOLD,82));
        winnerLabel.setForeground(Color.WHITE);

        /*=====================WINNER BUTTON PANEL=====================*/
        buttonPanel = new JPanel();
        buttonPanel.setSize(500,200);
        buttonPanel.setOpaque(false);
        buttonPanel.setFocusable(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(new Dimension(500,200));
        buttonPanel.setBounds(0,0,500,200);

        /*=====================WINNER BUTTON=====================*/
        this.done = new JButton("Done");
        this.done.setBounds(100, 0 , 200, 100);
        this.done.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        this.done.setBackground(new Color(163, 163, 163));
        this.done.setFocusable(false);
        this.done.setEnabled(false);
        this.done.setVisible(false);
        this.done.addActionListener(this);

        /*=====================BUTTON PANEL ADDITION=====================*/
        buttonPanel.add(done);

        /*=====================FRAME ADDITIONS=====================*/
        winnerPanel.add(winnerLabel,BorderLayout.NORTH);
        winnerPanel.add(buttonPanel,BorderLayout.CENTER);



    /////////////////////////////////////////////////////////////////////////////////////////////////////

        /*=====================GAMEBOARD INITIALIZATION=====================*/
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setPreferredSize(new Dimension(MainWindow.WIDTH - 2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT - MainWindow.TOP_HEIGHT));
        this.setBackground(new Color(60, 63, 65));
        this.setLayout(new BorderLayout());
        this.add(winnerPanel, BorderLayout.NORTH);
        this.add(board, BorderLayout.SOUTH);

    }

    public void newGame() {
        winnerPanel.setVisible(false);
        this.game.newGame();
        this.updateBoard();
    }

    public void updateBoard(){
        for(int i = 0 ; i < 3 ; i ++ ){
            for(int j = 0 ; j < 3 ; j++ ){
                if(game.getBoardCell(i, j) == Board.Cell.O) {
                    buttons[(i * 3) + j].setText("O");
                    buttons[(i * 3) + j].setBackground(new Color(40, 40, 40));
                    buttons[(i * 3) + j].setEnabled(false);
                }else if(game.getBoardCell(i, j) == Board.Cell.X){
                    buttons[(i * 3) + j].setText("X");
                    buttons[(i * 3) + j].setBackground(new Color(40, 40, 40));
                    buttons[(i * 3) + j].setEnabled(false);
                }else{
                    buttons[(i * 3) + j].setText(" ");
                    buttons[(i * 3) + j].setBackground(new Color(80, 80, 80));
                    buttons[(i * 3) + j].setEnabled(true);
                }
            }
        }
        winner = game.getBoard().GetResult();

        if(winner != Board.Result.Unknown){
            winnerPanel.setVisible(true);
            this.done.setEnabled(true);
            this.done.setVisible(true);

            for(int i = 0 ; i < 3 ; i ++ ) {
                for (int j = 0; j < 3; j++) {
                    buttons[(i * 3) + j].setEnabled(false);
                }
            }
            if(winner == Board.Result.XWins){
                winnerLabel.setText("X WINS");
            }else if(winner == Board.Result.OWins){
                winnerLabel.setText("O WINS");
            }else{
                winnerLabel.setText("TIE");
            }
        }
    }

    private void Hint(Move hint){
        buttons[(hint.getX()*3) + hint.getY()].setBackground(new Color(120, 80, 80));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                game.parseMove(new Move(i / 3, i % 3, Board.Cell.X));
            }
        }
/*        if (e.getSource() == replay) {
            game.newGame();
            updateBoard();
        }*/

        if(e.getSource() == undo){
                game.undo();
                game.undo();
        }
        else if(e.getSource() == hint){
                Hint(game.giveHint());
                return;
        }
        else if(e.getSource() == done){
            this.newGame();
            this.done.setEnabled(false);
            this.done.setVisible(false);
        }

        updateBoard();
        }
}
