package GUI.GameBoard;

import GUI.ColourPallets.DarkColourPallet;
import GUI.Dimensions;
import Players.Player;
import TicTacToe.Board;
import TicTacToe.Game;
import TicTacToe.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeBoard extends JPanel implements ActionListener, DarkColourPallet, Dimensions, GameBoard {
    private  final     JButton[]       buttons = new JButton[9];
    public             Board.Result    winner = Board.Result.Unknown;
    public  static     Game            game;

    boolean[][] buffer = new boolean[3][3];

    TicTacToeBoard() {
        /*=====================BOARD INITIALIZATION=====================*/
        this.setBackground(FRAME_BACKGROUND);
        this.setBounds(0, 0, GameBoard_Width, GameBoard_Height);
        this.setLayout(new GridLayout(3, 3, Spacers, Spacers));
        /*=====================BUTTON INITIALIZATION=====================*/
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(" ");
            buttons[i].setBackground(FRAME_OBJECT_ENABLED);
            buttons[i].setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 40));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }
    }

    public static Game newGame(Player playerX, Player playerO) {
        game = new Game(playerX , playerO);
        return game;
    }

    public void ButtonsSetEnabled(boolean bool) {
        if(!bool) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[(i * 3) + j].setEnabled(false);
                }
            }
        }else{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++){
                   if(game.board.board[i][j] == Board.Cell.BLANK){
                        buttons[(i * 3) + j].setEnabled(true);
                   }
                }
            }
        }
    }

    public void nextTurn(){
        game.nextTurn();
    }

    public void updateBoard()   {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board.board[i][j] == Board.Cell.O) {
                    buttons[(i * 3) + j].setText("O");
                    buttons[(i * 3) + j].setBackground(FRAME_OBJECT_DISABLED);
                } else if (game.board.board[i][j] == Board.Cell.X) {
                    buttons[(i * 3) + j].setText("X");
                    buttons[(i * 3) + j].setBackground(FRAME_OBJECT_DISABLED);
                } else {
                    buttons[(i * 3) + j].setText(" ");
                    buttons[(i * 3) + j].setBackground(FRAME_OBJECT_ENABLED);
                }
            }
        }
        winner = game.board.GetResult();
        if (winner != Board.Result.Unknown) {
            this.ButtonsSetEnabled(false);
            if (winner == Board.Result.XWins) {
                winnerPanel.Label("X WINS");
            } else if (winner == Board.Result.OWins) {
                winnerPanel.Label("O WINS");
            } else {
                winnerPanel.Label("TIE");
            }
            game.gameRecord.setResult(winner);
            game.gameRecord.updatePlayers();
            RIGHT_PANEL.updateStats();
            LEFT_PANEL.updateStats();
            BOARD.show(winnerPanel);
        }
        else {
            BOARD.show(GameBoard);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                ButtonsSetEnabled(false);
                game.parseMove(new Move(i / 3, i % 3));
            }
        }
    }

    public Game getGame() {
        return game;
    }
}