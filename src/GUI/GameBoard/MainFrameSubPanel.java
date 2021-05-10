package GUI.GameBoard;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import Players.Player;

import javax.swing.*;


public class MainFrameSubPanel extends JLayeredPane implements Dimensions, DarkColourPallet, GameBoard {

    public MainFrameSubPanel() {
        this.setBounds(0, 0, GameBoard_Width, GameBoard_Height);
        this.setSize(GameBoard_Width, GameBoard_Height);

        this.add(GameBoard, 1);
        this.add(winnerPanel, 0);
    }

    public void newGame( Player playerX , Player playerO) {
        GameBoard.newGame(playerX,playerO);
    }

    public void showWinnerPanel(boolean bool) {
        winnerPanel.setVisible(bool);
    }

    public void updateBoard() throws InterruptedException {
        GameBoard.updateBoard();
    }

    public void nextTurn() throws InterruptedException {
        GameBoard.nextTurn();
    }

//    public void Hint(Move hint){
//        GameBoard.Hint(hint);
//    }

//    private class TicTacToeBoard extends JPanel implements ActionListener {
//        public JButton[] buttons = new JButton[9];
//        public Board.Result winner = Board.Result.Unknown;
//
//        TicTacToeBoard() {
//            /*=====================BOARD INITIALIZATION=====================*/
//            this.setBackground(FRAME_BACKGROUND);
//            this.setBounds(0, 0, GameBoard_Width, GameBoard_Height);
//            this.setLayout(new GridLayout(3, 3, Spacers, Spacers));
//            /*=====================BUTTON INITIALIZATION=====================*/
//            for (int i = 0; i < buttons.length; i++) {
//                buttons[i] = new JButton(" ");
//                buttons[i].setBackground(FRAME_OBJECT_ENABLED);
//                buttons[i].setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 40));
//                buttons[i].setFocusable(false);
//                buttons[i].addActionListener(this);
//                this.add(buttons[i]);
//            }
//        }
//
//        private void Hint(Move hint) {
//            buttons[(hint.x * 3) + hint.y].setBackground(HINT);
//        }
//
//        public void ButtonsSetEnabled(Boolean bool) {
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    buttons[(i * 3) + j].setEnabled(bool);
//                }
//            }
//        }
//
//        public void updateBoard() {
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    if (game.board.board[i][j] == Board.Cell.O) {
//                        buttons[(i * 3) + j].setText("O");
//                        buttons[(i * 3) + j].setBackground(FRAME_OBJECT_DISABLED);
//                        buttons[(i * 3) + j].setEnabled(false);
//                    } else if (game.board.board[i][j] == Board.Cell.X) {
//                        buttons[(i * 3) + j].setText("X");
//                        buttons[(i * 3) + j].setBackground(FRAME_OBJECT_DISABLED);
//                        buttons[(i * 3) + j].setEnabled(false);
//                    } else {
//                        buttons[(i * 3) + j].setText(" ");
//                        buttons[(i * 3) + j].setBackground(FRAME_OBJECT_ENABLED);
//                        buttons[(i * 3) + j].setEnabled(true);
//                    }
//                }
//            }
//            winner = game.board.GetResult();
//            if (winner != Board.Result.Unknown) {
//                this.ButtonsSetEnabled(false);
//
//                if (winner == Board.Result.XWins) {
//                    winnerPanel.Label("X WINS");
//                } else if (winner == Board.Result.OWins) {
//                    winnerPanel.Label("O WINS");
//                } else {
//                    winnerPanel.Label("TIE");
//                }
//                showWinnerPanel(true);
//                game.gameRecord.setResult(winner);
//                game.gameRecord.updatePlayers();
//                settings.updateStats();
//                scoreboard.Stats.updateStats();
//            } else {
//                showWinnerPanel(false);
//            }
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            for (int i = 0; i < buttons.length; i++) {
//                if (e.getSource() == buttons[i]) {
//                    game.parseMove(new Move(i / 3, i % 3, Board.Cell.X));
//                    updateBoard();
//                    return;
//                }
//            }
//        }
//
//    }
//
//    private class WinnerPanel extends JPanel {
//        private final MainFrameSubPanel.WinnerPanel.ButtonPanel buttonPanel = new MainFrameSubPanel.WinnerPanel.ButtonPanel();
//        private final MainFrameSubPanel.WinnerPanel.WinnerLabel winnerLabel = new MainFrameSubPanel.WinnerPanel.WinnerLabel();
//
//        WinnerPanel() {
//            this.setBounds(0, 0, GameBoard_Width, GameBoard_Height);
//            this.setBackground(TINT);
//            this.setOpaque(true);
//            this.setLayout(new BorderLayout(10, 10));
//            this.setVisible(false);
//
//            this.add(winnerLabel, BorderLayout.NORTH);
//            this.add(buttonPanel, BorderLayout.CENTER);
//        }
//
//        public void Label(String string) {
//            this.winnerLabel.setText(string);
//        }
//
//        private class WinnerLabel extends JLabel {
//            WinnerLabel() {
//                this.setHorizontalAlignment(SwingConstants.CENTER);
//                this.setVerticalAlignment(SwingConstants.CENTER);
//                this.setVerticalTextPosition(SwingConstants.CENTER);
//                this.setHorizontalTextPosition(SwingConstants.CENTER);
//                this.setFont(new Font("DialogInput", Font.BOLD, 82));
//                this.setForeground(Color.WHITE);
//            }
//        }
//
//        private class ButtonPanel extends JPanel implements ActionListener {
//            public JButton replay = new JButton("REPLAY");
//            public JButton back = new JButton("BACK");
//
//            ButtonPanel() {
//                this.setSize(GameBoard_Width, 200);
//                this.setOpaque(false);
//                this.setFocusable(false);
//                this.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//                replay.setBounds(5, 250, 200, 100);
//                replay.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
//                replay.setBackground(BUTTON);
//                replay.addActionListener(this);
//                replay.setFocusable(false);
//                replay.addActionListener(this);
//
//                back.setBounds(350, 250, 200, 100);
//                back.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
//                back.setBackground(BUTTON);
//                back.addActionListener(this);
//                back.setFocusable(false);
//                back.addActionListener(this);
//
//                this.add(back);
//                this.add(replay);
//            }
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == replay) {
//                    game.newGame();
//                    GameBoard.updateBoard();
//                } else if (e.getSource() == back) {
//                    mainPanel.MainPanelLayout.show(mainPanel, "MainMenu");
//                }
//            }
//
//        }
//    }
}